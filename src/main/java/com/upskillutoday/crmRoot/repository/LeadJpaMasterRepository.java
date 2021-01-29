package com.upskillutoday.crmRoot.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LeadJpaMasterRepository extends JpaRepository<LeadMaster, Long> {

	public LeadMaster findByStudentNameAndContactNoAndCourseNameAndDeletedFlag(String string, String string2,
			String string3, boolean b);

	List<LeadMaster> findByCategoryMasterAndRemarkMasterAndDeletedFlag(CategoryMaster category,
			RemarkMaster remarkMaster, boolean b);

	
	LeadMaster findByStudentIdAndDeletedFlag(long leadId, boolean b);

	//List<LeadMaster> findByCategoryMasterAndDeletedFlagAndAssignLeadFlag(CategoryMaster category, boolean b, boolean c);

	//public LeadMaster findByStudentIdAndDeletedFlagAndAssignLeadFlag(Long studentId, boolean b, boolean c);

	public List<LeadMaster> findByCategoryMasterAndDeletedFlag(CategoryMaster category, boolean b);

	public LeadMaster findByStudentId(Long studentId);

	

	//public List<LeadMaster> findByUpdatedOn(String updatedOn);



//	@Query(value = "SELECT lead FROM LeadMaster lead where lead.deletedFlag=true and lead.assignLeadFlag=false")
//	//List<User> findAllUsers(Sort sort);
//	public List<LeadMaster> findAllAndDeletedFlagAndAssignLeadFlag(boolean b, boolean c);
//
//

	
	

	
}
