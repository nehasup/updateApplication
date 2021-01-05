package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.RoleDto;

public interface RoleService {

	boolean insertRole(RoleDto roleDto);

	List getAllRoleService();

}
