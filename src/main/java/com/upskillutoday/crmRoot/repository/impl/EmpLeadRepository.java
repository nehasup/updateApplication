package com.upskillutoday.crmRoot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.LeadMaster;


@Repository
public class EmpLeadRepository {
	  @Autowired
	     private EntityManager entityManager;
	  
	  

//		public EmpLead getListbyStudentId(Long StudentId) {
//			 LeadMaster lead = null;
//		        Query query = entityManager.createQuery("SELECT l FROM emp l WHERE l.email_id=:email");
//		        query.setParameter("email_id", email);
//		        try {
//		       	 lead = (LeadMaster) query.getSingleResult();
//		        } catch (Exception e) {
//		            e.printStackTrace();
//		        }
//		        return lead;
//		}
		
	
//
//    public List<EmpLead> getAllAssignLeadListDao() {
//    	List<EmpLead> list = null;
//        try {
//            Query query = entityManager.createQuery("Select NEW com.upskillutoday.crmRoot.response.EmpLeadResponseDto(lead.studentId as studentId,\r\n"
//            		+ "lead.studentName as studentName,\r\n"
//            		+ "lead.contactNo as contactNo,\r\n"
//            		+ "lead.emailId as emailId,\r\n"
//            		+ "lead.courseName as courseName,\r\n"
//            		+ "lead.city as city,\r\n"
//            		+ "lead.area as area,\r\n"
//            		+ "lead.modeOfCourse as modeOfCourse,\r\n"
//            		+ "lead.address as address,\r\n"
//            		+ "lead.budget as budget,\r\n"
//            		+ "lead.modificationStage as modificationStage,\r\n"
//            		+ "lead.remark as remark,\r\n"
//            		+ "lead.comments as comments,\r\n"
//            		+ "lead.instituteName as instituteName,\r\n"
//            		+"emp.employeeName as employeeName,\r\n"
//            		+ "lead.assignLeadFlag as assignLeadFlag)"
//            		+ " from EmpLead as emplead inner join emplead.employeeMaster as emp inner join emplead.leadMaster as lead where emplead.deletedFlag=1");
//             list = query.getResultList();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

}
