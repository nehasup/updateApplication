package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.model.RemarkMaster;


public interface RemarkRepository {
	
	boolean insertRemarkDao(RemarkMaster remark);
	List getRemarkListDao();
	boolean getRecordByRemarkIdDao(RemarkMaster remark);
	List getRemarkWithCount();
	List getRemarkWithCountForEmployee(Long empId);
	RemarkMaster getRemarkById(Long id);
}
