package com.upskillutoday.crmRoot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.LeadMaster;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface EmpLeadRepository {
	boolean addEmpLead(EmpLead empLead);
}

@Repository
class EmpLeadRepositoryImpl implements EmpLeadRepository {
	  @Autowired
	     private EntityManager entityManager;

	@Override
	public boolean addEmpLead(EmpLead empLead) {
		try {
			entityManager.persist(empLead);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
