package com.upskillutoday.crmRoot.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.upskillutoday.crmRoot.model.LeadMaster;

public interface FileStorageService {
	// public void init();

	  public Long save(MultipartFile file);

	public List<LeadMaster> findAll();
}
