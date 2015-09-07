package com.redex.hms.forms;

import com.redex.hms.forms.configs.DashboardNavigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/*
 * Dashboard MainView is a simple HorizontalLayout that wraps the menu on the
 * left and creates a simple container for the navigator on the right.
 */
@SuppressWarnings("serial")
public class MainView extends HorizontalLayout {

	public MainView() {
		setSizeFull();
		addStyleName("mainview");

		//This is menu in the left side. It is not included in the navigator. This is how we make a common menu for all pages.
		addComponent(new DashboardMenu());

		ComponentContainer content = new CssLayout();
		content.addStyleName("view-content");
		content.setSizeFull();
		addComponent(content);
		setExpandRatio(content, 1.0f);

		new DashboardNavigator(content);
	}
}
