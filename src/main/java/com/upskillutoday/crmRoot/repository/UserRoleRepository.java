package com.upskillutoday.crmRoot.repository;

import com.upskillutoday.crmRoot.model.EmployeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
		UserRole findByUsersAndDeletedFlag(UserMaster userMaster, boolean b);

		//UserRole findByUserRole(Long userId);
		@Query("select u from UserRole u inner join u.users us where us.userId=:userId")
		UserRole findByuserRole(Long userId);



}
