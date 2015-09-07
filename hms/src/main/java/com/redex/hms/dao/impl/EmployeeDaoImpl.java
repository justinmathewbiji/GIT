package com.redex.hms.dao.impl;

import org.springframework.stereotype.Repository;

import com.redex.hms.configs.core.dao.impl.GenericDaoImpl;
import com.redex.hms.dao.EmployeeDao;
import com.redex.hms.models.Employee;

@SuppressWarnings("unchecked")
@Repository("employeeDao")
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Integer> implements EmployeeDao {



	public EmployeeDaoImpl() {
		super(Employee.class);
	}

}
