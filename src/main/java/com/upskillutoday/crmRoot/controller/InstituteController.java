package com.upskillutoday.crmRoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.upskillutoday.crmRoot.dto.InstituteDto;
import com.upskillutoday.crmRoot.model.InstituteMaster;
import com.upskillutoday.crmRoot.repository.InstituteRepository;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.InstituteService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class InstituteController {

	@Autowired
	InstituteRepository instituteRepository;
	
	@Autowired
	InstituteService instituteService;

	 @PostMapping("/saveinsitute")
	 @ResponseBody public ResponseVO  createInstitute(
	 		@RequestBody InstituteDto instituteDto
	 ) {
		 ResponseVO<InstituteDto>responseVO=new ResponseVO<InstituteDto>();
	        boolean flag= instituteService.insertInstituteservice(instituteDto);
	        if(flag) {
	            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
	            responseVO.setMessage("Insert Successfully!!");
	            responseVO.setResult(instituteDto);
	        } else {
	            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	            responseVO.setMessage("Insert Failed!!");
	            responseVO.setResult(instituteDto);
	        }
	        return responseVO;
	}
}
