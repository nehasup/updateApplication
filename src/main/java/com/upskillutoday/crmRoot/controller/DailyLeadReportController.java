package com.upskillutoday.crmRoot.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.request.DailyLeadReportDto;
import com.upskillutoday.crmRoot.response.LeadReportRes;
import com.upskillutoday.crmRoot.service.DailyLeadReportService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class DailyLeadReportController {
	
	@Autowired
	DailyLeadReportService dailyLeadReportService;
	
	@GetMapping("/dailydateReportLead")
	public List<LeadReportRes> dailyLeadReport(
			@RequestParam("date") String date
	) throws ParseException {
		List<LeadReportRes> list = dailyLeadReportService.getDailyLeadReportService(date);
		return list;
	}
}
