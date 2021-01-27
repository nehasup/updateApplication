package com.upskillutoday.crmRoot.repository.impl;

import com.upskillutoday.crmRoot.model.InstituteLead;
import com.upskillutoday.crmRoot.repository.InstituteLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class InstituteLeadRepositoryImpl implements InstituteLeadRepository {

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
