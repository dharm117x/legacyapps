package com.example.page.order;

import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.markup.html.form.palette.theme.DefaultTheme;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import com.example.model.Order;
import com.example.page.TemplatePage;
import com.example.panel.DialogPanel;
import com.example.panel.order.OrderEditPanel;
import com.example.service.OrderService;

@MountPath("/orders")
public class OrderPage extends TemplatePage {

	@SpringBean
	private OrderService service;

	private static final long serialVersionUID = 1L;

	private String searchFilter;
	private final WebMarkupContainer tableContainer;

	public OrderPage() {
		// 1. Setup Modal
		final DialogPanel modal = new DialogPanel("modal");
		modal.add(new DefaultTheme());
		add(modal);

		// 2. Search Form
		Form<Void> searchForm = new Form<>("searchForm");
		searchForm.add(new TextField<>("searchField", new PropertyModel<>(this, "searchFilter")));
		searchForm.add(new AjaxButton("searchBtn") {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				target.add(tableContainer); // Refresh table on search
			}
		});
		add(searchForm);

		// 3. Table Container (Essential for AJAX refresh)
		tableContainer = new WebMarkupContainer("tableContainer");
		tableContainer.setOutputMarkupId(true);
		add(tableContainer);

		// 4. Data Provider
		IDataProvider<Order> provider = new SortableDataProvider<Order, String>() {
			@Override
			public Iterator<Order> iterator(long first, long count) {
				return service.findOrders(searchFilter, first, count).iterator();
			}

			@Override
			public long size() {
				return service.countOrders(searchFilter);
			}

			@Override
			public IModel<Order> model(Order object) {
				return Model.of(object);
			}
		};

		// 5. DataView
		DataView<Order> dataView = new DataView<Order>("orderList", provider, 3) {
			@Override
			protected void populateItem(Item<Order> item) {
				item.add(new Label("id", new PropertyModel<>(item.getModel(), "id")));
				item.add(new Label("customer", new PropertyModel<>(item.getModel(), "customer")));
				item.add(new Label("total", new PropertyModel<>(item.getModel(), "total")));

				item.add(new AjaxLink<Void>("edit") {
					@Override
					public void onClick(AjaxRequestTarget target) {
						modal.open(new OrderEditPanel(DialogPanel.CONTENT_ID, item.getModel()) {
							@Override
							protected void onSave(AjaxRequestTarget target) {
								target.add(tableContainer); // Refresh list after edit
							}
						}, target);
					}
				});
			}
		};
		tableContainer.add(dataView);
		tableContainer.add(new AjaxPagingNavigator("navigator", dataView));

		// 6. Add New Button
		add(new AjaxLink<Void>("addNew") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.open(new OrderEditPanel(DialogPanel.CONTENT_ID, Model.of(new Order())) {
					@Override
					protected void onSave(AjaxRequestTarget target) {
						target.add(tableContainer);
					}
				}, target);
			}
		});
	}

	@Override
	public IModel<?> getTitle() {
		return Model.of("Order Management");
	}
}
