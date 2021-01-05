package com.upskillutoday.crmRoot.repository;

import java.util.List;
import java.util.Optional;

import com.upskillutoday.crmRoot.model.RoleMaster;

public interface RoleRepository {

	boolean insertRoleDao(RoleMaster role);

	List getRoleListDao();

	RoleMaster getroleByid(Long roleId);
	
	Optional<RoleMaster> findByRoleName(String roleName);

}
