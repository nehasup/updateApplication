package com.upskillutoday.crmRoot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.repository.EmployeeRepository;


@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
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
	        List<String> list = null;
	        try {
	            Query query = entityManager.createQuery("Select NEW com.upskillutoday.crmRoot.response.EmployeeResponseDto( emp.employeeId as employeeId,emp.employeeName as employeeName,emp.contactNo as contactNo,emp.guardianNo as guardianNo,emp.emailId as emailId,emp.address as address,emp.birthDate as birthDate,emp.gender,c.categoryName as categoryName,c.categoryId as categoryId )"
	            		+ " from EmployeeMaster as emp inner join emp.category as c where emp.deletedFlag=1");
	             list = query.getResultList();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	

	@Override
	    public EmployeeMaster getRecordByEmployeeIdDao(EmployeeMaster employee) {
	       
	try{
	        Query query = entityManager.createQuery("From EmployeeMaster where employeeId=:id");

	        query.setParameter("id",employee.getEmployeeId());
	        
	        EmployeeMaster employee2= (EmployeeMaster) query.getSingleResult();
	        
	        return employee2;
	    }
	        catch (Exception e)
	    {
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
		Query query = entityManager.createQuery("SELECT emp.employeeId FROM EmployeeMaster as emp inner join emp.userMaster as us where us.userId = " + userId);
		Long empId = (Long) query.getSingleResult();
		return empId;
	}

	@Override
	public EmployeeMaster getEmployeeByUserId(Long userId) {
		return entityManager.createQuery("SELECT emp FROM EmployeeMaster as emp inner join emp.userMaster as um where um.userId = " + userId, EmployeeMaster.class).getSingleResult();
	}

	@Override
	public EmployeeMaster getEmployeeByEmpId(Long empId) {
		return entityManager.createQuery(
				"SELECT emp FROM EmployeeMaster as emp where emp.employeeId = " + empId,
				EmployeeMaster.class)
				.getSingleResult();
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
	public Long getEmployeeFromCategory(Long studentId) {
    return entityManager
        .createQuery(
            "SELECT em.employeeId FROM EmployeeMaster as em \n"
                + "    inner join EmpCategy as ec on em.employeeId = ec.employeeMaster.employeeId\n"
                + "    where ec.categoryMaster.categoryId = (SELECT lm.categoryMaster.categoryId FROM LeadMaster lm where lm.studentId = " + studentId + ")",
            Long.class)
        .getSingleResult();
	}

	@Override
	public List getVerifiactionAndAdmissionConusellor() {
    return entityManager
        .createQuery(
            "SELECT new EmployeeNameWithId (em.employeeId, em.employeeName ) FROM EmployeeMaster as em \n"
                + "                               inner join UserMaster um on em.userMaster.userId = um.userId \n"
                + "                               inner join UserRole ur on um.userId = ur.users.userId \n"
                + "                               inner join RoleMaster rm on ur.roles.roleId = rm.roleId \n"
                + "where rm.roleName = 'Admissions counsellor' or rm.roleName = 'Verification counsellor'")
        .getResultList();
	}
}
