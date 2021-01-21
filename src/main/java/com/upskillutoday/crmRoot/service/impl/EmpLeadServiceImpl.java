package com.upskillutoday.crmRoot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import com.upskillutoday.crmRoot.repository.CategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.EmpLeadJpaRepository;
import com.upskillutoday.crmRoot.repository.EmployeeJpaRepository;
import com.upskillutoday.crmRoot.repository.LeadJpaMasterRepository;
import com.upskillutoday.crmRoot.repository.RemarkJpaRepository;
import com.upskillutoday.crmRoot.repository.impl.EmpLeadRepository;
import com.upskillutoday.crmRoot.response.EmpLeadResponseDto;
import com.upskillutoday.crmRoot.response.LeadResponseDto;
import com.upskillutoday.crmRoot.service.EmpLeadService;
import com.upskillutoday.crmRoot.service.LeadMasterService;

@Service
public class EmpLeadServiceImpl implements EmpLeadService {
	
	@Autowired
	EmpLeadJpaRepository empleadJparepository;
	
	@Autowired
	EmpLeadRepository empLeadRepository;
	
	@Autowired
	LeadMasterService leadMasterService;
	
	@Autowired
	LeadJpaMasterRepository leadJpaMasterRepository;
	
	@Autowired
	EmployeeJpaRepository employeeJpaRepository;
	
	@Autowired
	CategoryJpaRepository categoryJpaRepository;
	
	@Autowired
	RemarkJpaRepository remarkJpaRepository;

	@Override
	public List<LeadMasterDto>  getAllAssignEmpLeadRecordService() {
		
		List<LeadMaster> leadMasterlist = leadMasterService.getAllLeadByAssignFlag();
		
		List<EmpLead> empList =  new ArrayList<EmpLead>();
		
		empList=empleadJparepository.findByDeletedFlag(true);
		
		List<LeadMasterDto> leadMasterDtos = new ArrayList<LeadMasterDto>();
		if(leadMasterlist!=null) {
			for(LeadMaster lead:leadMasterlist) {
				
				Long studentId=lead.getStudentId();
				
				LeadMaster leadMaster= leadJpaMasterRepository.findByStudentId(studentId);
				if(leadMaster==null) {
				System.out.println("not list");	
				}else {
					LeadMasterDto leadMasterDto1 = new LeadMasterDto();
					EmpLead empLead1 = empleadJparepository.findByLeadMaster(leadMaster);
						if(empLead1==null) {
								leadMasterDto1.setEmployeeName("Not Assign");
								leadMasterDto1.setEmployeeId(null);
				
							}else {
								EmployeeMaster employeeMaster = employeeJpaRepository.findByEmployeeIdAndDeletedFlag(empLead1.getEmployeeMaster().getEmployeeId(), true);
				
								leadMasterDto1.setEmployeeName(employeeMaster.getEmployeeName());
								leadMasterDto1.setEmployeeId(employeeMaster.getEmployeeId());
								}
					
				
		        	 
					leadMasterDto1.setStudentId(leadMaster.getStudentId());
					leadMasterDto1.setStudentName(leadMaster.getStudentName());
					leadMasterDto1.setCourseName(leadMaster.getCourseName());
					leadMasterDto1.setContactNo(leadMaster.getContactNo());
					leadMasterDto1.setArea(leadMaster.getArea());
					leadMasterDto1.setCity(leadMaster.getCity());
					leadMasterDto1.setEmailId(leadMaster.getEmailId());
					leadMasterDto1.setModeOfCourse(leadMaster.getModeOfCourse());
					leadMasterDto1.setAddress(leadMaster.getAddress());
					leadMasterDto1.setBudget(leadMaster.getBudget());		
					leadMasterDto1.setComments(leadMaster.getComments());
					leadMasterDto1.setInstituteName(leadMaster.getInstituteName());
					
					CategoryMaster categoryMaster = categoryJpaRepository.findByCategoryId(leadMaster.getCategoryMaster().getCategoryId());
					//leadMasterDto1.setCategoryMaster(categoryMaster);
					leadMasterDto1.setCategoryName(categoryMaster.getCategoryName());
					leadMasterDto1.setCategoryId(categoryMaster.getCategoryId());
					
					RemarkMaster remarkMaster = remarkJpaRepository.findByRemarkId(leadMaster.getRemarkMaster().getRemarkId());
				//	leadMasterDto1.setRemarkMaster(remarkMaster);
					leadMasterDto1.setRemarkId(remarkMaster.getRemarkId());
					leadMasterDto1.setRemarkName(remarkMaster.getRemarkName());
				
				
		     		//leadMaster.setUpdatedOn(new Date());
		     		//leadMaster.setDeletedFlag(true);
		     		//leadMaster.setAssignLeadFlag(false);
					
					leadMasterDtos.add(leadMasterDto1);      
					
				}
				
			}
				}
				
				
			
		return leadMasterDtos;
	}

	@Override
	public void setAllLeadToThisEmployee(List<LeadMaster> leadMasters, EmployeeMaster employeeMaster) {
		for(LeadMaster leadMaster : leadMasters) {
			EmpLead empLead = new EmpLead();

			empLead.setEmployeeMaster(employeeMaster);
			empLead.setLeadMaster(leadMaster);
			empLead.setUpdatedOn(new Date());
			empLead.setDeletedFlag(true);

			empLeadRepository.addEmpLead(empLead);
		}
	}
}
