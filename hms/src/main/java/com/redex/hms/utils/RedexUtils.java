package com.redex.hms.utils;

import java.lang.reflect.Constructor;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;

public class RedexUtils {

	public static HttpServletRequest getHttpServletRequest(final VaadinRequest vaadinRequest){
		HttpServletRequest request=null;
		if(vaadinRequest!=null){
			VaadinServletRequest vsRequest = (VaadinServletRequest)vaadinRequest;
			request = vsRequest.getHttpServletRequest();
		}
		return request;
	}

	public static Class<? extends View> getClassType(final String className,final HttpServletRequest request){
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

	public static View getView(final String className,final HttpServletRequest request){
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> ctor = clazz.getConstructor(HttpServletRequest.class);
			Object object = ctor.newInstance(new Object[] { request });
			return (View) object;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
