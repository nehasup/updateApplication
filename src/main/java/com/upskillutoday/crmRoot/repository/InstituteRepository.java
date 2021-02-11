package com.upskillutoday.crmRoot.repository;
import com.upskillutoday.crmRoot.model.InstituteMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public interface InstituteRepository {
	boolean insertInsituteDao(InstituteMaster institute);
	List getInstituteByCategoryFromStudentId(Long catId);
	InstituteMaster getInstituteById(Long id);
	List getInstituteReportDatewise(String date);
	List getInstituteReportDatewiseWithZero(String date);
	List getInstitute();
	List getInstituteOfStudent(Long studentId);
	void updateInstitute(InstituteMaster instituteMaster);
	Long getTotalCount(Long instituteId);
	Long getTotalCountByDate(Long instituteId, String date);
	Long getHighestId();
	List getEmployeeName(Long instId, String date);
}

@Repository
@Transactional
class InstituteRepositoryImpl implements InstituteRepository {

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
	public Long getTotalCount(Long instituteId) {
		return entityManager
				.createQuery(
						"select COUNT(lm.studentId) from InstituteLead as il\n"
								+ "    inner join InstituteMaster as im on il.instituteMaster.instituteId = im.instituteId\n"
								+ "    inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId " +
								"	where im.instituteId = " + instituteId + " and il.shouldCount = 1",
						Long.class)
				.getSingleResult();
	}

	@Override
	public Long getTotalCountByDate(Long instituteId, String date) {
		return entityManager
				.createQuery(
						"select COUNT(lm.studentId) from InstituteLead as il\n"
								+ "    inner join InstituteMaster as im on il.instituteMaster.instituteId = im.instituteId\n"
								+ "    inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId " +
								"	   where im.instituteId = " + instituteId + " and il.sentOn = DATE('" + date + "') and il.shouldCount = 1",
						Long.class)
				.getSingleResult();
	}

	@Override
	public Long getHighestId() {
		return entityManager.createQuery(
				"SELECT ins.instituteId FROM InstituteMaster as ins " +
						"ORDER By ins.instituteId desc "
				, Long.class)
				.setFirstResult(0)
				.setMaxResults(1)
				.getSingleResult();
	}

	@Override
	public List getEmployeeName(Long instituteId, String date) {
    return entityManager
        .createQuery(
            "select e.employeeName from InstituteLead as il\n"
                + "    inner join EmployeeMaster as e on il.employeeMaster.employeeId = e.employeeId\n"
                + "    where il.instituteMaster.instituteId = "+ instituteId +" and il.sentOn = DATE('" + date + "')"
                + "    group by e.employeeId")
        .getResultList();
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
						"SELECT im FROM InstituteMaster as im \n"
								+ "    where im.instituteId = " + id, InstituteMaster.class)
				.getSingleResult();
	}

	@Override
	public List getInstituteReportDatewise(String date) {
		return entityManager
				.createQuery(
						"select new InstituteReport (im.instituteId, im.instituteName, e.employeeName,  COUNT(lm.studentId), im.commitedLead) from InstituteLead as il\n" +
								"    inner join InstituteMaster as im on il.instituteMaster.instituteId = im.instituteId\n" +
								"    inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId \n" +
								"    inner join EmployeeMaster as e on il.employeeMaster.employeeId = e.employeeId \n" +
								"	 where il.sentOn = DATE('" + date + "') and il.shouldCount = 1" +
								"    group by im.instituteId ")
				.getResultList();
	}

	@Override
	public List getInstituteReportDatewiseWithZero(String date) {
		return entityManager
				.createQuery("select new InstituteReport (im2.instituteId, im2.instituteName, im2.commitedLead) from InstituteMaster as im2 " +
						"where im2.instituteId not in (select im.instituteId from InstituteLead as il\n" +
						"    inner join InstituteMaster as im on il.instituteMaster.instituteId = im.instituteId\n" +
						"    inner join LeadMaster as lm on il.leadMaster.studentId = lm.studentId \n" +
						"    inner join EmployeeMaster as e on il.employeeMaster.employeeId = e.employeeId \n" +
						"	 where il.sentOn = DATE('" + date + "') and il.shouldCount = 1" +
						"    group by im.instituteId )")
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
								+ "    where il.leadMaster.studentId = " + studentId + " and im.instituteName IS NOT NULL ", String.class
				)
				.getResultList();
	}
}
