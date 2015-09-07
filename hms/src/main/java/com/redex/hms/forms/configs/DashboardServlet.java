package com.redex.hms.forms.configs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.redex.hms.forms.DashboardUI;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/*", name = "DashboardServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = DashboardUI.class, productionMode = false)
public class DashboardServlet extends VaadinServlet {

	@Override
	protected final void servletInitialized() throws ServletException {
		super.servletInitialized();
		getService().addSessionInitListener(new DashboardSessionInitListener());
	}
}