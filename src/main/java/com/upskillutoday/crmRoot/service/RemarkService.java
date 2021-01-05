package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.RemarkDto;



public interface RemarkService {
	public boolean insertRemarkService(RemarkDto remarkDto);

	List getAllRecordRemarkService();

	boolean getRecordByRemarkIdService(RemarkDto remarkDto);

}
