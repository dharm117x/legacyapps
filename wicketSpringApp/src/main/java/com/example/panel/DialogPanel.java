package com.example.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class DialogPanel extends Panel {
    private static final long serialVersionUID = 1L;
    public static final String CONTENT_ID = "content";
    private final WebMarkupContainer window;

    public DialogPanel(String id) {
        super(id);
        setOutputMarkupId(true);

        // This is the outer wrapper that Wicket will refresh via AJAX
        window = new WebMarkupContainer("window");
        window.setOutputMarkupPlaceholderTag(true); 
        window.setVisible(false); // Modal is hidden on page load
        add(window);

        // Initial empty placeholder for the content
        window.add(new WebMarkupContainer(CONTENT_ID));
    }

    /**
     * Replaces the internal content with the provided Panel and shows the modal.
     */
    public void open(Panel panel, AjaxRequestTarget target) {
        // Validation to ensure the child panel uses the reserved ID
        if (!CONTENT_ID.equals(panel.getId())) {
            throw new IllegalArgumentException("Child panel must have ID: " + CONTENT_ID);
        }
        
        window.addOrReplace(panel); 
        window.setVisible(true);
        target.add(this); // Refresh the entire DialogPanel component
    }

    /**
     * Hides the modal and clears visibility.
     */
    public void close(AjaxRequestTarget target) {
        window.setVisible(false);
        target.add(this);
    }
}
