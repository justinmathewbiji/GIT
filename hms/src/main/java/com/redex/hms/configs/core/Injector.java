package com.redex.hms.configs.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class Injector {

	public static void inject(final Component component,final HttpServletRequest request){
		inject(component, getApplicationContext(request));
	}

	private static ApplicationContext getApplicationContext(final HttpServletRequest request){
		HttpSession session=request.getSession(false);
		return WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
	}

	private static void inject(final Component component,final ApplicationContext applicationContext){
		AutowireCapableBeanFactory beanFactory=applicationContext.getAutowireCapableBeanFactory();
		beanFactory.autowireBeanProperties(component, AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT, false);
	}

	public static void inject(final UI ui,final HttpServletRequest request){
		inject(ui, getApplicationContext(request));
	}


	private static void inject(final UI ui,final ApplicationContext applicationContext){
		AutowireCapableBeanFactory beanFactory=applicationContext.getAutowireCapableBeanFactory();
		beanFactory.autowireBeanProperties(ui, AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT, false);
	}
}
