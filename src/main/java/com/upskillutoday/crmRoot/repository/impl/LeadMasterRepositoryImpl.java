package com.upskillutoday.crmRoot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.repository.LeadMasterRepository;


@Repository
public class LeadMasterRepositoryImpl implements LeadMasterRepository {
	  @Autowired
	     private EntityManager entityManager;

	@Override
	public LeadMaster findByEmail(String email) {
		 LeadMaster lead = null;
	        Query query = entityManager.createQuery("SELECT l FROM lead_master l WHERE l.email_id=:email");
	        query.setParameter("email_id", email);
	        try {
	       	 lead = (LeadMaster) query.getSingleResult();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return lead;
	}
	
		
	
	@Override
	public boolean insertLeadRepository(LeadMaster leadMaster) {
		 try {
	            entityManager.persist(leadMaster);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
		 finally {
	            entityManager.close();
	        }
	}
	
	
	@Override
    public List getAllLeadListDao() {
        List<String> list = null;
        try {
            Query query = entityManager.createQuery("Select NEW com.upskillutoday.crmRoot.response.LeadResponseDto(lead.studentId as studentId,\r\n"
            		+ "lead.studentName as studentName,\r\n"
            		+ "lead.contactNo as contactNo,\r\n"
            		+ "lead.emailId as emailId,\r\n"
            		+ "lead.courseName as courseName,\r\n"
            		+ "lead.city as city,\r\n"
            		+ "lead.area as area,\r\n"
            		+ "lead.modeOfCourse as modeOfCourse,\r\n"
            		+ "lead.address as address,\r\n"
            		+ "lead.budget as budget,\r\n"
            		+ "lead.modificationStage as modificationStage,\r\n"
            		+ "lead.remark as remark,\r\n"
            		+ "lead.comments as comments,\r\n"
            		+ "lead.instituteName as instituteName,\r\n"
            		+ "c.categoryId as categoryId,\r\n"
            		+"c.categoryName as categoryName,\r\n"
            		+ "rmk.remarkId as remarkId,\r\n"
            		+ "rmk.remarkName as remarkName)"
            		+ " from LeadMaster as lead inner join lead.categoryMaster as c inner join lead.remarkMaster as rmk where lead.deletedFlag=1");
             list = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

	@Override
    public LeadMaster getRecordByStudentIdDao(LeadMaster leadMaster) {
       
try{
        Query query = entityManager.createQuery("From LeadMaster where studentId=:id");

        query.setParameter("id",leadMaster.getStudentId());
        
        LeadMaster leadMaster2= (LeadMaster) query.getSingleResult();
        
        return leadMaster2;
    }
        catch (Exception e)
    {
        e.printStackTrace();
        return null;
    }
}
	@Override
	public boolean updateLeadRepository(LeadMaster leadMaster) {
	 try {
           entityManager.merge(leadMaster);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
}



	@Override
	public List<LeadMaster> getAllLeadByassignFlag() {
		 List<LeadMaster> leadMasterDtos =null;
		try {
	            Query query = entityManager.createQuery("Select lead from LeadMaster as lead where lead.deletedFlag=1");
	            leadMasterDtos = query.getResultList();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return leadMasterDtos;
	    }
	}
	
	


 

