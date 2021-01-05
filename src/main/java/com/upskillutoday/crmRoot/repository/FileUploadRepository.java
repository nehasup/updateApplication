package com.upskillutoday.crmRoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillutoday.crmRoot.model.LeadMaster;

public interface FileUploadRepository extends JpaRepository<LeadMaster, Long> {

	LeadMaster findByStudentNameAndContactNoAndEmailIdAndDeletedFlag(String studentName, String contactNo,
			String emailId, boolean b);

	
	
}
