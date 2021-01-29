package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.response.LeadResponseDto;


public interface LeadMasterRepository {
	LeadMaster findByEmail(String email);
	boolean insertLeadRepository(LeadMaster leadMaster);
	LeadMaster getRecordByStudentIdDao(LeadMaster leadMaster);
	void updateLeadRepository(LeadMaster leadMaster);
	List<LeadMaster> getAllLeadByassignFlag();
	List<LeadResponseDto> getAllLeadForMe();
	List getLeadsByRemark(Long remarkId);
	List getAllLeadListByquery(Long userId);
	List getAllUnassignedNewLeads();
	LeadMaster getLeadByStudentId(Long stduentId);
	List getAllLeadFromStatusByEmp(Long remarkId, Long userId);
	List getLeadMasterByNameEmailContactDeleteFlag(String name, String contact, String email, boolean flag);
}
