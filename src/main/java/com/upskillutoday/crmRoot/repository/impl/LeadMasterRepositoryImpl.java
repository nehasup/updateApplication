package com.upskillutoday.crmRoot.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.repository.EmpLeadJpaRepository;
import com.upskillutoday.crmRoot.response.LeadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.repository.LeadMasterRepository;


@Repository
public class LeadMasterRepositoryImpl implements LeadMasterRepository {
	 @Autowired
	 private EntityManager entityManager;

  	@Autowired
	EmpLeadJpaRepository empLeadJpaRepository;

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
	public void updateLeadRepository(LeadMaster leadMaster) {
	 try {
           entityManager.merge(leadMaster);
     } catch (Exception e) {
           e.printStackTrace();
     }
}

	@Override
	public List<LeadMaster> getAllLeadByassignFlag() {
	 	List<LeadMaster> leadMasterDtos = null;
		try {
				Query query = entityManager.createQuery("Select lead from LeadMaster as lead where lead.deletedFlag = true");
				leadMasterDtos = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return leadMasterDtos;
	}

	@Override
	public List getAllLeadForMe() {
		return entityManager.createQuery("SELECT new LeadResponseDto (" +
				"lm.studentId, " +
				"lm.studentName, " +
				"lm.courseName, " +
				"lm.contactNo, " +
				"lm.area," +
				"lm.city, " +
				"lm.emailId, " +
				"lm.modeOfCourse, " +
				"lm.modificationStage, " +
				"lm.address, " +
				"lm.budget, " +
				"rmk.remarkId, " +
				"rmk.remarkName, " +
				"lm.comments, " +
				"lm.instituteName," +
				"cm.categoryId," +
				"cm.categoryName," +
				"lm.updatedOn, " +
				"emp.employeeName, " +
				"rm.roleName) " +
				"FROM LeadMaster as lm inner join lm.categoryMaster as cm inner join lm.remarkMaster as rmk \n"+
				"left join EmpLead as el on lm.studentId = el.leadMaster.studentId \n" +
				//"left join CategoryMaster as cm on cm.categoryId = el.leadMaster.categoryMaster.categoryId" +
				"left join EmployeeMaster as emp on emp.employeeId = el.employeeMaster.employeeId \n" +
				"left join UserRole as ur on ur.users.userId = emp.userMaster.userId \n" +
				"left join RoleMaster as rm on rm.roleId = ur.roles.roleId \n " +
				"left join CategoryMaster as cm on cm.categoryId = el.leadMaster.categoryMaster.categoryId").getResultList();
	}


	@Override
	public List getLeadsByRemark(Long remarkId) {
		return entityManager.createQuery("select lead from LeadMaster as lead " +
				"where lead.remarkMaster.remarkId = " + remarkId,
				LeadMaster.class).getResultList();
	}

	@Override
	public List<LeadMasterDto> getAllLeadListByquery(Long userId) {
		  List<LeadMasterDto> list = null;
		Query query = entityManager.createQuery("Select NEW com.upskillutoday.crmRoot.response.LeadResponseDto("
				+ "lead.studentId as studentId,\r\n"
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
        		+ "cm.categoryId as categoryId,\r\n"
        		+ "cm.categoryName as categoryName,\r\n"
        		+ "rmk.remarkId as remarkId,\r\n"
        		+ "rmk.remarkName as remarkName,\r\n"
        		+ "lead.updatedBy as updatedBy,\r\n"
        		+ "lead.updatedOn as updatedOn)"
				+ " FROM LeadMaster as lead inner join lead.categoryMaster as cm inner join lead.remarkMaster as rmk"
				+ " where cm.categoryId IN (\r\n"
				+ "	SELECT c.categoryId FROM EmpCategy ec inner join ec.employeeMaster emp inner join ec.categoryMaster c where emp.employeeId = :id)");
			    query.setParameter("id",userId);
			     
			       list = query.getResultList();
				return list;
	}

	@Override
	public List getAllUnassignedNewLeads() {
		return entityManager.createQuery(
				"SELECT lm FROM LeadMaster as lm where lm.remarkMaster.remarkId = 3"
		).getResultList();
	}
}