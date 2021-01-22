package com.upskillutoday.crmRoot.repository;



import com.upskillutoday.crmRoot.model.InstituteMaster;

import java.util.List;


public interface InstituteRepository {
	
	boolean insertInsituteDao(InstituteMaster institute);
	List getInstituteByCategoryFromStudentId(Long catId);
}
