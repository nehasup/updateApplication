package com.upskillutoday.crmRoot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.upskillutoday.crmRoot.model.*;
import com.upskillutoday.crmRoot.repository.*;
import com.upskillutoday.crmRoot.response.*;
import com.upskillutoday.crmRoot.service.*;
import com.upskillutoday.crmRoot.service.impl.EmpCategyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.upskillutoday.crmRoot.dto.EmployeeLeadDto;
import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.repository.EmpLeadJpaRepository;
import com.upskillutoday.crmRoot.repository.EmployeeJpaRepository;
import com.upskillutoday.crmRoot.repository.LeadJpaMasterRepository;
import com.upskillutoday.crmRoot.repository.UserMasterRepository;
import com.upskillutoday.crmRoot.service.EmpLeadService;
import com.upskillutoday.crmRoot.service.FileStorageService;
import com.upskillutoday.crmRoot.service.LeadMasterService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class LeadUploadFileController {

	@Autowired
	FileStorageService storageService;

	@Autowired
	LeadMasterService leadMasterService;
	
	@Autowired
	LeadJpaMasterRepository leadJpaMasterRepository;
	
	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Autowired
	EmployeeJpaRepository employeeJpaRepository;
	
	@Autowired
	EmpLeadJpaRepository empleadJparepository;
	
	@Autowired
	EmpLeadService empLeadService;

	@Autowired
	RemarkService remarkService;

	@Autowired
	EmpCategyServiceImpl empCategyService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	LeadMasterRepository leadMasterRepository;

	@Autowired
	HistoryRepository historyRepository;

	@Autowired
	EmployeeService employeeService;

	//import excel sheet
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(
			@RequestParam("file") MultipartFile file
	) {
		String message = "";
		try {
			Long result = storageService.save(file);
			if(result==0L) {
				message = "File import successfully: " + file.getOriginalFilename();
			} else if(result==1L) {
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

    //get All list of lead
	@GetMapping("/getLeadUploadlist")
	public List<LeadMaster> getuploadleadlist(Model model) {
		model.addAttribute("leadMaster", new LeadMaster());
		List<LeadMaster> leadList = storageService.findAll();
		return leadList;
	}

	//insert lead master
	@PostMapping("/insertLeadMaster")
	@ResponseBody
	public ResponseVO insertLead(
			@RequestBody LeadMasterDto leadMasterDto ,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse
	) {
		ResponseVO<LeadMasterDto> responseVO = new ResponseVO<LeadMasterDto>();
		boolean flag = leadMasterService.insertLeadService(leadMasterDto);
		if (flag) {
			responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
			responseVO.setMessage("Insert Successfully");
			responseVO.setResult(leadMasterDto);
		} else {
			responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
			responseVO.setMessage("Insert Failed!!");
			responseVO.setResult(leadMasterDto);
		}
		return responseVO;
	}
	
	 @GetMapping( value = "/getAllLeadList")
	 public @ResponseBody
	 ResponseVO<List> getAllLeadList(
	 		@RequestParam(name = "userId") Long userId
	 ) {
		ResponseVO<List> response = new ResponseVO<>();
		//user obj
		List list = leadMasterService.getAllLeadRecordService();
		try {
			EmployeeMaster employeeMaster = employeeJpaRepository.findByUserMaster(userMasterRepository.findAllByUserIdAndDeletedFlag(userId, true));
			RoleMaster roleMaster = roleRepository.getroleByid(roleRepository.getRoleIdFromUserId(userId));
			if(roleMaster.getRoleName().equalsIgnoreCase("Project manager") || roleMaster.getRoleName().equalsIgnoreCase("Verification counsellor")) {
				//Admin // All leads
				if(list!=null) {
					response.setResult(list);
				}
				else {
					//Data not present
					response.setResult(list);
					response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
					response.setMessage("Data is not Present");
				}
			} else if (roleMaster.getRoleName().equalsIgnoreCase("Admissions counsellor")) {
				// Cousler     //Category based leads
				List<LeadMasterDto>  leadMasterDtoList = leadMasterService.getAllLeadListCategoryWiseService(employeeMaster);
				if(leadMasterDtoList!=null) {
					response.setResult(leadMasterDtoList);
				}
				else {
					//Data not present
					response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
					response.setMessage("Data is not Present");
					response.setResult(leadMasterDtoList);
				}
			}
		} catch (NullPointerException nullPointerException) {}
		return response;
	}

	@GetMapping(value = "getAllLeadFromStatus", params = {"lead_status"})
	public List getAllLeadFromStatus(
			@RequestParam("lead_status") Long leadStatus
	) { return leadMasterRepository.getLeadsByRemark(leadStatus); }

	 @GetMapping("/getAllVerifiedLeadList")	  
	 public @ResponseBody ResponseVO<List> getAllVerifiedLeadList(
	 		@RequestParam(name = "userId") Long userId
	 ) {
		 System.out.println("user"+userId);
	        ResponseVO<List> response=new ResponseVO<List>();
	        System.out.println("List Successfully!!");
		 try {
			 EmployeeMaster employeeMaster = employeeJpaRepository.findByUserMaster(userMasterRepository.findAllByUserIdAndDeletedFlag(userId, true));
			 RoleMaster roleMaster = roleRepository.getroleByid(roleRepository.getRoleIdFromUserId(userId));
			 if(roleMaster.getRoleName().equalsIgnoreCase("Project manager") || roleMaster.getRoleName().equalsIgnoreCase("Verification counsellor")) {
//				 Admin // All leads
					List list=leadMasterService.getAllLeadRecordService();
					if(list!=null) {
						 response.setResult(list);
					}
					else {
						//Data not present
						 response.setResult(list);
						response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
						response.setMessage("Data is not Present");
					}
			 } else if (roleMaster.getRoleName().equalsIgnoreCase("Admissions counsellor")) {
				 //Cousler     //Category based leads
					List<LeadMasterDto>  leadMasterDtoList = leadMasterService.getCategoryWiseandverifyLeadService(employeeMaster);
					if(leadMasterDtoList!=null) {
						response.setResult(leadMasterDtoList);
					}
					else {
						//Data not present
						response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
						response.setMessage("Data is not Present");
						response.setResult(leadMasterDtoList);
					}
			 }
		 } catch (NullPointerException nullPointerException) {}
		return response;
	}

	//getRecordByidForEdit.........By neha
	 @GetMapping("/getAllLeadbyid/{id}")	  
	 @ResponseBody public ResponseVO<LeadMasterDto> getRecordByLeadIdController(
	 		@PathVariable(value = "id") Long studentId
	 ) {
	     ResponseVO<LeadMasterDto> response = new ResponseVO<LeadMasterDto>();
	     LeadMasterDto leadMasterDto = new LeadMasterDto();
	     leadMasterDto.setStudentId(studentId);
	     LeadMasterDto leadMasterDto2=leadMasterService.getRecordByStudentIdService(leadMasterDto);

	     if(leadMasterDto2!=null) {
	         response.setMessage("Search By Data Sucessfully");
	         response.setStatusCode(String.valueOf(HttpStatus.OK));
	         response.setResult(leadMasterDto2);
	     } else {
	         response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	         response.setMessage("Search Failed!!");
	         response.setResult(leadMasterDto2);

	     }
	     return response;
	 }

	 //update Lead by id
	// updated by Laukik
	 @PutMapping("/updateLeadByid/")
	 @ResponseBody public ResponseVO updateLeadController(
	 		@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "empId") Long empUserId,
			@RequestBody LeadMasterDto leadMasterDto
	 ) {
		 ResponseVO<LeadMasterDto> responseVO = new ResponseVO<LeadMasterDto>();
		 boolean flag = leadMasterService.updateLeadService(userId, leadMasterDto);
		  if(flag){
			  responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
			  responseVO.setMessage("Update Successfully!!");
			  historyRepository.insertHistory(new History(leadMasterDto.getComments(), new Date(), employeeService.getEmployeeByUserId(empUserId), leadJpaMasterRepository.findByStudentId(leadMasterDto.getStudentId()), remarkService.getRemarkById(leadMasterDto.getRemarkId())));
		  }
		  else {
			  responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
			  responseVO.setMessage("Updation Failed!!");
		  }
	      return responseVO;
	 }
	 
	//delete Lead by id
	@DeleteMapping("/deleteLead/{id}")
	public Map<String, Boolean> deleteLead(
			@PathVariable(value = "id") Long studentId,
			@RequestParam(value = "empId") Long empUserId
	) throws ResourceNotFoundException {
		LeadMaster leadMaster = leadJpaMasterRepository.findById(studentId)
	   .orElseThrow(() -> new ResourceNotFoundException("Student Id not found for this id :: " + studentId));
		leadMaster.setDeletedFlag(false);
		leadJpaMasterRepository.save(leadMaster);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deletedFlag", Boolean.TRUE);
		historyRepository.insertHistory(new History("Deleted Lead by Employee", new Date(), employeeService.getEmployeeByUserId(empUserId), leadJpaMasterRepository.findByStudentId(studentId), remarkService.getRemarkById(remarkService.getRemarkById("Deleted"))));
		return response;
	} 

	//assign lead to employee
	@PostMapping(value = "/saveleadassignemp", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseVO assignLeadtoEmployee(
			@RequestBody EmployeeLeadDto employeeLeadDto
	) {
		
		ResponseVO response = new ResponseVO();
		LeadMaster leadMasterObj = null;
		for(String leadMaster : employeeLeadDto.getStudentId()) {

			long leadId=Long.parseLong(leadMaster);  //convert string to long.

			//bring leadMasterObj from leadid
			
			leadMasterObj= leadJpaMasterRepository.findByStudentIdAndDeletedFlag(leadId, true);	
			
			EmployeeMaster employeeMaster = employeeJpaRepository.findByEmployeeIdAndDeletedFlag(employeeLeadDto.getEmployeeId(),true);
	
			//insert
			EmpLead empLead = new EmpLead();
			empLead.setEmployeeMaster(employeeMaster);
			empLead.setLeadMaster(leadMasterObj);
			empLead.setUpdatedOn(new Date());
			empLead.setDeletedFlag(true);

		   empleadJparepository.save(empLead);

		 //change flag assignleadflag
			leadMasterObj.setAssignLeadFlag(true);
			leadJpaMasterRepository.save(leadMasterObj);

			}

		return response;
    }
	  
	 @GetMapping("/getAssignLeadbyempId")	  
	 public @ResponseBody ResponseVO<List> getListAssignLeadbyEmpId(
	 		@RequestParam(name = "userId") Long userId
	 ) {
		 System.out.println("user"+userId);
	        ResponseVO<List> response=new ResponseVO<List>();
	        System.out.println("List Successfully!!");

		 try {
			 EmployeeMaster employeeMaster = employeeJpaRepository.findByUserMaster(userMasterRepository.findAllByUserIdAndDeletedFlag(userId, true));
			 RoleMaster roleMaster = roleRepository.getroleByid(roleRepository.getRoleIdFromUserId(userId));
			 if(roleMaster.getRoleName().equalsIgnoreCase("Project manager") || roleMaster.getRoleName().equalsIgnoreCase("Verification counsellor")) {
				//Admin // All leads
				//List list=leadMasterService.getAllLeadRecordService();
				List<LeadMasterDto> list =empLeadService.getAllAssignEmpLeadRecordService();
				if(list!=null) {
					 response.setResult(list);
				}
				else {
					//Data not present
					 response.setResult(list);
					response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
					response.setMessage("Data is not Present");
				}
			 } else if (roleMaster.getRoleName().equalsIgnoreCase("Admissions counsellor")) {
				 //Cousler     //Category based leads
				 List<LeadMasterDto> leadMasterDtoList=leadMasterService.getAllAssignLeadListService(employeeMaster);
				if(leadMasterDtoList!=null) {
					response.setResult(leadMasterDtoList);
				}
				else {
					//Data not present
					response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
					response.setMessage("Data is not Present");
					response.setResult(leadMasterDtoList);
				}
			 }
		 } catch (NullPointerException nullPointerException) {}
	        return response;
	    }
}
