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
	List getLeadsByRemark(Long remarkId);
	List<LeadMasterDto> getAllLeadListByquery(Long userId);
	List getAllUnassignedNewLeads();
	LeadMaster getLeadByStudentId(Long stduentId);
	List getAllLeadFromStatusByEmp(Long remarkId, Long userId);
}
