package com.redex.hms.business.service;

import java.util.List;

import com.redex.hms.beans.MenuDetailsBean;

public interface MenuDetailsBusinessService {


	public List<MenuDetailsBean> getMenuDetailsForUser(String userId);
}
