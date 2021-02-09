package com.upskillutoday.crmRoot.repository;

import com.upskillutoday.crmRoot.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface HistoryRepository {
    boolean insertHistory(History history);
    List getHistory();
    List getHistoryOfEmp(Long empId);
    List getHistoryOfLead(Long studentId);
    List getHistoryOfTheDate(String date);
}

@Repository
@Transactional
class HistoryRepositoryImpl implements HistoryRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean insertHistory(History history) {
        try {
            entityManager.persist(history);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public List getHistory() {
        Query query = entityManager.createQuery("SELECT history FROM History as history ", History.class);
        return ((List<History>) query.getResultList());
    }

    @Override
    public List getHistoryOfEmp(Long empId) {
        Query query = entityManager.createQuery("SELECT history FROM History as history WHERE history.employeeMaster.employeeId = " + empId, History.class);
        return ((List<History>) query.getResultList());
    }

    @Override
    public List getHistoryOfLead(Long studentId) {
        Query query = entityManager.createQuery("SELECT history FROM History as history WHERE history.leadMaster.studentId = " + studentId, History.class);
        return ((List<History>) query.getResultList());
    }

    @Override
    public List getHistoryOfTheDate(String date) {
        Query query = entityManager.createQuery("SELECT DISTINCT history FROM History as history WHERE history.updatedOn = DATE('" + date + "')" , History.class);
        return query.getResultList();
    }
}

