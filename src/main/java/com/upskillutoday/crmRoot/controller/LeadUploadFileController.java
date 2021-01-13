package com.upskillutoday.crmRoot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.upskillutoday.crmRoot.common.WebSession;
import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.dto.EmployeeLeadDto;
import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.repository.EmpLeadJpaRepository;
import com.upskillutoday.crmRoot.repository.EmployeeJpaRepository;
import com.upskillutoday.crmRoot.repository.LeadJpaMasterRepository;
import com.upskillutoday.crmRoot.repository.UserMasterRepository;
import com.upskillutoday.crmRoot.response.EmpLeadResponseDto;
import com.upskillutoday.crmRoot.response.ResponseMessage;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.EmpLeadService;
import com.upskillutoday.crmRoot.service.FileStorageService;
import com.upskillutoday.crmRoot.service.LeadMasterService;
import com.upskillutoday.crmRoot.util.SessionUtils;
;

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

	//import excel sheet
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		try {
		
			Long result = storageService.save(file);

			if(result==0L) {
				message = "File import successfully: " + file.getOriginalFilename();
			}
			else if(result==1L) {
				message = "Data already exist " + file.getOriginalFilename();
			}
			
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			
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
	public ResponseVO insertLead(@RequestBody LeadMasterDto leadMasterDto ,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception {
		ResponseVO<LeadMasterDto> responseVO = new ResponseVO<LeadMasterDto>();

//       System.out.println("UserName : "+httpServletRequest.getSession().getAttribute("userName")+" OR : "+httpServletRequest.getSession().getAttribute("userId") );
//		  
//		  WebSession webSession = SessionUtils.getWebSession(httpServletRequest);
//	        long userId = webSession.getLoginData().getUserId();
//	        
//	        
//	        
//	        System.out.println("userId : "+userId + " ; UserName : "+httpServletRequest.getSession().getAttribute("userName")+" OR : "+httpServletRequest.getSession().getAttribute("userId") );
//	   
		
		boolean flag = leadMasterService.insertLeadService(leadMasterDto);
		if (flag) {
			responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
			responseVO.setMessage("Insert Successfully");
			responseVO.setResult(leadMasterDto);
		}

		else {
			responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
			responseVO.setMessage("Insert Failed!!");
			responseVO.setResult(leadMasterDto);
		}
		return responseVO;

	}
	
	 @GetMapping("/getAllLeadList")	  
	 public @ResponseBody ResponseVO<List> getAllLeadList(@RequestParam(name = "userId") Long userId) {
		 //, @RequestParam(name = "pageName") String pageName
		 System.out.println("user"+userId);
	        ResponseVO<List> response=new ResponseVO<List>();
	        System.out.println("List Successfully!!");
	        //user obj
	        if(userId!=null) {
		        UserMaster userMaster = userMasterRepository.findAllByUserIdAndDeletedFlag(userId, true);
		        if(userMaster!=null) {
		        	EmployeeMaster employeeMaster = employeeJpaRepository.findByUserMaster(userMaster);
		        	if(employeeMaster!=null) {
		        		if(employeeMaster.getCategory()!=null && !employeeMaster.getCategory().equals("") ) {
		        			//Cousler     //Category based leads
		        			//List<LeadMasterDto>  leadMasterDtoList = leadMasterService.getAllLeadListCategoryWiseService(employeeMaster, pageName);
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
		        		else {
		        			//Admin // All leads
		        			
		        			//List list=leadMasterService.getAllLeadRecordService();
		        			List<LeadMasterDto> list = leadMasterService.getAllLeadRecordService();
		        		
		        			if(list!=null) {
		        				 response.setResult(list);
		        			}
		        			else {
		        				//Data not present
		        				 response.setResult(list);
		        				response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
		        				response.setMessage("Data is not Present");		        				
		        			}
		      			   
		        			
		        		}
		        		
		        	}
		        }
	        
	        }
	        
	        
			  
			

	        return response;
	    }
	
	 @GetMapping("/getAllVerifiedLeadList")	  
	 public @ResponseBody ResponseVO<List> getAllVerifiedLeadList(@RequestParam(name = "userId") Long userId) {
		 System.out.println("user"+userId);
	        ResponseVO<List> response=new ResponseVO<List>();
	        System.out.println("List Successfully!!");
	        //user obj
	        if(userId!=null) {
		        UserMaster userMaster = userMasterRepository.findAllByUserIdAndDeletedFlag(userId, true);
		        if(userMaster!=null) {
		        	EmployeeMaster employeeMaster = employeeJpaRepository.findByUserMaster(userMaster);
		        	if(employeeMaster!=null) {
		        		if(employeeMaster.getCategory()!=null && !employeeMaster.getCategory().equals("") ) {
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
		        		else {
		        			//Admin // All leads
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
		      			   
		        			
		        		}
		        	}
		        }
		        
	        
	        }
	        
	        
	        
			  
			

	        return response;
	    }
	 

	 
	 
	 
	 
	 
	 
	//getRecordByidForEdit.........By neha
	 @GetMapping("/getAllLeadbyid/{id}")	  
	 @ResponseBody public ResponseVO<LeadMasterDto> getRecordByLeadIdController(@PathVariable(value = "id") Long studentId) {
	     ResponseVO<LeadMasterDto> response = new ResponseVO<LeadMasterDto>();

	     LeadMasterDto leadMasterDto = new LeadMasterDto();
	     leadMasterDto.setStudentId(studentId);
	     
	    
	     LeadMasterDto leadMasterDto2=leadMasterService.getRecordByStudentIdService(leadMasterDto);
	    
	  
	     
	     if(leadMasterDto2!=null)
	     {
	         response.setMessage("Search By Data Sucessfully");
	         response.setStatusCode(String.valueOf(HttpStatus.OK));
	         response.setResult(leadMasterDto2);
	     }
	     else {
	         response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	         response.setMessage("Search Failed!!");
	         response.setResult(leadMasterDto2);

	     }
	     return response;
	 }
	 
	 
	 //update Lead by id
	 @PutMapping("/updateLeadByid/{id}")
	 @ResponseBody public ResponseVO updateLeadController(@PathVariable(value = "id") Long studentId,@RequestBody LeadMasterDto leadMasterDto) throws ResourceNotFoundException {
		 
		 ResponseVO<LeadMasterDto> responseVO = new ResponseVO<LeadMasterDto>();
		 
		
		 
		 boolean flag = leadMasterService.updateLeadService(leadMasterDto);
		 
		
		      if(flag){
		          responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
		          responseVO.setMessage("Update Successfully!!");
		      }
		      else {
		          responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		          responseVO.setMessage("Updation Failed!!");
		      }
	      return responseVO;
		 
		
		 
	 }
	 
	//delete Lead by id
	@DeleteMapping("/deleteLead/{id}")
	public Map<String, Boolean> deleteLead(@PathVariable(value = "id") Long studentId)
	     throws ResourceNotFoundException {
		LeadMaster leadMaster = leadJpaMasterRepository.findById(studentId)
	   .orElseThrow(() -> new ResourceNotFoundException("Student Id not found for this id :: " + studentId));

		leadMaster.setDeletedFlag(false);
		leadJpaMasterRepository.save(leadMaster);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deletedFlag", Boolean.TRUE);
	    return response;
	} 
	
	
	
//assign lead to employee
	@PostMapping(value = "/saveleadassignemp", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseVO assignLeadtoEmployee(@RequestBody EmployeeLeadDto employeeLeadDto) {	
		
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
	 public @ResponseBody ResponseVO<List> getListAssignLeadbyEmpId(@RequestParam(name = "userId") Long userId) {
		 System.out.println("user"+userId);
	        ResponseVO<List> response=new ResponseVO<List>();
	        System.out.println("List Successfully!!");
	        //user obj
	        if(userId!=null) {
		        UserMaster userMaster = userMasterRepository.findAllByUserIdAndDeletedFlag(userId, true);
		        if(userMaster!=null) {
		        	EmployeeMaster employeeMaster = employeeJpaRepository.findByUserMaster(userMaster);
		        	if(employeeMaster!=null) {
		        		if(employeeMaster.getCategory()!=null && !employeeMaster.getCategory().equals("") ) {
		        			        		
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
		        		else {
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
		      			   
		        			
		        		}
		        	}
		        	}
		        }
	        
	        return response;
	    }
	  
	
	

	 
	
}
