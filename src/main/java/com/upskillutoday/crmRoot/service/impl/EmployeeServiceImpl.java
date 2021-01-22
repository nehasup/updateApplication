package com.upskillutoday.crmRoot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.upskillutoday.crmRoot.model.*;
import com.upskillutoday.crmRoot.repository.*;
import com.upskillutoday.crmRoot.request.DailyLeadReportDto;
import com.upskillutoday.crmRoot.response.DailyReportModelDto;
import com.upskillutoday.crmRoot.response.EmployeeNameWithId;
import com.upskillutoday.crmRoot.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.upskillutoday.crmRoot.common.LoginData;
import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.request.EmpLoginReqDto;
import com.upskillutoday.crmRoot.response.EmpLoginResDto;
import com.upskillutoday.crmRoot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
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

	@Autowired
	EmpCategyRepository empCategyRepository;

	@Autowired
	CategoryServiceImpl categoryService;

	@Autowired
	private RemarkService remarkService;

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
		employee.setUserMaster(user);
		employee.setUpdatedOn(new Date());
		employee.setDeletedFlag(true);
		
		//save category id
		// Commented By Laukik
//		CategoryMaster category = subCategoryRepository.getCategorybyidDao(employeeDto.getCategoryId());
//		employee.setCategory(category);
//		employee.setUserMaster(user);

        boolean flag=employeeRepository.insertEmployeeDao(employee);

        // Added By Laukik
		for (Long catId: employeeDto.getCategories()) {
			EmpCategy empCategy = new EmpCategy();
			empCategy.setEmployeeMaster(employee);
			empCategy.setCategoryMaster(categoryService.getCatgoryById(catId));
			empCategyRepository.save(empCategy);
		}

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
//        	employeeDto2.setCategoryId(employee2.getCategory().getCategoryId());
//        	employeeDto2.setCategory(employee2.getCategory());
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
//		employee.setCategory(employeeDto.getCategory());

		category.setCategoryId(employeeDto.getCategoryId());

		employee.setUserMaster(employeeDto.getUsers());
		employee.setUpdatedOn(new Date());
		employee.setDeletedFlag(true);
//		employee.setCategory(category);
		employee.setCategoryId(employeeDto.getCategoryId());

	 try {
		 employeeRepository.updateEmployeeRepository(employee);

		 // Added By Laukik
		 for (Long catId: employeeDto.getCategories()) {
			 EmpCategy empCategy = new EmpCategy();
			 empCategy.setEmployeeMaster(employee);
			 empCategy.setCategoryMaster(categoryService.getCatgoryById(catId));
			 empCategyRepository.save(empCategy);
		 }


		 return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public EmpLoginResDto login(EmpLoginReqDto empLoginReqDto) {
		UserMaster userMaster = userMasterRepository.findAllByUserName(empLoginReqDto.getUserName());
		System.out.println("username" + userMaster.getUserId());
       EmpLoginResDto empLoginResDto = new EmpLoginResDto();
       if(!userMaster.getUserName().equals(empLoginReqDto.getUserName())){
            empLoginResDto.setResponseCode(HttpStatus.NOT_FOUND.value());
            empLoginResDto.setMessage("User Name is Not Found");
        } else {
          if (userMaster.getPass().equals(empLoginReqDto.getPass())) {
              if (userMaster.isDeletedFlag(true)) {
                   LoginData loginData = new LoginData();
                   empLoginResDto.setResponseCode(HttpStatus.OK.value());
                   empLoginResDto.setMessage("Login Successfully");
                   loginData.setUserName(userMaster.getUserName());
                   loginData.setUserId(userMaster.getUserId());
                   System.out.println("sdf"+userMaster.getUserId());
                    empLoginResDto.setUserId(userMaster.getUserId());
                    empLoginResDto.setUserName(userMaster.getUserName());
                    empLoginResDto.setUserMaster(userMaster);
                    UserRole userRole = userRoleRepository.findByuserRole(userMaster.getUserId());
                    EmployeeMaster employeeMaster=emplJpaRepository.findByUserMaster(userMaster);
                    empLoginResDto.setEmployeeId(employeeMaster.getEmployeeId());
                    empLoginResDto.setEmployeeName(employeeMaster.getEmployeeName());
                    empLoginResDto.setEmailId(employeeMaster.getEmailId());
                    empLoginResDto.setContactNo(employeeMaster.getContactNo());
                   empLoginResDto.setRoleMaster(userRole.getRoles());
			   } else {
                    empLoginResDto.setResponseCode(HttpStatus.FORBIDDEN.value());
                    empLoginResDto.setMessage("Account Has Been Blocked");
                }
            } else {
                empLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                empLoginResDto.setMessage("Invalid Password");
            }
        }
       return empLoginResDto;
	}

	@Override
	public Long getEmpIdFromUserId(Long userId) {
		return employeeRepository.getEmployeeIdByUserId(userId);
	}

	@Override
	public EmployeeMaster getEmployeeByUserId(Long userId) {
		return employeeRepository.getEmployeeByUserId(userId);
	}

	@Override
	public EmployeeMaster getEmployeeByEmpId(Long empId) {
		return employeeRepository.getEmployeeByEmpId(empId);
	}

	@Override
	public List getAllVerificationCounsellor() {
		return employeeRepository.getAllVerificationCounsellor();
	}

	@Override
	public Long getEmployeeAutomatically(Long studentId) {
		return employeeRepository.getEmployeeFromCategory(studentId);
	}

	@Override
	public List getDailyCountOfEmployees() {
		List<EmployeeNameWithId> employeeVeAndAdCo = employeeRepository.getVerifiactionAndAdmissionConusellor();
		ArrayList<DailyReportModelDto> dailyLeadReportDtos = new ArrayList<>();
		for(EmployeeNameWithId employeeNameWithId : employeeVeAndAdCo) {
			dailyLeadReportDtos.add(new DailyReportModelDto(employeeNameWithId.getEmpId(), employeeNameWithId.getEmployeeName(), remarkService.getRemarkWithCountForEmployee(employeeNameWithId.getEmpId())));
		}

		return dailyLeadReportDtos;
	}
}
