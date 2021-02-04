package com.upskillutoday.crmRoot.controller;

import java.io.IOException;
import java.util.*;
import javax.mail.internet.MimeMessage;
import com.upskillutoday.crmRoot.model.*;
import com.upskillutoday.crmRoot.repository.*;
import com.upskillutoday.crmRoot.request.StudentWithInst;
import com.upskillutoday.crmRoot.response.*;
import com.upskillutoday.crmRoot.service.*;
import com.upskillutoday.crmRoot.service.impl.EmpCategyServiceImpl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private InstituteLeadRepository instituteLeadRepository;

	@Autowired
	private InstituteRepository instituteRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	  private final ArrayList<String> architectureSub = new ArrayList<String>(Arrays.asList(
			  "B.voc (Interior Design)",
			  "Bachelor of Architecture",
			  "M.Arch"
	  ));

	private final ArrayList<String> managementSub = new ArrayList<String>(Arrays.asList(
			"Master of Management Studies",
			"Post Graduate Diploma in Management"
	));

	private final ArrayList<String> ndfsSub = new ArrayList<String>(Arrays.asList(
			"Accounts",
			"Apparel Merchandising",
			"Bag Designing",
			"Banking Expert",
			"Bfsi Expert",
			"Business Communication",
			"Capital Market",
			"Certified Financial Planner",
			"Community Journalist",
			"Construction Supervisor",
			"Creative Art",
			"Digital Marketing",
			"Digital Marketing Manager",
			"Embroidery And Fashion Craft",
			"Entrepreneurship Management",
			"Equity Market",
			"Event Management",
			"Family Business Management",
			"Fashion Business Management",
			"Fashion Photography",
			"Fashion Styling and Draping",
			"Finance Expert",
			"Foot ware Design",
			"Front Desk Officer",
			"Furniture Designing",
			"Global Fashion Design",
			"Graphic Design",
			"Graphic Designer",
			"GST",
			"Hr Payroll Management",
			"Hr Recruitment",
			"Human Resource Management",
			"Interior Commercial Design",
			"Interior Design - Commercial Space",
			"Interior Design - Home Space",
			"Interior Home Design",
			"Jewellery Designing",
			"Lighting Design",
			"Loan Officer",
			"Mutual Funds",
			"Photography",
			"Product Design",
			"Product Management",
			"Real Estate Management",
			"Retail Management",
			"Sales Executive (Media Org.)",
			"Set Design",
			"Social Media Manager",
			"Training Centre Manager",
			"Training Coordinator"
	));

	//import excel sheet
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(
			@RequestParam("file") MultipartFile file
	) {
		String message = "";
		try {
			Long result = storageService.save(file);
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
			@RequestBody LeadMasterDto leadMasterDto
	) {
		ResponseVO<LeadMasterDto> responseVO = new ResponseVO<LeadMasterDto>();
		boolean flag = leadMasterService.insertLeadService(leadMasterDto);
		if (flag) {
			responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
			responseVO.setMessage("Insert Successfully");
			responseVO.setResult(leadMasterDto);

			EmployeeMaster employeeMaster = employeeService.getEmployeeByEmpId(leadMasterDto.getEmployeeId());
			List leadMasterObjs = leadMasterRepository.getLeadMasterByNameEmailContactDeleteFlag(leadMasterDto.getStudentName(), leadMasterDto.getContactNo(), leadMasterDto.getEmailId(), true);
			if(leadMasterObjs.size() > 0) {
				LeadMaster leadMasterObj = (LeadMaster) leadMasterObjs.get(0);
				EmpLead empLead = new EmpLead();
				empLead.setLeadMaster(leadMasterObj);
				empLead.setEmployeeMaster(employeeMaster);
				empLead.setUpdatedOn(new Date());
				empLead.setDeletedFlag(true);
				empleadJparepository.save(empLead);
				historyRepository.insertHistory(new History("Inserted" ,new Date(), employeeService.getEmployeeByEmpId(leadMasterDto.getEmployeeId()), leadMasterObj, remarkService.getRemarkById(leadMasterObj.getRemarkMaster().getRemarkId())));
			}
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
		try {
			RoleMaster roleMaster = roleRepository.getroleByid(roleRepository.getRoleIdFromUserId(userId));
			if(roleMaster.getRoleName().equalsIgnoreCase("Project manager") || roleMaster.getRoleName().equalsIgnoreCase("Director") || roleMaster.getRoleName().equalsIgnoreCase("Founder")) {
				//Admin // All leads
				List list = leadMasterService.getAllLeadRecordService();

				if(list!=null) {
					response.setResult(list);
				}

				else {
					//Data not present
					response.setResult(list);
					response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
					response.setMessage("Data is not Present");
				}
			} else if (roleMaster.getRoleName().equalsIgnoreCase("Admissions counsellor") || roleMaster.getRoleName().equalsIgnoreCase("Verification counsellor")) {
				// Cousler     //Category based leads
				List  leadMasterDtoList = leadMasterRepository.getAllLeadListByquery(userId);


				for(int i=0; i < leadMasterDtoList.size(); i++) {
					((LeadResponseDto)(leadMasterDtoList.get(i))).setHistory(historyRepository.getHistoryOfLead(((LeadResponseDto)(leadMasterDtoList.get(i))).getStudentId()));
				}

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
		} catch (NullPointerException ignored) {}
		return response;
	}

	@GetMapping(value = "getAllLeadFromStatus", params = {"lead_status"})
	public List getAllLeadFromStatus(
			@RequestParam("lead_status") Long leadStatus
	) {
		List list =  leadMasterRepository.getLeadsByRemark(leadStatus);
		return list;
	}

	@GetMapping(value = "getAllLeadFromStatusByEmp", params = {"lead_status", "userId"})
	public List getAllLeadFromStatusByEmp(
			@RequestParam("lead_status") Long remarkId,
			@RequestParam("userId") Long userId
	) {
		List list = leadMasterRepository.getAllLeadFromStatusByEmp(remarkId, userId);

		for(int i=0; i < list.size(); i++) {
			((LeadResponseDto)(list.get(i))).setHistory(historyRepository.getHistoryOfLead(((LeadResponseDto)(list.get(i))).getStudentId()));
		}

		return list;
	}

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
			 if(roleMaster.getRoleName().equalsIgnoreCase("Project manager") || roleMaster.getRoleName().equalsIgnoreCase("Director") || roleMaster.getRoleName().equalsIgnoreCase("Founder")) {
//				 Admin // All leads
					List list = leadMasterService.getAllLeadRecordService();
					if(list!=null) {
						 response.setResult(list);
					}
					else {
						//Data not present
						 response.setResult(list);
						response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
						response.setMessage("Data is not Present");
					}
			 } else if (roleMaster.getRoleName().equalsIgnoreCase("Admissions counsellor") || roleMaster.getRoleName().equalsIgnoreCase("Verification counsellor")) {
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

	 // update Lead by id
	// updated by Laukik
	 @PutMapping("/updateLeadByid/{id}")
	 @ResponseBody public ResponseVO updateLeadController(
	 		@RequestParam(value = "userId") Long userId,
			@PathVariable(value = "id") Long studentId,
			@RequestBody LeadMasterDto leadMasterDto
	 ) {
		 ResponseVO<LeadMasterDto> responseVO = new ResponseVO<LeadMasterDto>();
		 boolean flag = leadMasterService.updateLeadService(userId, leadMasterDto);
		  if(flag){
			  responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
			  historyRepository.insertHistory(new History(leadMasterDto.getComments() == null ? leadMasterDto.getComments():"Updated" ,new Date(), employeeService.getEmployeeByUserId(userId), leadJpaMasterRepository.findByStudentId(studentId), remarkService.getRemarkById(leadMasterDto.getRemarkId())));
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
	public Map<String, Boolean> deleteLead(
			@PathVariable(value = "id") Long studentId,
			@RequestParam(value = "userId") Long empUserId
	) throws ResourceNotFoundException {
		LeadMaster leadMaster = leadJpaMasterRepository.findById(studentId)
	   .orElseThrow(() -> new ResourceNotFoundException("Student Id not found for this id :: " + studentId));
		leadMaster.setDeletedFlag(false);
		leadJpaMasterRepository.save(leadMaster);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deletedFlag", Boolean.TRUE);
		historyRepository.insertHistory(new History("Deleted Lead by Employee", new Date(), employeeService.getEmployeeByUserId(empUserId), leadJpaMasterRepository.findByStudentId(studentId), null));
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
			 if(roleMaster.getRoleName().equalsIgnoreCase("Project manager")) {
				//Admin // All leads
				//List list=leadMasterService.getAllLeadRecordService();
				List list = leadMasterRepository.getAllLeadForMe();
				if(list!=null) {
					 response.setResult(list);
				}
				else {
					//Data not present
					 response.setResult(list);
					response.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
					response.setMessage("Data is not Present");
				}
			 } else if (roleMaster.getRoleName().equalsIgnoreCase("Admissions counsellor")  || roleMaster.getRoleName().equalsIgnoreCase("Verification counsellor")) {
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

	@PostMapping(value = "/verifyTheLeadAssignAutomatically")
	@ResponseBody
	public ResponseVO verifyTheLead(@RequestParam("userId") String userId, @RequestBody StudentWithInst leadId) throws Exception {
		Long empId = empLeadService.assignLeadAutomatically(leadId.getStudentId());
		EmployeeMaster employeeMaster = employeeService.getEmployeeByEmpId(empId);
		LeadMaster leadMasterObj= leadJpaMasterRepository.findByStudentIdAndDeletedFlag(leadId.getStudentId(), true);
		EmpLead empLead = new EmpLead();
		empLead.setLeadMaster(leadMasterObj);
		empLead.setEmployeeMaster(employeeMaster);
		empLead.setUpdatedOn(new Date());
		empLead.setDeletedFlag(true);
		empleadJparepository.save(empLead);
		leadMasterObj.setAssignLeadFlag(true);
		leadJpaMasterRepository.save(leadMasterObj);
		LeadMaster leadMaster = leadMasterService.getLeadByStudentId(leadId.getStudentId());
		historyRepository.insertHistory(new History("Verified" ,new Date(), employeeService.getEmployeeByUserId(Long.parseLong(userId)),  leadMaster, leadMaster.getRemarkMaster()));
		for(Long instituteId : leadId.getInstituteIds()) {
			InstituteMaster instituteMaster = instituteRepository.getInstituteById(instituteId);
			instituteLeadRepository.insertInstituteLead(new InstituteLead(new Date(), instituteMaster, leadMaster, employeeService.getEmployeeByUserId(Long.parseLong(userId))));
			String email = instituteMaster.getEmailId();
			sendEmail(leadMaster, email.toLowerCase());
			if(
			instituteMaster.getInstituteName().equalsIgnoreCase("Aditya Group Of Institutes") ||
			instituteMaster.getInstituteName().equalsIgnoreCase("npfs") ||
			instituteMaster.getInstituteName().equalsIgnoreCase("upskillutoday")) {
				this.adityaGroupOfIstituteSendLead(leadMaster);
			}

		}

		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatusCode("200");
		responseVO.setMessage("All Done");
		responseVO.setResult("All Leads are assigned");
		return responseVO;
	}

	// For sending mail through method change method body to use
	private void sendEmail(LeadMaster leadMaster, String email) throws Exception{
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email);
		String stringBuilder = "Congratulations!\n" +
				"Students are coming your way. upskillUtoday brings you a new prospect." +
				"\n" + "Student Detail" +
				"\n" + "Student Name : " + leadMaster.getStudentName() +
				"\n" + "Contact No : " + leadMaster.getContactNo() +
				"\n" + "Email ID : " + leadMaster.getEmailId() +
				"\n" + "Course Name : " + leadMaster.getCategoryMaster().getCategoryName() +
				"\n\nThank you";
		helper.setText(stringBuilder);
		helper.setSubject("New UpSkilluToday Student enquiry");
		sender.send(message);
	}

	private void adityaGroupOfIstituteSendLead(LeadMaster leadMaster) throws IOException {
		String course_name = "";

		if(leadMaster.getCategoryMaster().getCategoryName().equalsIgnoreCase("Design")) {
			course_name = "Design Studies";
		} else if(architectureSub.contains(leadMaster.getSubCategoryMaster().getSubCategoryName())) {
			course_name = "Architecture";
		} else if(managementSub.contains(leadMaster.getSubCategoryMaster().getSubCategoryName())) {
			course_name = "Management";
		} else {
			course_name = "NSDC"; //9665330497
		}

		OkHttpClient client = new OkHttpClient().newBuilder()
				.build();
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(
				"{\r\n    " +
						"\"college_id\": \"217\",\r\n   " +
						"\"name\": \" " + leadMaster.getStudentName() + " " +
						"\",\r\n    \"email\": \" " + leadMaster.getEmailId().toLowerCase() + " " +
						"\",\r\n    \"country_dial_code\": \"+91\",\r\n   " +
						"\"mobile\": \" " + leadMaster.getContactNo() + " \",\r\n    " +
						"\"source\": \"upskillUtoday\",\r\n   " +
						" \"state\": \"Maharashtra\",\r\n    " +
						"\"city\": \" " + leadMaster.getCity() + " \",\r\n    " +
						"\"course\": \" " + course_name + " \",\r\n    " +
						"\"secret_key\": \"df1441a8ad21ec533d98221629b8f8a1\"\r\n}",
				mediaType);
		Request request = new Request.Builder()
				.url("https://api.nopaperforms.com/dataPorting/217/upskillutoday")
				.method("POST", body)
				.addHeader("Content-Type", "application/json")
				.build();
		Response response = client.newCall(request).execute();
		String res = response.body().string();
		System.out.println(res);
	}

	@GetMapping(value = "/getHistory")
	public List getHistory(@RequestParam("studentId") Long studentId) {
		return historyRepository.getHistoryOfLead(studentId);
	}

	@GetMapping(value = "/webhook")
	public String webhook() {
		return "Hello Webhook";
	}
}
