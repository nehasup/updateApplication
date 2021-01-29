package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.model.EmployeeMaster;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeRepository {
	boolean insertEmployeeDao(EmployeeMaster employee);
	List getAllEmpListDao();
	EmployeeMaster getRecordByEmployeeIdDao(EmployeeMaster employee);
	boolean updateEmployeeRepository(EmployeeMaster employee);
	Long getEmployeeIdByUserId(Long userId);
	EmployeeMaster getEmployeeByUserId(Long userId);
	EmployeeMaster getEmployeeByEmpId(Long empId);
	List getAllVerificationCounsellor();
	Long getEmployeeFromCategory(Long studentId);
	Long getEmployeeByCategory(Long cat);
	List getVerifiactionAndAdmissionConusellor();
	EmployeeMaster getVerificationConsellorByCategory(Long catId);
	List getCatsByEmpId(Long empId);
}
