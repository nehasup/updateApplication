package com.upskillutoday.crmRoot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.RemarkDto;
import com.upskillutoday.crmRoot.model.CityMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import com.upskillutoday.crmRoot.repository.RemarkRepository;
import com.upskillutoday.crmRoot.service.RemarkService;

@Service
public class RemarkServiceImpl implements RemarkService {
	
	@Autowired
	private RemarkRepository remarkRepository;

	@Override
	public boolean insertRemarkService(RemarkDto remarkDto) {
		RemarkMaster remark =new RemarkMaster();
		remark.setRemarkName(remarkDto.getRemarkName());
	    Date date = new Date();
	    remark.setUpdatedOn(date);
	    remark.setDeletedFlag(true);
        boolean flag=remarkRepository.insertRemarkDao(remark);
        return flag;
	}

	@Override
	public List getAllRecordRemarkService() {
		List  list = remarkRepository.getRemarkListDao();
        return list;
	}

	@Override
	public boolean getRecordByRemarkIdService(RemarkDto remarkDto) {
		 
		RemarkMaster remark = new RemarkMaster();
		remark.setRemarkId(remarkDto.getRemarkId());
	    
		boolean flag = remarkRepository.getRecordByRemarkIdDao(remark);
        remarkDto.setRemarkName(remark.getRemarkName());
      
        return flag;
	}

	@Override
	public String getRemarkStatus(Long id) {
		for(Object remarkMaster : this.getAllRecordRemarkService()) {
			if(id.equals(((RemarkMaster)remarkMaster).getRemarkId()))
				return ((RemarkMaster)remarkMaster).getRemarkName();
		}
		return null;
	}
}
