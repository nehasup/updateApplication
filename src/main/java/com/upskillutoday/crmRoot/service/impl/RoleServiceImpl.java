package com.upskillutoday.crmRoot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.RoleDto;
import com.upskillutoday.crmRoot.model.RoleMaster;
import com.upskillutoday.crmRoot.repository.RoleRepository;
import com.upskillutoday.crmRoot.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public boolean insertRole(RoleDto roleDto) {
        
		RoleMaster role=new RoleMaster();


        role.setRoleName(roleDto.getRoleName());
        role.setDepartment(roleDto.getDepartment());
        Date date = new Date();
        role.setUpdatedOn(date);
        role.setDeletedFlag(true);

        System.out.println("d11d");
        boolean flag=roleRepository.insertRoleDao(role);
        System.out.println("dd"+flag);
        return flag;
    }

	@Override
	public List getAllRoleService() {
		  List  list=roleRepository.getRoleListDao();
		return list;
	}

    @Override
    public Long roleIdByEmpId(Long empId) {
        return null;
    }

}
