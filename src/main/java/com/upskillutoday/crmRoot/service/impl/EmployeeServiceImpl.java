package com.upskillutoday.crmRoot.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.common.LoginData;
import com.upskillutoday.crmRoot.common.WebSession;
import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.RoleMaster;
import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.model.UserRole;
import com.upskillutoday.crmRoot.repository.CategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.EmployeeJpaRepository;
import com.upskillutoday.crmRoot.repository.EmployeeRepository;
import com.upskillutoday.crmRoot.repository.RoleRepository;
import com.upskillutoday.crmRoot.repository.SubCategoryRepository;
import com.upskillutoday.crmRoot.repository.UserMasterRepository;
import com.upskillutoday.crmRoot.repository.UserRoleRepository;
import com.upskillutoday.crmRoot.request.EmpLoginReqDto;
import com.upskillutoday.crmRoot.response.EmpLoginResDto;

import com.upskillutoday.crmRoot.service.EmployeeService;
import com.upskillutoday.crmRoot.util.SessionUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	CategoryJpaRepository categoryJpaRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	EmployeeJpaRepository emplJpaRepository;
	


	@Override
	public boolean insertEmployeeService(EmployeeDto employeeDto) throws Exception {
		
		//save userpassword and pass
		//user master
		
		
		// UserMaster user =userMasterRepository.findAllByUserName(employeeDto.getUserName());
		UserMaster user = new UserMaster();
		 user.setUserName(employeeDto.getUserName());
		 user.setPass(employeeDto.getPass());
		 user.setUpdatedOn(new Date());
		 user.setDeletedFlag(true);
		 
		 userMasterRepository.save(user);
		 
		 //user role
		 UserRole userRole= new UserRole();

		
		 //role master
		 RoleMaster roleMaster = roleRepository.getroleByid(employeeDto.getRoleId());
		 userRole.setRoles(roleMaster);
		 userRole.setUsers(user);
		 userRole.setUpdatedOn(new Date());
		 userRole.setDeletedFlag(true);
		 userRoleRepository.save(userRole);
		 
		
		//employee master
		EmployeeMaster employee = new EmployeeMaster();		
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setAddress(employeeDto.getAddress());
		employee.setBirthDate(employeeDto.getBirthDate());
	   	employee.setContactNo(employeeDto.getContactNo());
		employee.setEmailId(employeeDto.getEmailId());
		employee.setGender(employeeDto.getGender());
		employee.setGuardianNo(employeeDto.getGuardianNo());
		employee.setUpdatedOn(new Date());
		employee.setDeletedFlag(true);
		
		//save category id
		CategoryMaster category = subCategoryRepository.getCategorybyidDao(employeeDto.getCategoryId());	
		employee.setCategory(category);
		employee.setUserMaster(user);

		
        boolean flag=employeeRepository.insertEmployeeDao(employee);
    
        return flag;
	}

	@Override
	public List getAllEmpRecordService() {
        List  list=employeeRepository.getAllEmpListDao();
        return list;
    }
	
	@Override
    public EmployeeDto getRecordByEmpIdService(EmployeeDto employeeDto) {

        EmployeeMaster employee = new EmployeeMaster();
        employee.setEmployeeId(employeeDto.getEmployeeId());
     
        EmployeeMaster employee2 = employeeRepository.getRecordByEmployeeIdDao(employee);
        
    
        if(employee2!=null) {
        	EmployeeDto employeeDto2 = new EmployeeDto();
        	 
        	employeeDto2.setEmployeeId(employee2.getEmployeeId());
        	employeeDto2.setEmployeeName(employee2.getEmployeeName());
        	employeeDto2.setAddress(employee2.getAddress());
        	employeeDto2.setBirthDate(employee2.getBirthDate());
        	employeeDto2.setContactNo(employee2.getContactNo());
        	employeeDto2.setEmailId(employee2.getEmailId());
        	employeeDto2.setGuardianNo(employee2.getGuardianNo());
        	employeeDto2.setGender(employee2.getGender());
        	employeeDto2.setCategoryId(employee2.getCategory().getCategoryId());
        	employeeDto2.setCategory(employee2.getCategory());
        	employeeDto2.setUsers(employee2.getUserMaster());
        	
        	
        	UserRole userRole = userRoleRepository.findByUsersAndDeletedFlag(employee2.getUserMaster(), true); 
        	
        	employeeDto2.setRoles(userRole.getRoles());
        	employeeDto2.setRoleId(userRole.getRoles().getRoleId());  

       	return employeeDto2;
        	
        }
        else {
        	return null;
        }
   
        
    }

	@Override
	public boolean updateEmployeeService(EmployeeDto employeeDto) {
		//cat obj by id
		CategoryMaster category = categoryJpaRepository.findById(employeeDto.getCategoryId()).orElse(null);
	
		//save rolemaster //  role repositry
		RoleMaster roles =roleRepository.getroleByid(employeeDto.getRoleId());
		
		//getuserroleby userid		
		UserRole userRole=userRoleRepository.findByuserRole(employeeDto.getUsers().getUserId());
		userRole.setRoles(roles);
		userRoleRepository.save(userRole);
	 
		EmployeeMaster employee = new EmployeeMaster();
		employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setAddress(employeeDto.getAddress());
		employee.setBirthDate(employeeDto.getBirthDate());
		employee.setContactNo(employeeDto.getContactNo());
		employee.setEmailId(employeeDto.getEmailId());
		employee.setGuardianNo(employeeDto.getGuardianNo());
		employee.setGender(employeeDto.getGender());
		employee.setCategory(employeeDto.getCategory());
		employee.setUserMaster(employeeDto.getUsers());
		employee.setUpdatedOn(new Date());
		employee.setDeletedFlag(true);
	
		category.setCategoryId(employeeDto.getCategoryId());
		
		employee.setCategory(category);
		employee.setCategoryId(employeeDto.getCategoryId());
		
	
	 try {
		 employeeRepository.updateEmployeeRepository(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public EmpLoginResDto login(EmpLoginReqDto empLoginReqDto ,HttpServletRequest request) {
				
		UserMaster userMaster=userMasterRepository.findAllByUserName(empLoginReqDto.getUserName());
	
		System.out.println("username"+userMaster.getUserId());
		


       EmpLoginResDto empLoginResDto=new EmpLoginResDto();
       if(!userMaster.getUserName().equals(empLoginReqDto.getUserName())){
            empLoginResDto.setResponseCode(HttpStatus.NOT_FOUND.value());
            empLoginResDto.setMessage("User Name is Not Found");
        }
       else
       {
          if (userMaster.getPass().equals(empLoginReqDto.getPass()))
          {
              if (userMaster.isDeletedFlag(true))
               {
            	   WebSession webSession = new WebSession();
                   LoginData loginData = new LoginData();
              
                   empLoginResDto.setResponseCode(HttpStatus.OK.value());
                   empLoginResDto.setMessage("Login Successfully");

                   loginData.setUserName(userMaster.getUserName());
                   loginData.setUserId(userMaster.getUserId());
                   
                   System.out.println("sdf"+userMaster.getUserId());
                  
                   	webSession.setLoginData(loginData);
                   	SessionUtils.setWebSession(request, webSession);
                   	
                   	
                   	request.getSession().setAttribute("userId", userMaster.getUserId());
                   	request.getSession().setAttribute("userName", userMaster.getUserName());

                   	
                   
                   	
                    empLoginResDto.setUserId(userMaster.getUserId());
                    empLoginResDto.setUserName(userMaster.getUserName());
                    empLoginResDto.setUserMaster(userMaster);
                    
                    UserRole userRole = userRoleRepository.findByuserRole(userMaster.getUserId());
                    
                    //EmployeeMaster employeeMaster=emplJpaRepository.findByUserMaster(userMaster.getUserId());
                    EmployeeMaster employeeMaster=emplJpaRepository.findByUserMaster(userMaster);
                    
                    
             
                    empLoginResDto.setEmployeeId(employeeMaster.getEmployeeId());
                    empLoginResDto.setEmployeeName(employeeMaster.getEmployeeName());
                    empLoginResDto.setEmailId(employeeMaster.getEmailId());
                    empLoginResDto.setContactNo(employeeMaster.getContactNo());  
                    empLoginResDto.setCategory(employeeMaster.getCategory());
                   empLoginResDto.setRoleMaster(userRole.getRoles());
                    

                }                else
               {
                    empLoginResDto.setResponseCode(HttpStatus.FORBIDDEN.value());
                    empLoginResDto.setMessage("Account Has Been Blocked");
                }
            }
            else {
                empLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                empLoginResDto.setMessage("Invalid Password");
            }
        }        return empLoginResDto;

	}

	
	

}