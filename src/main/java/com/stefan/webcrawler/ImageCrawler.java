package com.stefan.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ImageCrawler {
    private static final Logger log = LoggerFactory.getLogger(ImageCrawler.class);

    public void getImageUrls(String pageUrl) {
        log.info("Getting image urls from page " + pageUrl);
        try {
            Document document = Jsoup.connect(pageUrl).get();
            Elements images = document.select("img[src]");

            for (Element image : images) {
                String src = image.attr("abs:src");
                log.info("Saving image URL to cache: " + src);
                ImageUrlCache.addImageUrl(pageUrl, src);
            }

        } catch (IOException e) {
            log.error("An error occurred for url " + pageUrl, e);
        }
    }
}
