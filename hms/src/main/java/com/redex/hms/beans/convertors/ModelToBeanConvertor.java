package com.redex.hms.beans.convertors;

import com.redex.hms.beans.MenuDetailsBean;
import com.redex.hms.models.MenuDetails;

public class ModelToBeanConvertor {

	public static MenuDetailsBean convertMenuDOToBean(final MenuDetails menuDetails){
		MenuDetailsBean menuDetailsBean=null;
		if(menuDetails!=null){
			menuDetailsBean=new MenuDetailsBean();
			menuDetailsBean.setClassName(menuDetails.getClassName());
			menuDetailsBean.setIcon(menuDetails.getIcon());
			menuDetailsBean.setMenuCaption(menuDetails.getMenuCaption());
			menuDetailsBean.setMenuId(menuDetails.getMenuId());
			menuDetailsBean.setParentId(menuDetails.getParentId());
			menuDetailsBean.setViewId(menuDetails.getViewId());
		}
		return menuDetailsBean;
	}

}
