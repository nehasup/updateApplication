package com.upskillutoday.crmRoot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import com.upskillutoday.crmRoot.model.RoleMaster;
import com.upskillutoday.crmRoot.model.SubCategoryMaster;
import com.upskillutoday.crmRoot.model.UserRole;
import com.upskillutoday.crmRoot.repository.CategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.LeadJpaMasterRepository;
import com.upskillutoday.crmRoot.repository.LeadMasterRepository;
import com.upskillutoday.crmRoot.repository.RemarkJpaRepository;
import com.upskillutoday.crmRoot.repository.SubCategoryJpaRepository;
import com.upskillutoday.crmRoot.service.LeadMasterService;


@Service
@Transactional
public class LeadMasterServiceImpl implements LeadMasterService{
	
	@Autowired
	private LeadMasterRepository leadRepostiory;
	
	@Autowired
	private SubCategoryJpaRepository subCategoryRepository;
	
	@Autowired
	CategoryJpaRepository categoryJpaRepository;
	
	@Autowired
	RemarkJpaRepository remarkJpaRepository;
	
	@Autowired
	private LeadJpaMasterRepository leadJpaMasterRepository;

	@Override
	public boolean insertLeadService(LeadMasterDto leadMasterDto) {
		
		//save category
		CategoryMaster categoryMaster = categoryJpaRepository.findByCategoryId(leadMasterDto.getCategoryId());
		
		//save subcategory
		SubCategoryMaster subCategoryMaster = subCategoryRepository.findBySubCategoryId(leadMasterDto.getSubCategoryId());
		
		
		long id = 3;
			RemarkMaster remarkMaster = remarkJpaRepository.findById(id).orElse(null);
		
		
		LeadMaster leadMaster = new LeadMaster();
		
		leadMaster.setStudentName(leadMasterDto.getStudentName());
		leadMaster.setCourseName(leadMasterDto.getCourseName());
		leadMaster.setContactNo(leadMasterDto.getContactNo());
		leadMaster.setArea(leadMasterDto.getArea());
		leadMaster.setCity(leadMasterDto.getCity());
		leadMaster.setEmailId(leadMasterDto.getEmailId());
		leadMaster.setModeOfCourse(leadMasterDto.getModeOfCourse());
		leadMaster.setAddress(leadMasterDto.getAddress());
		leadMaster.setBudget(leadMasterDto.getBudget());
		leadMaster.setModificationStage(leadMasterDto.getModificationStage());
		leadMaster.setRemark(leadMasterDto.getRemark());
		leadMaster.setComments(leadMasterDto.getComments());
		leadMaster.setInstituteName(leadMasterDto.getInstituteName());
		leadMaster.setUpdatedOn(new Date());
		leadMaster.setDeletedFlag(true);
		leadMaster.setCategoryMaster(categoryMaster);
		leadMaster.setSubCategoryMaster(subCategoryMaster);
		leadMaster.setRemarkMaster(remarkMaster);
	
		
		
		//leadMaster.setUpdatedBy();
		
		  try {
			  leadRepostiory.insertLeadRepository(leadMaster);
	            return true;
	        }

	        catch (Exception e){
	            e.printStackTrace();
	            return false;
	        }
		
		
	}

	@Override
	public List getAllLeadRecordService() {
        List  list=leadRepostiory.getAllLeadListDao();
        return list;
    }
	
