package com.upskillutoday.crmRoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.upskillutoday.crmRoot.dto.RoleDto;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.RoleService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/saveRoles")
	@ResponseBody 
	public ResponseVO insertRoles(@RequestBody RoleDto roleDto) {
		  ResponseVO response = new ResponseVO();
	      
	        boolean flag=roleService.insertRole(roleDto);
	        if(flag)
	        {	response.setResult(flag);
	            response.setMessage("Insert Role Sucessfully");
	            response.setStatusCode(String.valueOf(HttpStatus.OK));
	        }
	        else {
	            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	            response.setMessage("Insert Failed!!");
	            response.setResult(flag);
	        }
	        return response;

    }
	
	//get role active list
		 @GetMapping("/getActiveRoleList")	  
		 @ResponseBody  public ResponseVO<List> getRoleAllList() {
		        ResponseVO<List> response=new ResponseVO<List>();
		        System.out.println("List Successfully!!");
		        List list=roleService.getAllRoleService();
		        response.setResult(list);

		        if(list.size()!=0){
		            response.setStatusCode(String.valueOf(HttpStatus.OK));
		            response.setMessage("Data is Present Successfully!!");
		        }
		        else {
		            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		            response.setMessage("Data is Not Present!!");
		        }

		        return response;
		    }
	

	

}
