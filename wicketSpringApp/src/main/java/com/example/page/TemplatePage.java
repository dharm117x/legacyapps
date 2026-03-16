package com.example.page;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;

import com.example.config.AppSession;
import com.example.panel.FooterPanel;
import com.example.panel.HeaderPanel;
import com.example.panel.MenuPanel;

public class TemplatePage extends WebPage {

	private static final long serialVersionUID = -7460977228738805491L;
	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;

	public TemplatePage() {
		add(headerPanel = new HeaderPanel("headerPanel"));
		add(menuPanel = new MenuPanel("menuPanel"));
		add(footerPanel = new FooterPanel("footerPanel"));
	}

	@Override
	protected void onConfigure() {
	    super.onConfigure();
	    setStatelessHint(false);
	    AppSession session = (AppSession) getSession();
	    // If not signed in AND we aren't already on the LoginPage
	    if (!session.isSignedIn() && !getClass().equals(LoginPage.class)) {
	        throw new RestartResponseAtInterceptPageException(LoginPage.class);
	    }
	}

	
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(new PackageResourceReference(getClass(), "style.css")));
    }
    
	public Component getHeaderPanel() {
		return headerPanel;
	}

	public void setHeaderPanel(Component headerPanel) {
		this.headerPanel = headerPanel;
	}

	public Component getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(Component menuPanel) {
		this.menuPanel = menuPanel;
	}

	public Component getFooterPanel() {
		return footerPanel;
	}

	public void setFooterPanel(Component footerPanel) {
		this.footerPanel = footerPanel;
	}

}