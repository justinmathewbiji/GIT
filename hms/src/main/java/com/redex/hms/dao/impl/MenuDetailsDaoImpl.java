package com.redex.hms.dao.impl;

import org.springframework.stereotype.Repository;

import com.redex.hms.configs.core.dao.impl.GenericDaoImpl;
import com.redex.hms.dao.MenuDetailsDao;
import com.redex.hms.models.MenuDetails;

@Repository
public class MenuDetailsDaoImpl extends GenericDaoImpl<MenuDetails, Integer> implements MenuDetailsDao{

	public MenuDetailsDaoImpl() {
		super(MenuDetails.class);
	}

}
