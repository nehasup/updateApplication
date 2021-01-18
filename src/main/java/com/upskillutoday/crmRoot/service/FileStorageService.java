package com.upskillutoday.crmRoot.service;


import java.util.List;

import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;

public interface FileStorageService {
	// public void init();
	  Long save(MultipartFile file) throws ResourceNotFoundException;
	  List<LeadMaster> findAll();
}
