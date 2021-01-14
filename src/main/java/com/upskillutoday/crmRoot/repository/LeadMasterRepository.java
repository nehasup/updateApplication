package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;


public interface LeadMasterRepository {
	public LeadMaster findByEmail(String email);
	
	public boolean insertLeadRepository(LeadMaster leadMaster);

	List getAllLeadListDao();

	LeadMaster getRecordByStudentIdDao(LeadMaster leadMaster);

	boolean updateLeadRepository(LeadMaster leadMaster);

	public List<LeadMaster> getAllLeadByassignFlag();

	//List getDailyLeadDao(LeadMaster leadMaster);

	



	
}
