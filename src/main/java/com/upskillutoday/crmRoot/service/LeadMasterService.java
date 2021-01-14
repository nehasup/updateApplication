package com.upskillutoday.crmRoot.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.dto.EmployeeLeadDto;
import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.request.DailyLeadReportDto;

public interface LeadMasterService {

	public boolean insertLeadService(LeadMasterDto leadMasterDto);

	List getAllLeadRecordService();

	LeadMasterDto getRecordByStudentIdService(LeadMasterDto leadMasterDto);

	boolean updateLeadService(LeadMasterDto leadMasterDto);

	public List<LeadMasterDto> getAllLeadListCategoryWiseService(EmployeeMaster employeeMaster);

	List<LeadMasterDto> getCategoryWiseandverifyLeadService(EmployeeMaster employeeMaster);

	List<LeadMasterDto> getAllAssignLeadListService(EmployeeMaster employeeMaster);

	public List<LeadMaster> getAllLeadByAssignFlag();

	//public List getDailyLeadReportService(DailyLeadReportDto dailyLeadReportDto) throws ParseException;

	

	//public boolean insertEmpLeadService(EmployeeLeadDto employeeLeadDto);

	//EmployeeDto getRecordByemployeIdService(EmployeeDto employeeDto);


}
