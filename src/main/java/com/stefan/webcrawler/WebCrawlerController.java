package com.stefan.webcrawler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RestController
public class WebCrawlerController {
    private static final String CRAWL_URL = "url";
    private static final String CRAWL_DEPTH = "depth";

    @PostMapping("/crawl")
    public void crawl(@RequestBody Map<String, Object> payload) {
        String url = (String) payload.get(CRAWL_URL);
        Integer depth = (Integer) payload.get(CRAWL_DEPTH);

        LinksCrawler linksCrawler = new LinksCrawler(depth);
        Set<String> pageLinks = linksCrawler.getPageLinks(url);

        ImageCrawler imageCrawler = new ImageCrawler();
        pageLinks.parallelStream().forEach(imageCrawler::getImageUrls);
    }

    @GetMapping("/inventory")
    public Map<String, List<String>> inventory() {
        return ImageUrlCache.getImageUrls();
    }
}
