package com.example.panel;

import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.navigation.paging.IPageable;

public class PagingPanel extends AjaxPagingNavigator {
    private static final long serialVersionUID = 1L;

    public PagingPanel(String id, IPageable pageable) {
        super(id, pageable);
    }
}
