package com.upskillutoday.crmRoot.repository.impl;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upskillutoday.crmRoot.model.InstituteMaster;
import com.upskillutoday.crmRoot.repository.InstituteRepository;

import java.util.List;

@Repository
@Transactional
public class InstituteRepositoryImpl implements InstituteRepository {
	
	@Autowired
    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public boolean insertInsituteDao(InstituteMaster institute) {

		try {
			entityManager.persist(institute);
			return true;
		}
		catch (Exception e) {
			 e.printStackTrace();
	            return false;
		}
		
	}

	@Override
	public List getInstituteByCategoryFromStudentId(Long stduentId) {
    return entityManager
        .createQuery(
            "SELECT new InstituteNameResponse (im.instituteId, im.instituteName) FROM InstituteMaster as im\n"
                + "    inner join CategoryMaster as c on im.categoryMaster.categoryId = c.categoryId\n"
                + "    where im.categoryMaster.categoryId = " + stduentId)
        .getResultList();
	}

	@Override
	public InstituteMaster getInstituteById(Long id) {
		return entityManager
				.createQuery(
						"SELECT im FROM InstituteMaster as im\n"
								+ "    where im.instituteId = " + id, InstituteMaster.class)
				.getSingleResult();
	}

	@Override
	public List getInstituteReport() {
    return entityManager
        .createQuery(
            "select new InstituteReport (im.instituteName, COUNT(lm.studentId), e.employeeName,  COUNT(h.employeeMaster.employeeId))  from InstituteMaster as im\n"
                + "    inner join InstituteLead as il on im.instituteId = il.instituteMaster.instituteId \n"
                + "    inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId \n"
                + "    inner join History as h on lm.studentId = h.leadMaster.studentId \n"
                + "    inner join EmployeeMaster as e on h.employeeMaster.employeeId = e.employeeId \n"
                + "    where h.comment = 'Verified'\n"
                + "    group by h.employeeMaster.employeeId , il.instituteMaster.instituteId")
        .getResultList();
	}

	@Override
	public List getInstituteReportDatewise(String date) {
    return entityManager
        .createQuery(
				"select new InstituteReport (im.instituteName, COUNT(lm.studentId), e.employeeName,  COUNT(h.employeeMaster.employeeId))  from InstituteMaster as im\n"
						+ "    inner join InstituteLead as il on im.instituteId = il.instituteMaster.instituteId \n"
						+ "    inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId \n"
						+ "    inner join History as h on lm.studentId = h.leadMaster.studentId \n"
						+ "    inner join EmployeeMaster as e on h.employeeMaster.employeeId = e.employeeId \n"
						+ "    where h.comment = 'Verified' and il.sentOn = DATE( '" + date + "' ) \n"
						+ "    group by h.employeeMaster.employeeId , il.instituteMaster.instituteId")
        .getResultList();
	}

	@Override
	public List getInstitute() {
		return entityManager.createQuery(
				"SELECT im FROM InstituteMaster as im",
				InstituteMaster.class
		).getResultList();
	}
}
