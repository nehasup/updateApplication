package com.upskillutoday.crmRoot.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.repository.DailyLeadReportRepository;
import com.upskillutoday.crmRoot.repository.EmpLeadJpaRepository;
import com.upskillutoday.crmRoot.repository.EmployeeJpaRepository;
import com.upskillutoday.crmRoot.repository.LeadJpaMasterRepository;
import com.upskillutoday.crmRoot.request.DailyLeadReportDto;
import com.upskillutoday.crmRoot.response.LeadReportRes;
import com.upskillutoday.crmRoot.response.LeadResponseDto;
import com.upskillutoday.crmRoot.service.DailyLeadReportService;
import com.upskillutoday.crmRoot.service.LeadMasterService;
import com.upskillutoday.crmRoot.service.RemarkService;

@Service
public class DailyLeadReportServiceImpl  implements DailyLeadReportService{
	
	@Autowired
	private LeadJpaMasterRepository leadJpaMasterRepository;
	
	@Autowired
	private DailyLeadReportRepository dailyLeadReportRepository;
	
	@Autowired
	private LeadMasterService leadMasterService;
	
	@Autowired
	private RemarkService remarkService;
	
	@Autowired
	private EmpLeadJpaRepository empLeadJpaRepository;
	
	@Autowired
	private EmployeeJpaRepository employeeJpaRepository;
	

	@Override
	public List<LeadReportRes> getDailyLeadReportService(DailyLeadReportDto dailyLeadReportDto) throws ParseException {

		List<LeadMaster> leadMasterList = dailyLeadReportRepository.findByUpdatedOn(dailyLeadReportDto.getUpdatedOn());
		ArrayList<LeadReportRes> leadReportRess = new ArrayList<>();

		

		for(LeadMaster leadMaster : leadMasterList) {
			
//			EmpLead empLead =empLeadJpaRepository.findByLeadMaster(leadMaster);
//			if(empLead!=null) {
				Long employeeId =leadMaster.getUpdatedBy();
		
				EmployeeMaster employeeMaster =employeeJpaRepository.findByEmployeeIdAndDeletedFlag(employeeId, true);
				String status = remarkService.getRemarkStatus(leadMaster.getRemarkMaster().getRemarkId());
				leadReportRess.add(new LeadReportRes(leadMaster.getStudentName(), 
						leadMaster.getContactNo(), 
						leadMaster.getCity(),
						leadMaster.getArea(), leadMaster.getAddress(), 
						leadMaster.getEmailId(), leadMaster.getCourseName(), leadMaster.getComments(), 
						leadMaster.getInstituteName(), status,employeeMaster.getEmployeeName()));
			//}
		}
		
		return leadReportRess;
//		LeadMaster leadMaster = new LeadMaster();
//		leadMaster.setUpdatedOn(leadMasterDto.getUpdatedOn());
//		
//		ArrayList<LeadMasterDto> leadMasterDtoList = new ArrayList<LeadMasterDto>();
//	
//
//		ArrayList<LeadMaster>leadMasterList=dailyLeadReportRepository.findByUpdatedOn(leadMasterDto.getUpdatedOn());
//		LeadMasterDto leadMasterDto1 = new LeadMasterDto();
//		
//		for(LeadMaster leadMaster2:leadMasterList)
//		{
//			leadMasterDto1.setStudentId(leadMaster2.getStudentId());
//			leadMasterDto1.setStudentName(leadMaster2.getStudentName());
//			leadMasterDto1.setCity(leadMaster2.getCity());
//			leadMasterDto1.setEmailId(leadMaster2.getEmailId());
//			leadMasterDto1.setCourseName(leadMaster2.getCourseName());
//			leadMasterDto1.setComments(leadMaster2.getComments());
//			leadMasterDto1.setInstituteName(leadMaster2.getInstituteName());
//			System.out.println("asdf"+leadMasterDto1.getRemarkId());
//			leadMasterDtoList.add(leadMasterDto1);
//		}
//		return leadMasterDtoList;
	}

}
