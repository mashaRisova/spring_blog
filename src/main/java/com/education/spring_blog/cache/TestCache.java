package com.education.spring_blog.cache;

import com.education.spring_blog.models.Post;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestCache {
    private final Cache<Long, Post> cache;

    public TestCache() {
        this.cache = CacheBuilder.newBuilder()
                .initialCapacity(32)
                .concurrencyLevel(8)
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    public Post get(long id) {
        return cache.getIfPresent(id);
    }
}