package com.upskillutoday.crmRoot.repository.impl;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upskillutoday.crmRoot.model.InstituteMaster;
import com.upskillutoday.crmRoot.repository.InstituteRepository;

import java.util.Date;
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
	public void updateInstitute(InstituteMaster instituteMaster) {
		try {
			entityManager.merge(instituteMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List getInstituteByCategoryFromStudentId(Long stduentId) {
    return entityManager
        .createQuery(
            "SELECT DISTINCT new InstituteNameResponse (im.instituteId, im.instituteName) FROM InstituteMaster as im\n"
                + "    inner join CategoryMaster as c on im.categoryMaster.categoryId = c.categoryId\n"
                + "    where im.categoryMaster.categoryId = " + stduentId + " and im.instituteName IS NOT NULL and im.contactNo IS NOT NULL")
        .getResultList();
	}

	@Override
	public InstituteMaster getInstituteById(Long id) {
		return entityManager
				.createQuery(
						"SELECT im FROM InstituteMaster as im\n"
								+ "    where im.instituteId = " + id + " and im.instituteName IS NOT NULL and im.contactNo IS NOT NULL", InstituteMaster.class)
				.getSingleResult();
	}

	@Override
	public List getInstituteReport() {
    return entityManager
        .createQuery(
            "select new InstituteReport (im.instituteId, im.instituteName, e.employeeName,  COUNT(h.employeeMaster.employeeId))  from InstituteMaster as im\n"
                + "    inner join InstituteLead as il on im.instituteId = il.instituteMaster.instituteId \n"
                + "    inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId \n"
                + "    inner join History as h on lm.studentId = h.leadMaster.studentId \n"
                + "    inner join EmployeeMaster as e on h.employeeMaster.employeeId = e.employeeId \n"
                + "    where h.comment = 'Verified' and il.sentOn = DATE( '" + (new Date()).toString() + "' ) and im.instituteName IS NOT NULL and im.contactNo IS NOT NULL\n"
                + "    group by il.instituteMaster.instituteId, h.employeeMaster.employeeId ")
        .getResultList();
	}

	@Override
	public List getInstituteWithZero() {
    return entityManager
        .createQuery(
            "select new InstituteReport (im.instituteName)  from InstituteMaster as im \n"
                + " where im.instituteName not in (select im2.instituteName from InstituteMaster as im2 "
				+ " inner join InstituteLead as il on im2.instituteId = il.instituteMaster.instituteId "
				+ " inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId "
                + " inner join History as h on lm.studentId = h.leadMaster.studentId "
                + " inner join EmployeeMaster as e on h.employeeMaster.employeeId = e.employeeId "
                + " where h.comment = 'Verified' and im2.instituteName IS NOT NULL and im2.contactNo IS NOT NULL"
                + " group by il.instituteMaster.instituteId, h.employeeMaster.employeeId )"
                + " group by im.instituteId ")
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
						+ "    where h.comment = 'Verified' and il.sentOn = DATE( '" + date + "' ) and im.instituteName IS NOT NULL and im.contactNo IS NOT NULL \n"
						+ "    group by h.employeeMaster.employeeId , il.instituteMaster.instituteId")
        .getResultList();
	}

	@Override
	public List getInstituteReportDatewiseWithZero(String date) {
		return entityManager
				.createQuery("select new InstituteReport (im2.instituteName)  from InstituteMaster as im2 \n"
						+ " where im2.instituteName not in (select im.instituteName from InstituteMaster as im\n"
								+ "    inner join InstituteLead as il on im.instituteId = il.instituteMaster.instituteId \n"
								+ "    inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId \n"
								+ "    inner join History as h on lm.studentId = h.leadMaster.studentId \n"
								+ "    inner join EmployeeMaster as e on h.employeeMaster.employeeId = e.employeeId \n"
								+ "    where h.comment = 'Verified' and il.sentOn = DATE( '" + date + "' ) and im.instituteName IS NOT NULL and im.contactNo IS NOT NULL \n"
								+ "    group by h.employeeMaster.employeeId , il.instituteMaster.instituteId)"
								+ " group by im2.instituteId ")
				.getResultList();
	}

	@Override
	public List getInstitute() {
		return entityManager.createQuery(
				"SELECT im FROM InstituteMaster as im where im.instituteName IS NOT NULL and im.contactNo IS NOT NULL",
				InstituteMaster.class
		).getResultList();
	}

	@Override
	public List getInstituteOfStudent(Long studentId) {
    return entityManager
        .createQuery(
            "SELECT im.instituteName FROM InstituteLead  as il \n"
                + "    inner join il.instituteMaster as im \n"
                + "    where il.leadMaster.studentId = " + studentId + " and im.instituteName IS NOT NULL and im.contactNo IS NOT NULL", String.class
		)
        .getResultList();
	}
}
