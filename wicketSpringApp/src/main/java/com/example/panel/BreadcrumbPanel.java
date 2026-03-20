package com.example.panel;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.example.config.MenuItem;

public class BreadcrumbPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public BreadcrumbPanel(String id, List<MenuItem> crumbs) {
        super(id);

        add(new ListView<MenuItem>("crumbs", crumbs) {
            @Override
            protected void populateItem(ListItem<MenuItem> item) {
            	MenuItem crumb = item.getModelObject();
                boolean isLast = item.getIndex() == (crumbs.size() - 1);

                // Create link
                BookmarkablePageLink<Void> link = new BookmarkablePageLink<>("link", crumb.getPageClass());
                link.add(new Label("label", crumb.getLabel()));
                item.add(link);

                // Bootstrap styling
                item.add(AttributeModifier.append("class", "breadcrumb-item"));
                
                if (isLast) {
                    item.add(AttributeModifier.append("class", "active"));
                    link.setEnabled(false); // Current page isn't clickable
                    link.add(AttributeModifier.append("aria-current", "page"));
                }
            }
        });
    }
}
