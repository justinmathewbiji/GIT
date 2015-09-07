package com.redex.hms.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redex.hms.beans.MenuDetailsBean;
import com.redex.hms.beans.convertors.ModelToBeanConvertor;
import com.redex.hms.business.service.MenuDetailsBusinessService;
import com.redex.hms.models.MenuDetails;
import com.redex.hms.service.MenuDetailsService;

@Service
public class MenuDetailsBusinessServiceImpl implements MenuDetailsBusinessService{

	@Autowired
	private MenuDetailsService menuDetailsService;

	@Override
	public List<MenuDetailsBean> getMenuDetailsForUser(final String userId) {
		try {
			List<MenuDetails> menuDetails=menuDetailsService.readAll();
			List<MenuDetailsBean> menuDetailsBeans=new ArrayList<MenuDetailsBean>();
			for(MenuDetails menuDetail: menuDetails){
				menuDetailsBeans.add(ModelToBeanConvertor.convertMenuDOToBean(menuDetail));
			}
			return menuDetailsBeans;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
