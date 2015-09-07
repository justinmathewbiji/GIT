package com.redex.hms.forms.menu;

import javax.servlet.http.HttpServletRequest;

import com.redex.hms.beans.MenuDetailsBean;
import com.redex.hms.utils.RedexUtils;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public  class RedexViewItem {
	//DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true);

	private final String viewName;
	/*private final Class<? extends View> viewClass;*/
	private final View viewClass;
	private final Resource icon;
	private final boolean stateful;
	private final String caption;

	private RedexViewItem(final String viewName,
			final Class<? extends View> viewClass, final Resource icon,
			final boolean stateful) {
		this.viewName = viewName;
		this.viewClass = null;
		this.icon = icon;
		this.stateful = stateful;
		this.caption=viewName;
	}

	public RedexViewItem(final MenuDetailsBean menuDetailsBean,final HttpServletRequest httpServletRequest) {
		this.caption=menuDetailsBean.getMenuCaption();
		this.icon=null;
		this.stateful=false;
		this.viewClass=RedexUtils.getView(menuDetailsBean.getClassName(), httpServletRequest);
		this.viewName=menuDetailsBean.getViewId();

	}


	public boolean isStateful() {
		return stateful;
	}

	public String getViewName() {
		return viewName;
	}



	public View getViewClass() {
		return viewClass;
	}

	public Resource getIcon() {
		return icon;
	}



	/*public static RedexMenuItem getByViewName(final String viewName) {
		RedexMenuItem result = null;
		for (RedexMenuItem viewType : values()) {
			if (viewType.getViewName().equals(viewName)) {
				result = viewType;
				break;
			}
		}
		return result;
	}*/

	public String getCaption() {
		return caption;
	}

	private Class<? extends View> getClassName(final String className,final HttpServletRequest request){
		try {
			Class<?> clazz = Class.forName(className);
			//Constructor<?> ctor = clazz.getConstructor(HttpServletRequest.class);
			//Object object = ctor.newInstance(new Object[] { request });
			return ( Class<? extends View>) clazz;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
