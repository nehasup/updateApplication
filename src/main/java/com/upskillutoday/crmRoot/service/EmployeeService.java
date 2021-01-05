package com.upskillutoday.crmRoot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.request.EmpLoginReqDto;
import com.upskillutoday.crmRoot.response.EmpLoginResDto;

public interface EmployeeService {

	boolean insertEmployeeService(EmployeeDto employeeDto) throws Exception;

	List getAllEmpRecordService();

	EmployeeDto getRecordByEmpIdService(EmployeeDto employeeDto);

	boolean updateEmployeeService(EmployeeDto employeeDto);

	EmpLoginResDto login(EmpLoginReqDto empLoginReqDto, HttpServletRequest request);

}
