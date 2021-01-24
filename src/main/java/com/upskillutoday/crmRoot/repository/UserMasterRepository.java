package com.upskillutoday.crmRoot.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import com.upskillutoday.crmRoot.response.AuthenticationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.security.core.userdetails.User;
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

//	@Query("SELECT new UserDto(us.userName, us.pass) FROM UserMaster as us WHERE us.userName = :username")
//	User getUserByUserName(@Param("username") String username);

  @Query(
      "SELECT new AuthenticationResponse ( us.userId, us.userName, e.employeeId, e.employeeName, rm.roleId, rm.roleName, e.contactNo, e.emailId) \n"
          + "    FROM UserMaster as us \n"
          + "    inner join EmployeeMaster as e on us.userId = e.userMaster.userId \n"
          + "    inner join UserRole as ur on ur.users.userId = us.userId \n"
          + "    inner join RoleMaster as rm on ur.roles.roleId = rm.roleId \n"
          + "    where us.userName = :username")
  AuthenticationResponse getLoginDataFromName(String username);
}
