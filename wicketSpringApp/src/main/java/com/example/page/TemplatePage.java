package com.example.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

import com.example.config.AppSession;
import com.example.config.MenuItem;
import com.example.panel.BreadcrumbPanel;
import com.example.panel.FooterPanel;
import com.example.panel.HeaderPanel;

public class TemplatePage extends WebPage {

	private static final long serialVersionUID = -7460977228738805491L;
	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;
	protected List<MenuItem> crumbs = new ArrayList<MenuItem>();
	
	public TemplatePage() {
		add(new BreadcrumbPanel("breadcrumb", crumbs));   
		add(headerPanel = new HeaderPanel("headerPanel"));
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