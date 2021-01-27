package com.upskillutoday.crmRoot.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.request.DailyLeadReportDto;
import com.upskillutoday.crmRoot.response.LeadReportRes;

public interface DailyLeadReportService {
	

	List<LeadReportRes> getDailyLeadReportService(String date) throws ParseException;
}
