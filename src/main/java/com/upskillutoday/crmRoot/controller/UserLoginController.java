package com.upskillutoday.crmRoot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.upskillutoday.crmRoot.dto.UserLoginDto;
import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.request.EmpLoginReqDto;

import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.response.UserLoginResponseDto;
import com.upskillutoday.crmRoot.service.EmployeeService;
import com.upskillutoday.crmRoot.service.UserLoginService;


import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")

public class UserLoginController {
	
	@Autowired
	private UserLoginService userloginService;
	
	@Autowired
	private EmployeeService employeeServiceimpl;
	
	 @PostMapping("/loginuser")
	 @ResponseBody
	 public ResponseVO<UserLoginResponseDto> userLoginController(@RequestBody  UserLoginDto userLoginDto) {

	        ResponseVO<UserLoginResponseDto> response = new ResponseVO<UserLoginResponseDto>();

	        UserLoginResponseDto dto=userloginService.loginuserService(userLoginDto);

	        if(userLoginDto.isFlag())
	        {
	            response.setStatusCode(String.valueOf(HttpStatus.OK));
	            System.out.println(response.getStatusCode());
	            response.setMessage(userLoginDto.getLoginMsg());
	            response.setResult(dto);
	        }
	        else {
	            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	            System.out.println(response.getStatusCode());
	            response.setMessage(userLoginDto.getLoginMsg());
	            response.setResult(dto);

	        }
	        return response;
	    }
	 
//	 @PostMapping("/loginemploye")
//	    public ResponseEntity login(@RequestBody EmpLoginReqDto empLoginReqDto)
//	    {
//		
//	        EmpLoginResDto empLoginResDto = employeeServiceimpl.login(empLoginReqDto);
//	        return new ResponseEntity(empLoginResDto,HttpStatus.OK);
//	    }
//	 
//	   @PostMapping("/login")
//	    public boolean login(@RequestBody UserMaster user) {
//	        return
//	          user.getUserName().equals("userName") && user.getPass().equals("pass");
//	    }
//		
//	    @RequestMapping("/user")
//	    public Principal user(HttpServletRequest request) {
//	        String authToken = request.getHeader("Authorization")
//	          .substring("Basic".length()).trim();
//	        return () ->  new String(Base64.getDecoder()
//	          .decode(authToken)).split(":")[0];
//	    }

}
