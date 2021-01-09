package com.stefan.webcrawler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ImageUrlCache {
    private static final Map<String, List<String>> imageUrls = new ConcurrentHashMap<>();

    public static void addImageUrl(String pageUrl, String imageUrl) {
        imageUrls.putIfAbsent(pageUrl, new ArrayList<>());
        imageUrls.get(pageUrl).add(imageUrl);
    }

    public static Map<String, List<String>> getImageUrls() {
        return imageUrls;
    }
}
