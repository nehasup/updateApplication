package com.upskillutoday.crmRoot.service;

import com.upskillutoday.crmRoot.dto.InstituteDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.InstituteMaster;
import com.upskillutoday.crmRoot.repository.InstituteRepository;
import com.upskillutoday.crmRoot.repository.JPARepository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface InstituteService {
	boolean insertInstituteservice(InstituteDto instituteDto);
	List getInstituteByCategoryFromStudentId(Long catId);
	List getInstitutes();
	boolean updateInstitute(InstituteMaster instituteMaster);
}

@Service
@Transactional
class InstituteServiceImpl implements InstituteService {

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Override
    public boolean insertInstituteservice(InstituteDto instituteDto) {
        InstituteMaster institute = new InstituteMaster();
        CategoryMaster categoryMaster = categoryJpaRepository.findByCategoryId(Long.parseLong(instituteDto.getCategoryId()));
        institute.setInstituteId(instituteRepository.getHighestId() + 1L);
        institute.setInstituteName(instituteDto.getInstituteName());
        institute.setContactNo(instituteDto.getContactNo());
        institute.setEmailId(instituteDto.getEmailId());
        institute.setDesignMaker(instituteDto.getDesignMaker());
        institute.setAddress(instituteDto.getAddress());
        institute.setAdditionalCommited(instituteDto.getAdditionalCommited());
        institute.setCategoryMaster(categoryMaster);
        institute.setCity(instituteDto.getCity());
        institute.setUsps(instituteDto.getUsps());
        institute.setLocality(instituteDto.getLocality());
        institute.setCommitedLead(instituteDto.getCommitedLead());
        institute.setConversion(instituteDto.getConversion());
        institute.setAddmissionComitted(instituteDto.getAddmissionComitted());
        institute.setCourseName(instituteDto.getCourseName());
        institute.setUpdatedOn(new Date());
        institute.setDeletedFlag(true);
        boolean flag = instituteRepository.insertInsituteDao(institute);
        System.out.println("Out insert service ");
        return flag;
    }

    @Override
    public boolean updateInstitute(InstituteMaster instituteMaster) {
        try {
            instituteRepository.updateInstitute(instituteMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getInstituteByCategoryFromStudentId(Long catId) {
        return instituteRepository.getInstituteByCategoryFromStudentId(catId);
    }

    @Override
    public List getInstitutes() {
        List list = instituteRepository.getInstitute();
        ArrayList<InstituteMaster> newList = new ArrayList<>();
        for(InstituteMaster instituteMaster : (List<InstituteMaster>) list) {
            String contact;
            if(!(instituteMaster.getContactNo().contains(" ") || instituteMaster.getContactNo().contains("+") || instituteMaster.getContactNo().contains("(") || instituteMaster.getContactNo().contains(")"))) {
                Long contactl = new BigDecimal(instituteMaster.getContactNo()).longValue();
                contactl = contactl % 10000000000L;
                contact = String.valueOf(contactl);
            } else {
                contact = instituteMaster.getContactNo();
            }
            instituteMaster.setContactNo(contact);
            newList.add(instituteMaster);
        }
        return newList;
    }
}
