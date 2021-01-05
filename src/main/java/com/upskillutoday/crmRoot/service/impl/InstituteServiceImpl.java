package com.upskillutoday.crmRoot.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.InstituteDto;
import com.upskillutoday.crmRoot.model.InstituteMaster;
import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.repository.InstituteRepository;
import com.upskillutoday.crmRoot.repository.UserLoginDao;
import com.upskillutoday.crmRoot.service.InstituteService;

@Service
public class InstituteServiceImpl implements InstituteService {
	
	@Autowired
	InstituteRepository instituteRepository;
	
	@Autowired
	UserLoginDao userLoginRepository;

	@Override
	public boolean insertInstituteservice(InstituteDto instituteDto) {
		
		InstituteMaster institute = new InstituteMaster();
		
		//institute.setInstituteId(instituteDto.getInstituteId());
		institute.setInstituteName(instituteDto.getInstituteName());
		institute.setLegalName(instituteDto.getLegalName());
		institute.setContactNo(instituteDto.getContactNo());
		institute.setEmailId(instituteDto.getEmailId());
		institute.setAddress(instituteDto.getAddress());
		institute.setGstNo(instituteDto.getGstNo());
		institute.setUpSkillExecutive(instituteDto.getUpSkillExecutive());
		institute.setCourseName(instituteDto.getCourseName());
		institute.setDesignMaker(instituteDto.getDesignMaker());
		institute.setHeadCounselor(instituteDto.getHeadCounselor());
		institute.setCounselor(instituteDto.getCounselor());
		institute.setEligibility(instituteDto.getEligibility());
		institute.setTargetLocality(instituteDto.getTargetLocality());
		institute.setAgeLimit(instituteDto.getAgeLimit());
		institute.setPerDayLead(instituteDto.getPerDayLead());
		institute.setUpdatedBy(instituteDto.getUpdatedBy());
        Date date = new Date();
		institute.setUpdatedOn(date);
		institute.setDeletedFlag(true);
				
		
		 boolean flag=instituteRepository.insertInsituteDao(institute);
		 //institute.setInstituteId(instituteDto.getInstituteId());
		
			
			 UserMaster user =new UserMaster();
			 user.setUserName(instituteDto.getUserName());
			  user.setPass(instituteDto.getPass());
			
			//user.setInstitute(institute);
			userLoginRepository.saveUserNameDao(user);

		
	        System.out.println("Out insert service ");
	        return flag;
	}

	
}
