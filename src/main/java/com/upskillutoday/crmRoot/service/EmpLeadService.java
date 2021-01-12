package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.response.EmpLeadResponseDto;

public interface EmpLeadService {

	public List<LeadMasterDto> getAllAssignEmpLeadRecordService();

}