	@Override
    public LeadMasterDto getRecordByStudentIdService(LeadMasterDto leadMasterDto) {
		
		LeadMaster leadMaster = new LeadMaster();
		leadMaster.setStudentId(leadMasterDto.getStudentId());


		LeadMaster leadMaster2 = leadRepostiory.getRecordByStudentIdDao(leadMaster);
     
     
			if(leadMaster2!=null) {
        	
        	
        	LeadMasterDto leadMasterDto2 = new LeadMasterDto();
        	leadMasterDto2.setStudentId(leadMaster2.getStudentId());
        	leadMasterDto2.setStudentName(leadMaster2.getStudentName());
        	leadMasterDto2.setCourseName(leadMaster2.getCourseName());
        	leadMasterDto2.setContactNo(leadMaster2.getContactNo());
        	leadMasterDto2.setArea(leadMaster2.getArea());
        	leadMasterDto2.setCity(leadMaster2.getCity());
        	leadMasterDto2.setEmailId(leadMaster2.getEmailId());
        	leadMasterDto2.setModeOfCourse(leadMaster2.getModeOfCourse());
        	leadMasterDto2.setAddress(leadMaster2.getAddress());
        	leadMasterDto2.setBudget(leadMaster2.getBudget());
        	leadMasterDto2.setModificationStage(leadMaster2.getModificationStage());
        	leadMasterDto2.setRemark(leadMaster2.getRemark());
        	leadMasterDto2.setComments(leadMaster2.getComments());
        	leadMasterDto2.setInstituteName(leadMaster2.getInstituteName());        	
        	leadMasterDto2.setCategoryId(leadMaster2.getCategoryMaster().getCategoryId());
        	
        	
       	if(leadMasterDto.getSubCategoryMaster() != null) {
       		System.out.println("not happend");
       		leadMasterDto2.setSubCategoryId(leadMaster2.getSubCategoryMaster().getSubCategoryId());
    		leadMasterDto2.setSubCategoryMaster(leadMaster2.getSubCategoryMaster());
        	}else {
        		System.out.println("else");
        		//leadMasterDto2.setSubCategoryId(leadMaster2.getSubCategoryMaster().getSubCategoryId());
        		leadMasterDto2.setSubCategoryMaster(leadMaster2.getSubCategoryMaster());
        		
        	}
        	
        	
        	leadMasterDto2.setRemarkId(leadMaster2.getRemarkMaster().getRemarkId());
        	
        	leadMasterDto2.setCategoryMaster(leadMaster2.getCategoryMaster());
        	//leadMasterDto2.setSubCategoryMaster(leadMaster2.getSubCategoryMaster());
        	leadMasterDto2.setRemarkMaster(leadMaster2.getRemarkMaster());
        
        	
    
       	return leadMasterDto2;
        	
        }
        else {
        	return null;
        }
   
        
    }
	
	
	@Override
	public boolean updateLeadService(LeadMasterDto leadMasterDto) {
		//cat obj by id
		CategoryMaster category = categoryJpaRepository.findById(leadMasterDto.getCategoryId()).orElse(null);
		//SubCategoryMaster subCategoryMaster = subCategoryRepository.findById(leadMasterDto.getSubCategoryId()).orElse(null);
		
		RemarkMaster remarkMaster = remarkJpaRepository.findById(leadMasterDto.getRemarkId()).orElse(null);
	
	 
		LeadMaster leadMaster = new LeadMaster();
		leadMaster.setStudentId(leadMasterDto.getStudentId());
		leadMaster.setStudentName(leadMasterDto.getStudentName());
		leadMaster.setCourseName(leadMasterDto.getCourseName());
		leadMaster.setContactNo(leadMasterDto.getContactNo());
		leadMaster.setArea(leadMasterDto.getArea());
		leadMaster.setCity(leadMasterDto.getCity());
		leadMaster.setEmailId(leadMasterDto.getEmailId());
		leadMaster.setModeOfCourse(leadMasterDto.getModeOfCourse());
		leadMaster.setAddress(leadMasterDto.getAddress());
		leadMaster.setBudget(leadMasterDto.getBudget());
		leadMaster.setModificationStage(leadMasterDto.getModificationStage());
		leadMaster.setRemark(leadMasterDto.getRemark());
		leadMaster.setComments(leadMasterDto.getComments());
		leadMaster.setInstituteName(leadMasterDto.getInstituteName());
		leadMaster.setUpdatedOn(new Date());
		leadMaster.setDeletedFlag(true);
		leadMaster.setCategoryMaster(category);
		leadMaster.setSubCategoryMaster(leadMasterDto.getSubCategoryMaster());
	
		leadMaster.setRemarkMaster(remarkMaster);

		
	
	 try {
		 leadRepostiory.updateLeadRepository(leadMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List<LeadMasterDto> getAllLeadListCategoryWiseService(EmployeeMaster employeeMaster) {
		// TODO Auto-generated method stub
		
		List<LeadMaster> leadMasterList = leadJpaMasterRepository.findByCategoryMasterAndDeletedFlag(employeeMaster.getCategory(), true);
		List<LeadMasterDto> leadMasterDtoList = new ArrayList<LeadMasterDto>();
		
		if(leadMasterList!=null) {
			for (LeadMaster leadMaster : leadMasterList) {
				LeadMasterDto leadMasterDto = new LeadMasterDto();
			
				leadMasterDto.setStudentId(leadMaster.getStudentId());
				leadMasterDto.setStudentName(leadMaster.getStudentName());
				leadMasterDto.setCourseName(leadMaster.getCourseName());
				leadMasterDto.setContactNo(leadMaster.getContactNo());
				leadMasterDto.setArea(leadMaster.getArea());
				leadMasterDto.setCity(leadMaster.getCity());
				leadMasterDto.setEmailId(leadMaster.getEmailId());
				leadMasterDto.setModeOfCourse(leadMaster.getModeOfCourse());
				leadMasterDto.setAddress(leadMaster.getAddress());
				leadMasterDto.setBudget(leadMaster.getBudget());
				leadMasterDto.setModificationStage(leadMaster.getModificationStage());
				leadMasterDto.setRemark(leadMaster.getRemark());
				leadMasterDto.setComments(leadMaster.getComments());
				leadMasterDto.setInstituteName(leadMaster.getInstituteName());
				leadMasterDto.setUpdatedOn(new Date());
				leadMasterDto.setDeletedFlag(true);
				leadMasterDto.setCategoryName(leadMaster.getCategoryMaster().getCategoryName());
				//leadMasterDto.setSubCategoryName(leadMaster.getSubCategoryMaster().getSubCategoryName());
				leadMasterDto.setRemarkName(leadMaster.getRemarkMaster().getRemarkName());
				
				leadMasterDtoList.add(leadMasterDto);
			}
		}		
		return leadMasterDtoList;
	}
	
	
	@Override
	
	public List<LeadMasterDto> getCategoryWiseandverifyLeadService(EmployeeMaster employeeMaster) {
		// TODO Auto-generated method stub
		
		RemarkMaster remarkMaster = remarkJpaRepository.findByRemarkId((long) 7);
		
		List<LeadMaster> leadMasterList = leadJpaMasterRepository.findByCategoryMasterAndRemarkMasterAndDeletedFlag(employeeMaster.getCategory(),remarkMaster, true);

		List<LeadMasterDto> leadMasterDtoList = new ArrayList<LeadMasterDto>();
		
		if(leadMasterList!=null) {
			for (LeadMaster leadMaster : leadMasterList) {
				LeadMasterDto leadMasterDto = new LeadMasterDto();
			
				leadMasterDto.setStudentId(leadMaster.getStudentId());
				leadMasterDto.setStudentName(leadMaster.getStudentName());
				leadMasterDto.setCourseName(leadMaster.getCourseName());
				leadMasterDto.setContactNo(leadMaster.getContactNo());
				leadMasterDto.setArea(leadMaster.getArea());
				leadMasterDto.setCity(leadMaster.getCity());
				leadMasterDto.setEmailId(leadMaster.getEmailId());
				leadMasterDto.setModeOfCourse(leadMaster.getModeOfCourse());
				leadMasterDto.setAddress(leadMaster.getAddress());
				leadMasterDto.setBudget(leadMaster.getBudget());
				leadMasterDto.setModificationStage(leadMaster.getModificationStage());
				leadMasterDto.setRemark(leadMaster.getRemark());
				leadMasterDto.setComments(leadMaster.getComments());
				leadMasterDto.setInstituteName(leadMaster.getInstituteName());
				leadMasterDto.setUpdatedOn(new Date());
				leadMasterDto.setDeletedFlag(true);
				leadMasterDto.setCategoryName(leadMaster.getCategoryMaster().getCategoryName());
				//leadMasterDto.setSubCategoryName(leadMaster.getSubCategoryMaster().getSubCategoryName());
				leadMasterDto.setRemarkName(leadMaster.getRemarkMaster().getRemarkName());
				
				leadMasterDtoList.add(leadMasterDto);
			}
		}		
		return leadMasterDtoList;
	}

	
	
}
