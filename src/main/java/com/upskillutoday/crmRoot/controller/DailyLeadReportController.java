package com.upskillutoday.crmRoot.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/dailydateReportLead")
	public List<LeadReportRes> dailyLeadReport(@RequestBody DailyLeadReportDto dailyLeadReportDto) throws ParseException {
		
		List<LeadReportRes> list=dailyLeadReportService.getDailyLeadReportService(dailyLeadReportDto);
	
		return list;
	}

}
