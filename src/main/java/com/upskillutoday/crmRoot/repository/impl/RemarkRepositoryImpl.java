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
    return entityManager
        .createQuery(
            "SELECT new CountRemarkDto(rmkm.remarkId, rmkm.remarkName, COUNT(l.studentId) ) FROM RemarkMaster as rmkm\n"
                + "    inner join LeadMaster as l on l.remarkMaster.remarkId = rmkm.remarkId\n"  +
					" WHERE l.deletedFlag = true"
                + "    group by rmkm.remarkId")
        .getResultList();
	}

	@Override
	public CountRemarkDto getAllCount() {
		return (CountRemarkDto) entityManager
				.createQuery(
						"SELECT new CountRemarkDto(COUNT(l.studentId)) FROM RemarkMaster as rmkm\n"
								+ "    inner join LeadMaster as l on l.remarkMaster.remarkId = rmkm.remarkId \n" +
								" WHERE l.deletedFlag = true")
				.getSingleResult();
	}

	@Override
	public List getRemarkWithCountForEmployee(Long empId) {
    return entityManager
        .createQuery(
            "select new CountRemarkDto( rs.remarkId, rs.remarkName, COUNT(lm.studentId) ) from EmpLead as el\n"
                + "    inner join EmployeeMaster as e on e.employeeId = el.employeeMaster.employeeId \n"
                + "    inner join LeadMaster as lm on el.leadMaster.studentId = lm.studentId\n"
                + "    inner join RemarkMaster as rs on lm.remarkMaster.remarkId = rs.remarkId\n"
                + "    where e.employeeId = " + empId + " \n"
                + "    group by rs.remarkId")
        .getResultList();
	}

	@Override
	public CountRemarkDto getAllCountForEmp(Long empId) {
    return (CountRemarkDto)
        entityManager
            .createQuery(
                "select COUNT(lm.studentId) from EmpLead as el\n"
                    + "    inner join EmployeeMaster as e on el.employeeMaster.employeeId = e.employeeId\n"
                    + "    inner join LeadMaster as lm on el.leadMaster.studentId = lm.studentId\n"
                    + "    where e.employeeId = "
                    + empId)
            .getSingleResult();
	}

	@Override
	public List getReamrkWithCountForEmpDatewise(Long empId, String date) {
    return entityManager
        .createQuery(
            "select new CountRemarkDto ( rs.remarkId, rs.remarkName, COUNT( DISTINCT lm.studentId) )from History as h\n"
                + "    inner join EmployeeMaster as e on h.employeeMaster.employeeId = e.employeeId \n"
                + "    inner join LeadMaster as lm on h.leadMaster.studentId = lm.studentId \n"
                + "    inner join RemarkMaster as rs on h.remarkMaster.remarkId = rs.remarkId \n"
                + "    where e.employeeId = " + empId + " and h.updatedOn = DATE('" + date + "')\n"
                + "    group by rs.remarkId \n")
        .getResultList();
	}

  //	ALTER TABLE `institute_master` CHANGE COLUMN `institute_usp` `institute_usp` LONGTEXT NULL DEFAULT NULL , CHANGE COLUMN `course_name` `course_name` LONGTEXT NULL DEFAULT NULL ;

  @Override
  public CountRemarkDto getAllCountForEmpDatewise(Long empId, String date) {
    return entityManager
        .createQuery(
            "select new CountRemarkDto ( COUNT(lm.studentId) ) from History as h\n"
                + "    inner join EmployeeMaster as e on h.employeeMaster.employeeId = e.employeeId \n"
                + "    inner join LeadMaster as lm on h.leadMaster.studentId = lm.studentId \n"
                + "    inner join RemarkMaster as rs on h.remarkMaster.remarkId = rs.remarkId \n"
                + "    where e.employeeId = " + empId + " and h.updatedOn = DATE('" + date + "')",
				CountRemarkDto.class)
        .getSingleResult();
	}

	@Override
	public RemarkMaster getRemarkById(Long id) {
		return entityManager.createQuery("SELECT rm FROM RemarkMaster as rm WHERE rm.remarkId = " + id, RemarkMaster.class).getSingleResult();
	}
}
