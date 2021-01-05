package com.upskillutoday.crmRoot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.upskillutoday.crmRoot.dto.SubCategoryDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.SubCategoryMaster;
import com.upskillutoday.crmRoot.repository.CategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.SubCategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.SubCategoryRepository;
import com.upskillutoday.crmRoot.service.SubCategoryService;


@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	
	@Autowired
	SubCategoryRepository subCategoryRepository;
	
	@Autowired
	CategoryJpaRepository categoryJpaRepository;
	
	@Autowired
	SubCategoryJpaRepository subcategoryJPARepository;
	
	
	@Override
	public boolean insertSubCategoryService(SubCategoryDto subCategoryDto) {
		
		SubCategoryMaster subCategory = new SubCategoryMaster();
		subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
		
	
		subCategory.setUpdatedOn(new Date());
		subCategory.setDeletedFlag(true);
        
		
		CategoryMaster category = subCategoryRepository.getCategorybyidDao(subCategoryDto.getCategoryId());	
		subCategory.setCategory(category);
		
		boolean flag=subCategoryRepository.insertSubCategoryDao(subCategory);

        return flag;
	}


	@Override
	public List getAllSubCategoryService() {
        List  list=subCategoryRepository.getAllSubCategDao();
        return list;
    }
	
	@Override
    public SubCategoryDto getSubCatebyIdService(SubCategoryDto subCategoryDto) {

       
		SubCategoryMaster subCategory = new SubCategoryMaster();
		subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
	 
		SubCategoryMaster subCategory11=subCategoryRepository.getBySubCatIdDao(subCategory);
		if(subCategory11!=null) {

	        SubCategoryDto subCategoryDto2 = new SubCategoryDto();
	        
	        subCategoryDto2.setSubCategoryId(subCategory11.getSubCategoryId());
	        subCategoryDto2.setSubCategoryName(subCategory11.getSubCategoryName()); 
	        subCategoryDto2.setCategoryId(subCategory11.getCategory().getCategoryId());	        
	        subCategoryDto2.setCategory(subCategory11.getCategory());
	        
	        
	        return subCategoryDto2;
		}
		else {
			return null;
		}
        
    }


	@Override
	public boolean updateService(SubCategoryDto subCategoryDto) {
		
		
		//cat obj by id
			//System.out.println("service id"+subCategoryDto.getCategoryId());
		CategoryMaster category = categoryJpaRepository.findById(subCategoryDto.getCategoryId()).orElse(null);
		
			// subCategoryMaster.setCategoryMaster(subCategoryMasterReqDto.getCategoryMaster());
	
		SubCategoryMaster subCategory = new SubCategoryMaster();
		subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
		subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
		
		subCategory.setUpdatedOn(new Date());
		subCategory.setDeletedFlag(true);
		subCategory.setCategory(subCategoryDto.getCategory());
		
		category.setCategoryId(subCategoryDto.getCategoryId());
		//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+subCategoryDto.getCategoryId());
		
		subCategory.setCategory(category);
		
		
		subCategory.setCategoryId(subCategoryDto.getCategoryId());
		
		
		 try {
			 subCategoryRepository.updateSubCategoryRepository(subCategory);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	
		

	}
    
	

}
