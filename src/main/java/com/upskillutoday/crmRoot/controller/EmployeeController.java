package com.upskillutoday.crmRoot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.upskillutoday.crmRoot.request.AuthenticationRequest;
import com.upskillutoday.crmRoot.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.repository.EmployeeJpaRepository;
import com.upskillutoday.crmRoot.request.EmpLoginReqDto;
import com.upskillutoday.crmRoot.response.EmpLoginResDto;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeJpaRepository employeeJpaRepository;

	@Autowired
    private UserLoginService userLoginService;
	
	@PostMapping("/saveEmployee")
	@ResponseBody 
	public ResponseVO insertEmployee(
	        @RequestBody EmployeeDto employeeDto,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        ResponseVO response = new ResponseVO();
        boolean flag=employeeService.insertEmployeeService(employeeDto);
        if(flag) {
            response.setMessage("Insert Employee Sucessfully");
            response.setStatusCode(String.valueOf(HttpStatus.OK));
        } else {
            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            response.setMessage("Insert Failed!!");
        }
        return response;
    }
	
     @GetMapping("/getAllEmployeeList")
     @ResponseBody
     public ResponseVO<List> getEmployeeAllList() {
        ResponseVO<List> response=new ResponseVO<List>();
        System.out.println("List Successfully!!");
        List list=employeeService.getAllEmpRecordService();
        response.setResult(list);
        if(list.size()!=0) {
            response.setStatusCode(String.valueOf(HttpStatus.OK));
            response.setMessage("Data is Present Successfully!!");
        } else {
            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            response.setMessage("Data is Not Present!!");
        }
        return response;
    }
 
     //getRecordByidForEdit.........By neha
     @GetMapping("/getAllEmpbyid/{id}")
     @ResponseBody public ResponseVO<EmployeeDto> getRecordByEmployeeIdController(
             @PathVariable(value = "id") Long employeeId
     ) {
         ResponseVO<EmployeeDto> response = new ResponseVO<EmployeeDto>();
         EmployeeDto employeeDto = new EmployeeDto();
         employeeDto.setEmployeeId(employeeId);
         EmployeeDto resultEmployeeDto =employeeService.getRecordByEmpIdService(employeeDto);
         if(resultEmployeeDto!=null) {
             response.setMessage("Search By Data Sucessfully");
             response.setStatusCode(String.valueOf(HttpStatus.OK));
             response.setResult(resultEmployeeDto);
         } else {
             response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
             response.setMessage("Search Failed!!");
             response.setResult(resultEmployeeDto);
         }
         return response;
     }
 
 
     //update employee by id
     @PutMapping("/updateEmployeeByid/{id}")
     @ResponseBody public ResponseVO updateEmloyeeController(
             @PathVariable(value = "id") Long employeeId,
             @RequestBody EmployeeDto employeeDto
     ) {
         ResponseVO<EmployeeDto> responseVO = new ResponseVO<EmployeeDto>();
         boolean flag = employeeService.updateEmployeeService(employeeDto);
              if(flag) {
                  responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
                  responseVO.setMessage("Update Successfully!!");
              } else {
                  responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
                  responseVO.setMessage("Updation Failed!!");
              }
          return responseVO;
     }
 
    //delete employee by id
    @DeleteMapping("/deleteEmp/{id}")
    public Map<String, Boolean> deleteEmployeeCategory(
            @PathVariable(value = "id") Long employeeId
    ) throws ResourceNotFoundException {
        EmployeeMaster employeeMaster = employeeJpaRepository.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("employee Id not found for this id :: " + employeeId));
        employeeMaster.setDeletedFlag(false);
        employeeJpaRepository.save(employeeMaster);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deletedFlag", Boolean.TRUE);
        return response;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        EmpLoginResDto empLoginResDto = employeeService.login(new EmpLoginReqDto(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        return userLoginService.createAuthenticationToken(authenticationRequest);
    }
}
