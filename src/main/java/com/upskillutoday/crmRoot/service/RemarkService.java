package com.upskillutoday.crmRoot.service;

import java.util.Date;
import java.util.List;

import com.upskillutoday.crmRoot.dto.RemarkDto;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import com.upskillutoday.crmRoot.repository.RemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface RemarkService {
	boolean insertRemarkService(RemarkDto remarkDto);
	List getAllRecordRemarkService();
	boolean getRecordByRemarkIdService(RemarkDto remarkDto);
	String getRemarkStatus(Long id);
	Long getRemarkById(String str);
	RemarkMaster getRemarkById(Long id);
	List getAllRemarkWithCount();
	List getRemarkWithCountForEmployee(Long empId);
	List getAllCountOfEmpDatewise(Long empId, String date);
}

@Service
@Transactional
class RemarkServiceImpl implements RemarkService {

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

    @Override
    public Long getRemarkById(String str) {
        for(Object obj : this.getAllRecordRemarkService()) {
            if(str.equalsIgnoreCase(((RemarkMaster) obj).getRemarkName()))
                return ((RemarkMaster) obj).getRemarkId();
        }
        return 11L;
    }

    @Override
    public RemarkMaster getRemarkById(Long id) {
        return remarkRepository.getRemarkById(id);
    }

    @Override
    public List getAllRemarkWithCount() {
        List list = remarkRepository.getRemarkWithCount();
        list.add(remarkRepository.getAllCount());
        return list;
    }

    @Override
    public List getRemarkWithCountForEmployee(Long empId) {
        List list = remarkRepository.getRemarkWithCountForEmployee(empId);
        list.add(remarkRepository.getAllCountForEmp(empId));
        return list;
    }

    @Override
    public List getAllCountOfEmpDatewise(Long empId, String date) {
        List list = remarkRepository.getReamrkWithCountForEmpDatewise(empId, date);
        list.add(remarkRepository.getAllCountForEmpDatewise(empId, date));
        return list;
    }
}
