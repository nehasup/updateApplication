package com.upskillutoday.crmRoot.service;

import java.util.List;
import com.upskillutoday.crmRoot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

public interface EmpLeadService {
	EmployeeMaster assignLeadAutomatically(Long studentId);
}

@Service
@Transactional
class EmpLeadServiceImpl implements EmpLeadService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public EmployeeMaster assignLeadAutomatically(Long studentId) {
        List list = employeeService.getEmployeeAutomatically(studentId);
        return (EmployeeMaster) list.get(0);
    }
}
