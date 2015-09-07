package com.redex.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redex.hms.configs.core.service.impl.GenericServiceImpl;
import com.redex.hms.dao.MenuDetailsDao;
import com.redex.hms.models.MenuDetails;
import com.redex.hms.service.MenuDetailsService;

@Service
@Transactional
public class MenuDetailsServiceImpl extends GenericServiceImpl<MenuDetails, Integer> implements MenuDetailsService {
	private MenuDetailsDao menuDetailsDao;

	public MenuDetailsDao getMenuDetailsDao() {
		return menuDetailsDao;
	}
	@Autowired
	public void setMenuDetailsDao(final MenuDetailsDao menuDetailsDao) {
		this.menuDetailsDao = menuDetailsDao;
		this.genericDao=menuDetailsDao;
	}


}
