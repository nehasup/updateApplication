package com.upskillutoday.crmRoot.repository;
import java.util.List;

import com.upskillutoday.crmRoot.dto.CountRemarkDto;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface RemarkRepository {
	boolean insertRemarkDao(RemarkMaster remark);
	List getRemarkListDao();
	boolean getRecordByRemarkIdDao(RemarkMaster remark);
	List getRemarkWithCount();
	List getRemarkWithCountForEmployee(Long empId);
	RemarkMaster getRemarkById(Long id);
	CountRemarkDto getAllCount();
	CountRemarkDto getAllCountForEmp(Long empId);
	List getReamrkWithCountForEmpDatewise(Long empId, String date);
	CountRemarkDto getAllCountForEmpDatewise(Long empId, String date);
}

@Repository
@Transactional
class RemarkRepositoryImpl implements RemarkRepository {

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
		return entityManager
				.createQuery(
						"select new CountRemarkDto (COUNT(lm.studentId)) from EmpLead as el\n"
								+ "    inner join EmployeeMaster as e on el.employeeMaster.employeeId = e.employeeId\n"
								+ "    inner join LeadMaster as lm on el.leadMaster.studentId = lm.studentId\n"
								+ "    where e.employeeId = "
								+ empId, CountRemarkDto.class)
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
