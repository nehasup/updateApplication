package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.model.EmployeeMaster;

public interface EmployeeRepository {

	boolean insertEmployeeDao(EmployeeMaster employee);

	List getAllEmpListDao();

	EmployeeMaster getRecordByEmployeeIdDao(EmployeeMaster employee);

	boolean updateEmployeeRepository(EmployeeMaster employee);

	Long getEmployeeIdByUserId(Long userId);

	EmployeeMaster getEmployeeByUserId(Long userId);
	
}
