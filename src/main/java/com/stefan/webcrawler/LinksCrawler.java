package com.stefan.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LinksCrawler {
    private static final Logger log = LoggerFactory.getLogger(LinksCrawler.class);
    private final Set<String> links;
    private final int maxDepth;

    public LinksCrawler(int maxDepth) {
        this.maxDepth = maxDepth;
        this.links = new HashSet<>();
    }

    public Set<String> getPageLinks(String url) {
        getPageLinksWithDepth(url, 0);
        return links;
    }

    private void getPageLinksWithDepth(String url, int depth) {
        if ((!links.contains(url) && (depth < maxDepth))) {
            try {
                Document document = Jsoup.connect(url).get();
                log.info("Found link in depth: " + depth + " [" + url + "]");

                depth++;
                links.add(url);

                Elements linksOnPage = document.select("a[href]");

                for (Element page : linksOnPage) {
                    getPageLinksWithDepth(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                log.error("An error occurred for url " + url, e);
            }
        }
    }
}
