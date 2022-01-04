package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class BeanForInputStream {
    private final Resource resource;

    public BeanForInputStream (@Value("${filename}") String csvName) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        this.resource = resourceLoader.getResource(csvName);
    }

    public Resource getResource() {
        return resource;
    }
}
