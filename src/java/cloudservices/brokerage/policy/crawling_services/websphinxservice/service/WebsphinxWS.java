/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudservices.brokerage.policy.crawling_services.websphinxservice.service;

import cloudservices.brokerage.policy.crawling_services.crawler4jservice.crawler_logic.repository.URLRepositoryService;
import cloudservices.brokerage.utils.file_utils.ResourceFileUtil;
import cloudservices.brokerage.utils.logging.LoggerSetup;
import cloudservices.brokerage.policy.crawling_services.websphinxservice.configuration.WebsphinxConfig;
import cloudservices.brokerage.policy.crawling_services.websphinxservice.crawler_logic.CrawlerController;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Arash Khodadadi http://www.arashkhodadadi.com/
 */
@WebService(serviceName = "WebsphinxWS")
public class WebsphinxWS {

    private final static Logger LOGGER = Logger.getLogger(WebsphinxWS.class
            .getName());

    @WebMethod(operationName = "isFilterSupported")
    public boolean isFilterSupported() {
        return false;
    }

    @WebMethod(operationName = "crawl")
    public Set<String> crawl(@WebParam(name = "seeds") Set<String> seeds) throws IOException, Exception {
        seeds=new HashSet<>();
        seeds.add("http://www.arashkhodadadi.com/");
        seeds.add("http://www.xmethods.com/ve2/index.po");
        
        LoggerSetup.setup(ResourceFileUtil.getResourcePath("log.txt"), ResourceFileUtil.getResourcePath("log.html"));
        String[] crawlSeeds = (String[]) seeds.toArray(new String[0]);
        WebsphinxConfig config = new WebsphinxConfig(crawlSeeds);
        config.setMaxDepth(1);
        CrawlerController controller = new CrawlerController(config);
        long startTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Crawling Start");
        controller.start();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        String msg = "Crawling End in " + totalTime + "ms";
        LOGGER.log(Level.INFO, msg);

        Set outgoingUrls = URLRepositoryService.getInstance().getRepository().getUrls();
        outgoingUrls.removeAll(seeds);
        return outgoingUrls;
    }
}
