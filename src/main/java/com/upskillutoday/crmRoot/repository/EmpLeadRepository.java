package com.upskillutoday.crmRoot.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.EmpLead;

@Repository
public interface EmpLeadRepository {
	boolean addEmpLead(EmpLead empLead);
	List getAllLeadsFromEmployeeId(Long empId);
	List getEmpLeadFromStudentId(Long leadId);
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

	@Override
	public List getAllLeadsFromEmployeeId(Long empId) {
		return entityManager.createQuery(
				"SELECT new LeadMasterDto( lm ) FROM LeadMaster as lm " +
						"inner join EmpLead as el on el.leadMaster.studentId = lm.studentId " +
						"where el.employeeMaster.employeeId = " + empId
		).getResultList();
	}

	@Override
	public List getEmpLeadFromStudentId(Long leadId) {
		return entityManager.createQuery(
				"SELECT el FROM EmpLead as el " +
						"WHERE el.leadMaster.studentId = " + leadId,
				EmpLead.class
		).getResultList();
	}
}
