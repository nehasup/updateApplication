package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.EmployeeMaster;

public interface LeadMasterService {

	public boolean insertLeadService(LeadMasterDto leadMasterDto);

	List getAllLeadRecordService();

	LeadMasterDto getRecordByStudentIdService(LeadMasterDto leadMasterDto);

	boolean updateLeadService(LeadMasterDto leadMasterDto);

	public List<LeadMasterDto> getAllLeadListCategoryWiseService(EmployeeMaster employeeMaster);

	List<LeadMasterDto> getCategoryWiseandverifyLeadService(EmployeeMaster employeeMaster);

	//EmployeeDto getRecordByemployeIdService(EmployeeDto employeeDto);


}
