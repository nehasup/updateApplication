package com.upskillutoday.crmRoot.service;

import com.upskillutoday.crmRoot.dto.InstituteDto;

import java.util.List;

public interface InstituteService {
	boolean insertInstituteservice(InstituteDto instituteDto);
	List getInstituteByCategoryFromStudentId(Long catId);
}
