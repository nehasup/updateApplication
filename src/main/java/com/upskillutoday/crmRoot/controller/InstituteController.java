package com.upskillutoday.crmRoot.controller;

import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.response.ResponseMessage;
import com.upskillutoday.crmRoot.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upskillutoday.crmRoot.dto.InstituteDto;
import com.upskillutoday.crmRoot.model.InstituteMaster;
import com.upskillutoday.crmRoot.repository.InstituteRepository;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.InstituteService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class InstituteController {

	@Autowired
	InstituteRepository instituteRepository;
	
	@Autowired
	InstituteService instituteService;

	@Autowired
	FileStorageService storageService;

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

	@PostMapping("/saveInstituteFromFile")
	@ResponseBody public ResponseEntity<ResponseMessage>  saveInstituteFromFile(
			@RequestParam("file") MultipartFile file
	) {
		String message = "";
		try {
			Long result = storageService.saveInstituteFile(file);
			if(result > 0L) {
				message = "File import successfully: " + file.getOriginalFilename() + " Count : " + result;
			} else {
				message = "Data already exist " + file.getOriginalFilename();
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (ResourceNotFoundException e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "! : " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/getInstituteByCategoryFromStudentId")
	public List getInstituteByCategoryFromStudentId(@RequestParam("catId") Long catId) {
	 	return instituteService.getInstituteByCategoryFromStudentId(catId);
	}

}
