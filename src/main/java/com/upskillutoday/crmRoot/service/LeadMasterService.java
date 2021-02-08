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
import reactor.core.publisher.Flux;

public interface LeadMasterService {

	boolean insertLeadService(LeadMasterDto leadMasterDto);
	List getAllLeadRecordService();
	Flux getAllLeadRecordServiceFlux();
	LeadMasterDto getRecordByStudentIdService(LeadMasterDto leadMasterDto);
	boolean updateLeadService(Long userId, LeadMasterDto leadMasterDto);
	List<LeadMasterDto> getCategoryWiseandverifyLeadService(EmployeeMaster employeeMaster);
	List getAllAssignLeadListService(EmployeeMaster employeeMaster);
	List<LeadMaster> getAllLeadByAssignFlag();
	String assignUnverifiedLeadToVerifiers();
	LeadMaster getLeadByStudentId(Long stduentId);
}
