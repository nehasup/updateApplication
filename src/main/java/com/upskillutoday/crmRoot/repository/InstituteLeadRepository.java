package com.upskillutoday.crmRoot.repository;

import com.upskillutoday.crmRoot.model.InstituteLead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface InstituteLeadRepository {
    boolean insertInstituteLead(InstituteLead instituteLead);
}

@Repository
@Transactional
class InstituteLeadRepositoryImpl implements InstituteLeadRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean insertInstituteLead(InstituteLead instituteLead) {
        try {
            entityManager.persist(instituteLead);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }
}
