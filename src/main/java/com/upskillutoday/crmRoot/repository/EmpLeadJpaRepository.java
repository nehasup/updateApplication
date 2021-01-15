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

	EmpLead findByLeadMaster(LeadMaster leadMaster);

}
