package com.upskillutoday.crmRoot.service;

import com.upskillutoday.crmRoot.model.EmpCategy;
import com.upskillutoday.crmRoot.repository.JPARepository.EmpCategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface EmpCategyService {
    boolean isEmployeeHasCategory(Long empId);
}

@Service
@Transactional
class EmpCategyServiceImpl implements EmpCategyService {

    @Autowired
    EmpCategyRepository empCategyRepository;

    @Override
    public boolean isEmployeeHasCategory(Long empId) {
        for(EmpCategy empCategy : empCategyRepository.findAll()) {
            if(empCategy.getEmployeeMaster().getEmployeeId().equals(empId))
                return true;
        }
        return false;
    }
}
