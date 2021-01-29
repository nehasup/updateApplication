package com.upskillutoday.crmRoot.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.repository.EmpLeadJpaRepository;
import com.upskillutoday.crmRoot.response.LeadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.repository.LeadMasterRepository;

@Repository
@Transactional
public class LeadMasterRepositoryImpl implements LeadMasterRepository {
	 @Autowired
	 @PersistenceContext
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
				"FROM LeadMaster as lm " +
				"inner join lm.categoryMaster as cm " +
				"inner join lm.remarkMaster as rmk \n"+
				"left join EmpLead as el on lm.studentId = el.leadMaster.studentId \n" +
				"left join EmployeeMaster as emp on emp.employeeId = el.employeeMaster.employeeId \n" +
				"left join UserRole as ur on ur.users.userId = emp.userMaster.userId \n" +
				"left join RoleMaster as rm on rm.roleId = ur.roles.roleId ")
				.getResultList();
	}


	@Override
	public List getLeadsByRemark(Long remarkId) {
		return entityManager.createQuery("Select NEW LeadResponseDto("
				+ "lead.studentId ,"
				+ "lead.studentName ,"
				+ "lead.contactNo,"
				+ "lead.emailId ,"
				+ "lead.courseName ,"
				+ "lead.city ,"
				+ "lead.area ,"
				+ "lead.modeOfCourse ,"
				+ "lead.address ,"
				+ "lead.budget,"
				+ "lead.modificationStage,"
				+ "lead.remark ,"
				+ "lead.comments ,"
				+ "lead.instituteName ,"
				+ "cm.categoryId ,"
				+ "cm.categoryName ,"
				+ "rmk.remarkId ,"
				+ "rmk.remarkName ,"
				+ "lead.updatedBy , "
				+ "lead.updatedOn )"
				+ " FROM LeadMaster as lead " +
				"	inner join lead.categoryMaster as cm " +
				"	inner join lead.remarkMaster as rmk " +
				"	where lead.remarkMaster.remarkId = " + remarkId,
		LeadResponseDto.class
		).getResultList();
	}

	@Override
	public List getAllLeadListByquery(Long userId) {
		  List list = null;
		Query query = entityManager.createQuery("Select NEW LeadResponseDto("
				+ "lead.studentId ,"
        		+ "lead.studentName ,"
        		+ "lead.contactNo,"
        		+ "lead.emailId ,"
        		+ "lead.courseName ,"
        		+ "lead.city ,"
        		+ "lead.area ,"
        		+ "lead.modeOfCourse ,"
        		+ "lead.address ,"
        		+ "lead.budget,"
        		+ "lead.modificationStage,"
        		+ "lead.remark ,"
        		+ "lead.comments ,"
        		+ "lead.instituteName ,"
        		+ "cm.categoryId ,"
        		+ "cm.categoryName ,"
        		+ "rmk.remarkId ,"
        		+ "rmk.remarkName ,"
        		+ "lead.updatedBy , "
        		+ "lead.updatedOn )"
				+ " FROM LeadMaster as lead " +
				"	inner join lead.categoryMaster as cm " +
				"	inner join lead.remarkMaster as rmk " +
				"  	inner join EmpLead as el on el.leadMaster.studentId = lead.studentId " +
				"	inner join el.employeeMaster as em " +
				"	where em.employeeId = " + userId);
			     
			       list = query.getResultList();

				return list;
	}

	@Override
	public List getAllUnassignedNewLeads() {
		return entityManager.createQuery(
				"SELECT lm FROM LeadMaster as lm where lm.remarkMaster.remarkId = 3"
		, LeadMaster.class).getResultList();
	}

	@Override
	public LeadMaster getLeadByStudentId(Long stduentId) {
		return entityManager.createQuery(
				"SELECT lm FROM  LeadMaster as lm WHERE lm.studentId = " + stduentId, LeadMaster.class).getSingleResult();
	}

	@Override
	public List getAllLeadFromStatusByEmp(Long remarkId, Long userId) {
    return entityManager
        .createQuery(
            "SELECT new LeadResponseDto ( " +
					"lm.studentId, " +
					"lm.studentName, " +
					"lm.contactNo, " +
					"lm.emailId, " +
					"lm.courseName, " +
					"lm.city, " +
					"lm.area, " +
					"lm.modeOfCourse, " +
					"lm.address, " +
					"lm.budget, " +
					"lm.modificationStage, " +
					"lm.remark, " +
					"lm.comments, " +
					"lm.instituteName, " +
					"cat.categoryId, " +
					"cat.categoryName, " +
					"rm.remarkId, " +
					"rm.remarkName, " +
					"lm.updatedBy, " +
					"lm.updatedOn ) FROM LeadMaster as lm\n"
                + "    inner join EmpLead as el on lm.studentId = el.leadMaster.studentId \n"
                + "    inner join EmployeeMaster as e on el.employeeMaster.employeeId = e.employeeId \n"
                + "    inner join UserMaster as um on e.userMaster.userId = um.userId\n"
				+ "		inner join lm.categoryMaster as cat \n"
				+ "		inner join lm.remarkMaster as rm \n"
                + "    where um.userId = " + userId
                + " and lm.remarkMaster.remarkId = " + remarkId)
        .getResultList();
	}

	@Override
	public List getLeadMasterByNameEmailContactDeleteFlag(String name, String contact, String email, boolean flag) {
		return entityManager.createQuery(
				"SELECT lm FROM LeadMaster as lm " +
						" WHERE lm.emailId = '" + email + "'" +
						" and lm.contactNo = " + contact
		).getResultList();
	}
}