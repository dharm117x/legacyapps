package com.example.panel.order;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.example.model.Order;
import com.example.panel.DialogPanel;
import com.example.service.OrderService;

public abstract class OrderEditPanel extends Panel {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private OrderService service;
	
	public OrderEditPanel(String id, IModel<Order> model) {
		
		 // 1. Set the CompoundPropertyModel on the Panel
	    super(id, new CompoundPropertyModel<>(model));

	    // 2. Explicitly pass 'null' or the model to the Form 
	    // so it doesn't look for a property named "form"
	    Form<Order> form = new Form<>("form", model); 
	    add(form);

	    // Now these will correctly map to order.getCustomer() and order.getTotal()
	    form.add(new TextField<String>("customer").setRequired(true));
	    form.add(new NumberTextField<Double>("total"));
	    
		form.add(new AjaxButton("save") {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				service.save(form.getModelObject());
				findParent(DialogPanel.class).close(target);
				onSave(target); // Call the abstract method to refresh the page
			}
		});
		// Inside the constructor, after the save button:
		form.add(new AjaxLink<Void>("cancel") {
		    @Override
		    public void onClick(AjaxRequestTarget target) {
		        findParent(DialogPanel.class).close(target);
		    }
		});
	}

	protected abstract void onSave(AjaxRequestTarget target);

}
