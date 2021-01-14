package com.upskillutoday.crmRoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.response.EmpLeadResponseDto;


@Repository
public interface EmpLeadJpaRepository extends JpaRepository<EmpLead, Long>{

	List<EmpLead> findByEmployeeMasterAndDeletedFlag(EmployeeMaster employeeMaster, boolean b);

	List<EmpLead> findByDeletedFlag(boolean b);
	
//	@Query(value = "SELECT emplead FROM EmpLead emplead where emplead.studentId=?1")
//	EmpLead findByLeadMaster(long studentId);

	EmpLead findByLeadMaster(LeadMaster leadMaster);

	
	
//	@Query(value = "SELECT lead FROM LeadMaster lead where lead.deletedFlag=true and lead.assignLeadFlag=false")
//	//List<User> findAllUsers(Sort sort);
//	public List<LeadMaster> findAllAndDeletedFlagAndAssignLeadFlag(boolean b, boolean c);

//	@Query(value = "SELECT emplead FROM EmpLead emplead where emplead.deletedFlag=1")
//	List<EmpLeadResponseDto> getAllAssignEmpLead();



}
