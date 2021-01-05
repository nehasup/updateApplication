package com.upskillutoday.crmRoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;

public interface LeadJpaMasterRepository extends JpaRepository<LeadMaster, Long> {

	List<LeadMaster> findByCategoryMasterAndDeletedFlag(CategoryMaster category, boolean b);
	
	public LeadMaster findByStudentNameAndContactNoAndCourseNameAndDeletedFlag(String string, String string2,
			String string3, boolean b);

	List<LeadMaster> findByCategoryMasterAndRemarkMasterAndDeletedFlag(CategoryMaster category,
			RemarkMaster remarkMaster, boolean b);

	
	

	
}
