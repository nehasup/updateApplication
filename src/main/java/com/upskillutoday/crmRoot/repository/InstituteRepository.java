package com.upskillutoday.crmRoot.repository;



import com.upskillutoday.crmRoot.model.InstituteMaster;

import java.util.List;


public interface InstituteRepository {
	
	boolean insertInsituteDao(InstituteMaster institute);
	List getInstituteByCategoryFromStudentId(Long catId);
	InstituteMaster getInstituteById(Long id);
	List getInstituteReport();
	List getInstituteReportDatewise(String date);
	List getInstitute();
	List getInstituteOfStudent(Long studentId);
}
