package com.upskillutoday.crmRoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.UserMaster;


public interface EmployeeJpaRepository extends JpaRepository<EmployeeMaster, Long> {

	EmployeeMaster findByUserMaster(UserMaster userMaster);

	//EmployeeMaster findByUserMaster(Long userId);

}
