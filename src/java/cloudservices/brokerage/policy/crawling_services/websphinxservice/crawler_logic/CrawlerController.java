/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudservices.brokerage.policy.crawling_services.websphinxservice.crawler_logic;

import cloudservices.brokerage.policy.crawling_services.websphinxservice.configuration.WebsphinxConfig;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import websphinx.DownloadParameters;
import websphinx.Link;

/**
 *
 * @author Arash Khodadadi http://www.arashkhodadadi.com/
 */
public class CrawlerController {

    private final WebsphinxConfig config;

    public CrawlerController(WebsphinxConfig config) {
        this.config = config;
    }

    public void start() {
        CustomCrawler crawler = new CustomCrawler(this.config);
        String[] seeds = this.config.getCrawlSeeds();
        Link[] links = new Link[seeds.length];
        int pointer = 0;
        for (String seed : seeds) {
            try {
                URL url = new URL(seed);
                Link link = new Link(url);
                links[pointer] = link;
                pointer++;
            } catch (MalformedURLException ex) {
                Logger.getLogger(CrawlerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        crawler.setRoots(links);
        int maxDepth = config.getMaxDepth();
        if (maxDepth != -1) {
            crawler.setMaxDepth(maxDepth);
        }

        DownloadParameters dp = new DownloadParameters();
        dp.changeObeyRobotExclusion(false);
        int maxPageSize = config.getMaxPages();
        if (maxPageSize != -1) {
            dp.changeMaxPageSize(maxPageSize);
        }
        dp.changeUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0)");
        crawler.setDownloadParameters(dp);
        crawler.setDomain(config.getDomain());
        crawler.setLinkType(config.getLinkType());
        
        crawler.run();
    }
}
