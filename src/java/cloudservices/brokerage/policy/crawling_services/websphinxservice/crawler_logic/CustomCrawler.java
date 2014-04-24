/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudservices.brokerage.policy.crawling_services.websphinxservice.crawler_logic;

import cloudservices.brokerage.policy.crawling_services.websphinxservice.configuration.WebsphinxConfig;
import cloudservices.brokerage.policy.crawling_services.websphinxservice.crawler_logic.repository.URLRepositoryService;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import websphinx.Crawler;
import websphinx.Page;

/**
 *
 * @author Arash Khodadadi http://www.arashkhodadadi.com/
 */
public class CustomCrawler extends Crawler {

    private final static Logger LOGGER = Logger.getLogger(CustomCrawler.class
            .getName());
    private WebsphinxConfig config;

    public CustomCrawler(WebsphinxConfig config) {
        super();
        this.config = config;
    }

    @Override
    public void visit(Page page) {
        String href = page.getURL().toString();
        String msg = "Try to Visit: " + href;
        LOGGER.log(Level.FINER, msg);
        doVisit(page);
        try {
            Thread.sleep(config.getPolitenessDelay());
        } catch (InterruptedException e) {
        }
    }

    public synchronized void doVisit(Page page) {
        URL url = page.getURL();
        if (url != null) {
            String href = page.getURL().toString();
            String msg = "Visiting: " + url;
            LOGGER.log(Level.FINE, msg);
            URLRepositoryService.getInstance().getRepository().put(href);
        }
    }
}
