package com.example.config;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;

public class MenuItem implements Serializable {

    private String label;
    private Class<? extends WebPage> pageClass;

    public MenuItem(String label, Class<? extends WebPage> pageClass) {
        this.label = label;
        this.pageClass = pageClass;
    }

    public String getLabel() {
        return label;
    }

    public Class<? extends WebPage> getPageClass() {
        return pageClass;
    }
}