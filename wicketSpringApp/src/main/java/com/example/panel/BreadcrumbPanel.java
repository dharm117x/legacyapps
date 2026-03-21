package com.example.panel;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.example.config.MenuItem;

public class BreadcrumbPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public BreadcrumbPanel(String id, IModel<List<MenuItem>> crumbsModel) {
		super(id);

		// Pass the model directly to the ListView
		add(new ListView<MenuItem>("crumbs", crumbsModel) {
			@Override
			protected void populateItem(ListItem<MenuItem> item) {
				MenuItem crumb = item.getModelObject();

				// Get the current list from the model to check size
				List<MenuItem> list = getModelObject();
				boolean isLast = item.getIndex() == (list.size() - 1);

				BookmarkablePageLink<Void> link = new BookmarkablePageLink<>("link", crumb.getPageClass());
				link.add(new Label("label", crumb.getLabel()));
				item.add(link);

				item.add(AttributeModifier.append("class", "breadcrumb-item"));

				if (isLast) {
					item.add(AttributeModifier.append("class", "active"));
					link.setEnabled(false);
					link.add(AttributeModifier.append("aria-current", "page"));
				}
			}
		});
	}
}