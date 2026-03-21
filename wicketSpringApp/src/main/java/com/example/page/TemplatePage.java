package com.example.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import com.example.config.AppSession;
import com.example.config.MenuItem;
import com.example.page.user.UserRegistrationPage;
import com.example.panel.BreadcrumbPanel;
import com.example.panel.FooterPanel;
import com.example.panel.HeaderPanel;

public abstract class TemplatePage extends WebPage {

	private static final long serialVersionUID = -7460977228738805491L;
	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;
	protected List<MenuItem> crumbs = new ArrayList<MenuItem>();

	public TemplatePage() {
		add(new Label("title", getTitle()));
		add(new BreadcrumbPanel("breadcrumb", crumbs));
		add(headerPanel = new HeaderPanel("headerPanel"));
		add(footerPanel = new FooterPanel("footerPanel"));
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		setStatelessHint(false);
		AppSession session = (AppSession) getSession();
		boolean isPublicPage = getClass().equals(LoginPage.class) || getClass().equals(UserRegistrationPage.class);
		if (!session.isSignedIn() && !isPublicPage) {
			throw new RestartResponseAtInterceptPageException(LoginPage.class);
		}
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
	}

	public abstract IModel<?> getTitle();


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