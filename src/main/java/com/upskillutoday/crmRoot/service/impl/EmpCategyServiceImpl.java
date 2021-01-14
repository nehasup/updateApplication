package com.upskillutoday.crmRoot.service.impl;

import com.upskillutoday.crmRoot.model.EmpCategy;
import com.upskillutoday.crmRoot.repository.EmpCategyRepository;
import com.upskillutoday.crmRoot.service.EmpCategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpCategyServiceImpl implements EmpCategyService {

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
