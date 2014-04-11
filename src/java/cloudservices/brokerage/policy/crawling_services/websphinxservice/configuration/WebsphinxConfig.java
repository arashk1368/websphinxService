/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudservices.brokerage.policy.crawling_services.websphinxservice.configuration;

import websphinx.Crawler;

/**
 *
 * @author Arash Khodadadi http://www.arashkhodadadi.com/
 */
public class WebsphinxConfig {

    private final static int DEFAULT_MAX_DEPTH = -1; //unlimited
    private final static int DEFAULT_MAX_PAGES = -1; //unlimited
    private final static String[] DEFAULT_DOMAIN = Crawler.WEB;
    private final static String[] DEFAULT_LINK_TYPE = Crawler.ALL_LINKS;
    private final static int DEFAULT_POLITENESS_DELAY = 500; //ms
    private int maxDepth;
    private int maxPages;
    private String[] crawlSeeds;
    private String[] domain;
    private String[] linkType;
    private int politenessDelay;

    public WebsphinxConfig() {
        this.maxDepth = DEFAULT_MAX_DEPTH;
        this.maxPages = DEFAULT_MAX_PAGES;
        this.domain = DEFAULT_DOMAIN;
        this.linkType = DEFAULT_LINK_TYPE;
        this.politenessDelay=DEFAULT_POLITENESS_DELAY;
    }

    public WebsphinxConfig(String[] crawlSeeds) {
        this();
        this.crawlSeeds = crawlSeeds;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public String[] getCrawlSeeds() {
        return crawlSeeds;
    }

    public void setCrawlSeeds(String[] crawlSeeds) {
        this.crawlSeeds = crawlSeeds;
    }

    public String[] getDomain() {
        return domain;
    }

    public void setDomain(String[] domain) {
        this.domain = domain;
    }

    public String[] getLinkType() {
        return linkType;
    }

    public void setLinkType(String[] linkType) {
        this.linkType = linkType;
    }

    public int getPolitenessDelay() {
        return politenessDelay;
    }

    public void setPolitenessDelay(int politenessDelay) {
        this.politenessDelay = politenessDelay;
    }
    
    
}
