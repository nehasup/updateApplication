package com.upskillutoday.crmRoot.repository;

import com.upskillutoday.crmRoot.model.History;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface HistoryRepository {
    boolean insertHistory(History history);
    List getHistory();
    List getHistoryOfEmp(Long empId);
    List getHistoryOfLead(Long studentId);
}
