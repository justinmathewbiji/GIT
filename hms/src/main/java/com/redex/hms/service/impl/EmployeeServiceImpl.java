package com.redex.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redex.hms.configs.core.service.impl.GenericServiceImpl;
import com.redex.hms.dao.EmployeeDao;
import com.redex.hms.models.Employee;
import com.redex.hms.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Integer> implements EmployeeService {


	private EmployeeDao employeeDao;



	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}


	@Autowired
	public void setEmployeeDao(final EmployeeDao employeeDao) {
		System.out.println("@@@@@@@@ service setter");
		super.genericDao=employeeDao;
		this.employeeDao = employeeDao;
	}


}
