package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.model.EmployeeMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeRepository {
	boolean insertEmployeeDao(EmployeeMaster employee);
	List getAllEmpListDao();
	EmployeeMaster getRecordByEmployeeIdDao(EmployeeMaster employee);
	boolean updateEmployeeRepository(EmployeeMaster employee);
	Long getEmployeeIdByUserId(Long userId);
	EmployeeMaster getEmployeeByUserId(Long userId);
	EmployeeMaster getEmployeeByEmpId(Long empId);
	List getAllVerificationCounsellor();
	List getEmployeeFromCategory(Long studentId);
	List getEmployeeFromCat(Long catId);
	Long getEmployeeByCategory(Long cat);
	List getVerifiactionAndAdmissionConusellor();
	List getVerificationConsellorByCategory(Long catId);
	List getCatsByEmpId(Long empId);
}

@Repository
@Transactional
class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean insertEmployeeDao(EmployeeMaster employee) {
		try {
			entityManager.persist(employee);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List getAllEmpListDao() {
		return entityManager.createQuery(
				"Select NEW EmployeeResponseDto(emp.employeeId, emp.employeeName, emp.contactNo ,emp.guardianNo , emp.emailId , emp.address, emp.birthDate, emp.gender, c.categoryName, c.categoryId)"
					+ " from EmployeeMaster as emp" +
						" left join emp.category as c" +
						" where emp.deletedFlag = true"
		).getResultList();
	}

	@Override
	public EmployeeMaster getRecordByEmployeeIdDao(EmployeeMaster employee) {
		try {
			Query query = entityManager.createQuery("SELECT em From EmployeeMaster as em where em.employeeId = :id");
			query.setParameter("id", employee.getEmployeeId());
			EmployeeMaster employee2 = (EmployeeMaster) query.getSingleResult();
			return employee2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateEmployeeRepository(EmployeeMaster employee) {
		try {
			entityManager.merge(employee);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Long getEmployeeIdByUserId(Long userId) {
		return entityManager.createQuery(
				"SELECT emp.employeeId FROM EmployeeMaster as emp " +
						"inner join emp.userMaster as us " +
						"where us.userId = " + userId, Long.class
		).getSingleResult();
	}

	@Override
	public EmployeeMaster getEmployeeByUserId(Long userId) {
		return entityManager.createQuery(
				"SELECT emp FROM EmployeeMaster as emp " +
						"inner join emp.userMaster as um " +
						"where um.userId = " + userId
				, EmployeeMaster.class
		).getSingleResult();
	}

	@Override
	public EmployeeMaster getEmployeeByEmpId(Long empId) {
		return entityManager.createQuery(
				"SELECT emp FROM EmployeeMaster as emp where emp.employeeId = " + empId,
				EmployeeMaster.class
		).getSingleResult();
	}

	@Override
	public List getAllVerificationCounsellor() {
		return entityManager
				.createQuery(
						"SELECT em From EmployeeMaster as em\n"
								+ "    inner join UserMaster as um on em.userMaster.userId = um.userId\n"
								+ "    inner join UserRole as ur on um.userId = ur.users.userId\n"
								+ "    where ur.roles.roleId in (\n"
								+ "        SELECT rm.roleId FROM RoleMaster as rm where rm.roleName = 'Verification counsellor')")
				.getResultList();
	}

	@Override
	public List getEmployeeFromCategory(Long studentId) {
		return entityManager
				.createQuery(
						"SELECT em FROM EmployeeMaster as em \n"
								+ "    inner join EmpCategy as ec on em.employeeId = ec.employeeMaster.employeeId \n"
								+ "    inner join UserMaster as um on em.userMaster.userId = um.userId\n"
								+ "    inner join UserRole as ur on um.userId = ur.users.userId \n" +
								"      inner join EmpLead as el on el.employeeMaster.employeeId = em.employeeId \n" +
								"      inner join LeadMaster as lm on lm.studentId = el.leadMaster.studentId \n"
								+ "   where ec.categoryMaster.categoryId = ( " +
								"SELECT lm.categoryMaster.categoryId FROM LeadMaster lm " +
								"where lm.studentId = " + studentId +
								") and ur.roles.roleId = 11 " +
								"GROUP BY em.employeeId " +
								"ORDER BY COUNT(lm.studentId) " ,EmployeeMaster.class)
				.getResultList();
	}

	@Override
	public List getEmployeeFromCat(Long catId) {
		return entityManager
				.createQuery(
						"SELECT em FROM EmployeeMaster as em \n"
								+ "    inner join EmpCategy as ec on em.employeeId = ec.employeeMaster.employeeId \n"
								+ "    inner join UserMaster as um on em.userMaster.userId = um.userId\n"
								+ "    inner join UserRole as ur on um.userId = ur.users.userId \n" +
								"      inner join EmpLead as el on el.employeeMaster.employeeId = em.employeeId \n" +
								"      inner join LeadMaster as lm on lm.studentId = el.leadMaster.studentId \n"
								+ "   where ec.categoryMaster.categoryId = " + catId + " and ur.roles.roleId = 11 " +
								"GROUP BY em.employeeId " +
								"ORDER BY COUNT(lm.studentId) " ,EmployeeMaster.class)
				.getResultList();
	}

	@Override
	public Long getEmployeeByCategory(Long catId) {
		return entityManager
				.createQuery(
						"SELECT em.employeeId FROM EmployeeMaster as em \n"
								+ "    inner join EmpCategy as ec on em.employeeId = ec.employeeMaster.employeeId \n"
								+ "    inner join UserMaster as um on em.userMaster.userId = um.userId\n"
								+ "    inner join UserRole as ur on um.userId = ur.users.userId \n"
								+ "    where ec.categoryMaster.categoryId = " + catId
								+ "\n and ur.roles.roleId = 11"
								+ ")",
						Long.class)
				.getSingleResult();
	}

	@Override
	public List getVerifiactionAndAdmissionConusellor() {
		return entityManager
				.createQuery(
						"SELECT new EmployeeNameWithId (em.employeeId, em.employeeName ) FROM EmployeeMaster as em \n"
								+ "       inner join UserMaster um on em.userMaster.userId = um.userId \n"
								+ "       inner join UserRole ur on um.userId = ur.users.userId \n"
								+ "       inner join RoleMaster rm on ur.roles.roleId = rm.roleId \n"
								+ "where rm.roleName = 'Admissions counsellor' or rm.roleName = 'Verification counsellor'")
				.getResultList();
	}

	@Override
	public List getVerificationConsellorByCategory(Long catId) {
    return entityManager
        .createQuery(
            "select e \n"
                + "from EmployeeMaster as e\n"
                + "inner join e.userMaster as um \n"
                + "inner join UserRole as ur on ur.users.userId = um.userId \n"
                + "inner join EmpCategy as ec on e.employeeId = ec.employeeMaster.employeeId \n"
                + "  left join EmpLead as el on e.employeeId = el.employeeMaster.employeeId"
                + "  left join LeadMaster as lm on el.leadMaster.studentId = lm.studentId \n"
                + "where ec.categoryMaster.categoryId = "
                + catId
                + " and ur.roles.roleId = 10 "
                + " group by e.employeeId \n"
                + " order by COUNT(lm.studentId) ")
        .getResultList();
	}

	@Override
	public List getCatsByEmpId(Long empId) {
		return entityManager
				.createQuery(
						"select cat.categoryName  from CategoryMaster as cat\n"
								+ "    inner join EmpCategy as ec on cat.categoryId = ec.categoryMaster.categoryId\n"
								+ "    inner join EmployeeMaster as e on ec.employeeMaster.employeeId = e.employeeId \n"
								+ "    where e.employeeId = " + empId)
				.getResultList();
	}
}
