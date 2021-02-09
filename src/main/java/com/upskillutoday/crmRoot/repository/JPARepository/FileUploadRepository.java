package com.upskillutoday.crmRoot.repository.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillutoday.crmRoot.model.LeadMaster;
import org.springframework.data.jpa.repository.Query;

public interface FileUploadRepository extends JpaRepository<LeadMaster, Long> {
	@Query("select lm from LeadMaster as lm where lm.studentName = :studentName and lm.contactNo = :contactNo and lm.emailId = :emailId and lm.deletedFlag = :b")
	LeadMaster findByStudentNameAndContactNoAndEmailIdAndDeletedFlag(String studentName, String contactNo, String emailId, boolean b);
}
