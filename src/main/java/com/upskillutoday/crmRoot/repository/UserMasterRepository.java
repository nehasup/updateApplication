package com.upskillutoday.crmRoot.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.UserMaster;


@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {

	UserMaster findAllByUserName(String userName);
	//UserMaster loadUserMasterByUsername(String userName);
	UserMaster findByUserName(String userName);
	Boolean existsByUserName(String userName);
	UserMaster findAllByUserIdAndDeletedFlag(Long userId, boolean b);

	//Boolean existsByEmail(String email);
	

}
