package com.upskillutoday.crmRoot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.upskillutoday.crmRoot.dto.CountRemarkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.CityMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import com.upskillutoday.crmRoot.repository.RemarkRepository;

@Repository
@Transactional
public class RemarkRepositoryImpl implements RemarkRepository {

	@Autowired
	@PersistenceContext   
	private EntityManager entityManager;

	@Override
	public boolean insertRemarkDao(RemarkMaster remark) {
		 try {
	            entityManager.persist(remark);
	            return true;
	        } catch (Exception e) {	   
	        	e.printStackTrace();
	        }
	            return false;
	}

	@Override
	public List getRemarkListDao() {
		 List<String> list = null;
	        try {
	            Query query = entityManager.createQuery("Select rm from RemarkMaster as rm  where rm.deletedFlag=1",RemarkMaster.class);
	             list = query.getResultList();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	}

	@Override
	public boolean getRecordByRemarkIdDao(RemarkMaster remark) {
		 System.out.println("remark id"+remark.getRemarkId());
			try{
			        Query query = entityManager.createQuery("From RemarkMaster where remarkId=:id");

			        query.setParameter("id",remark.getRemarkId());

			        RemarkMaster remark1= (RemarkMaster) query.getSingleResult();
			        System.out.println("fgfg"+remark1.getRemarkId());
			        	
			        remark.setRemarkName(remark1.getRemarkName());
			        

			        return true;
			    }
			        catch (Exception e)
			    {
			        e.printStackTrace();
			        return false;
			    }
	}

	@Override
	public List getRemarkWithCount() {
		return entityManager.createQuery("SELECT new CountRemarkDto(remark.remarkId, remark.remarkName , COUNT(leadMaster.studentId)) \n" +
				"FROM RemarkMaster as remark\n" +
				"inner join LeadMaster as leadMaster \n" +
				"on remark.remarkId = leadMaster.remarkMaster.remarkId").getResultList();
	}

	@Override
	public List getRemarkWithCountForEmployee(Long empId) {
		return entityManager.createQuery("SELECT new CountRemarkDto( remark.remarkId, remark.remarkName , COUNT(leadMaster.studentId)) \n" +
				"    FROM RemarkMaster as remark\n" +
				"        inner join LeadMaster as leadMaster\n" +
				"                   on remark.remarkId = leadMaster.remarkMaster.remarkId\n" +
				"            inner join EmpLead as el\n" +
				"                on el.leadMaster.studentId = leadMaster.studentId\n" +
				"                inner join EmployeeMaster as e\n" +
				"                    on e.employeeId = el.employeeMaster.employeeId\n" +
				"                where e.employeeId = " + empId).getResultList();
	}


}
