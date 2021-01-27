package com.upskillutoday.crmRoot.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.upskillutoday.crmRoot.model.History;
import com.upskillutoday.crmRoot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
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

	@Autowired
	private HistoryRepository historyRepository;
	

	@Override
	public List<LeadReportRes> getDailyLeadReportService(String date) throws ParseException {
		List<History> histories = historyRepository.getHistoryOfTheDate(date);
		ArrayList<LeadReportRes> leadReportRess = new ArrayList<>();
		for(History history: histories) {
				Long employeeId = history.getLeadMaster().getUpdatedBy();
				leadReportRess.add(new LeadReportRes(history.getLeadMaster().getStudentName(),
						history.getLeadMaster().getContactNo(),
						history.getLeadMaster().getCity(),
						history.getLeadMaster().getArea(), history.getLeadMaster().getAddress(),
						history.getLeadMaster().getEmailId(), history.getLeadMaster().getCourseName(), history.getLeadMaster().getComments(),
						history.getLeadMaster().getInstituteName(), history.getRemarkMaster().getRemarkName(), history.getEmployeeMaster().getEmployeeName()));
		}
		return leadReportRess;
	}
}
