package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.RemarkDto;
import com.upskillutoday.crmRoot.model.RemarkMaster;


public interface RemarkService {
	public boolean insertRemarkService(RemarkDto remarkDto);
	List getAllRecordRemarkService();
	boolean getRecordByRemarkIdService(RemarkDto remarkDto);
	String getRemarkStatus(Long id);
	Long getRemarkById(String str);
	RemarkMaster getRemarkById(Long id);
}
