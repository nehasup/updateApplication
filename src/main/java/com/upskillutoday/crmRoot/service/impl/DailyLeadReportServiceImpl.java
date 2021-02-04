package com.upskillutoday.crmRoot.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.upskillutoday.crmRoot.model.History;
import com.upskillutoday.crmRoot.repository.*;
import com.upskillutoday.crmRoot.response.InstituteReport;
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
	private HistoryRepository historyRepository;

	@Autowired
	private InstituteRepository instituteRepository;

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
						instituteRepository.getInstituteOfStudent(history.getLeadMaster().getStudentId()), history.getRemarkMaster().getRemarkName(), history.getEmployeeMaster().getEmployeeName()));
		}
		return leadReportRess;
	}

	@Override
	public List getInstituteTotalReport() {
		Date date = new Date();
		List list = instituteRepository.getInstituteReport();
		ArrayList<InstituteReport> newList = new ArrayList();
		for(InstituteReport instituteReport : (List<InstituteReport>) list) {
			instituteReport.setTotalCount(instituteRepository.getTotalCount(instituteReport.getId()));
			newList.add(instituteReport);
		}

		newList.addAll(instituteRepository.getInstituteWithZero());
		return newList;
	}

	@Override
	public List getInstituteTotalReportDateWise(String date) {
		List list = instituteRepository.getInstituteReportDatewise(date);

		ArrayList<InstituteReport> newList = new ArrayList();
		for(InstituteReport instituteReport : (List<InstituteReport>) list) {
			instituteReport.setTotalCount(instituteRepository.getTotalCountByDate(instituteReport.getId(), date));
			newList.add(instituteReport);
		}

		newList.addAll(instituteRepository.getInstituteReportDatewiseWithZero(date));
		return list;
	}
}
