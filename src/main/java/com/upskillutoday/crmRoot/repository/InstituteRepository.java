package com.upskillutoday.crmRoot.repository;



import com.upskillutoday.crmRoot.model.InstituteMaster;

import java.util.List;


public interface InstituteRepository {
	
	boolean insertInsituteDao(InstituteMaster institute);
	List getInstituteByCategoryFromStudentId(Long catId);
	InstituteMaster getInstituteById(Long id);
	List getInstituteReport();
	List getInstituteWithZero();
	List getInstituteReportDatewise(String date);
	List getInstituteReportDatewiseWithZero(String date);
	List getInstitute();
	List getInstituteOfStudent(Long studentId);
	void updateInstitute(InstituteMaster instituteMaster);
	Long getTotalCount(Long instituteId);
	Long getTotalCountByDate(Long instituteId, String date);
}
