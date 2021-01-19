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
	            Query query = entityManager.createQuery("Select NEW com.upskillutoday.crmRoot.response.EmployeeResponseDto(emp.employeeId as employeeId,emp.employeeName as employeeName,emp.contactNo as contactNo,emp.guardianNo as guardianNo,emp.emailId as emailId,emp.address as address,emp.birthDate as birthDate,emp.gender,c.categoryName as categoryName,c.categoryId as categoryId)"
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
		return (EmployeeMaster) entityManager.createQuery("SELECT emp FROM EmployeeMaster as emp inner join emp.userMaster as um where um.userId = " + userId);
	}


}
