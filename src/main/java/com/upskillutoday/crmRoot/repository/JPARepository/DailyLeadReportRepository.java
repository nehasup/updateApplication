package com.upskillutoday.crmRoot.repository.JPARepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.response.LeadResponseDto;

public interface DailyLeadReportRepository extends JpaRepository<LeadMaster, Long> {
	
	public List<LeadMaster> findByUpdatedOn(Date updatedOn);

}
