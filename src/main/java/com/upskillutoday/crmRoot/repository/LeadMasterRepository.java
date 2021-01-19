package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.LeadMaster;


public interface LeadMasterRepository {
	LeadMaster findByEmail(String email);
	boolean insertLeadRepository(LeadMaster leadMaster);
	LeadMaster getRecordByStudentIdDao(LeadMaster leadMaster);
	void updateLeadRepository(LeadMaster leadMaster);
	List<LeadMaster> getAllLeadByassignFlag();
	List<LeadMasterDto> getAllLeadForMe();
	List<LeadMasterDto> getAllLeadListByquery(Long userId);
	List getLeadsByRemark(Long remarkId);
}
