package com.upskillutoday.crmRoot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.CategoryDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.CategoryCity;
import com.upskillutoday.crmRoot.model.CityMaster;
import com.upskillutoday.crmRoot.repository.CategoryRepository;
import com.upskillutoday.crmRoot.repository.CityRepository;
import com.upskillutoday.crmRoot.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CityRepository cityRepository;
		
	
	
	@Override
	public boolean insertCategoryService(CategoryDto categoryDto) {

		CategoryMaster category = new CategoryMaster();
		category.setCategoryName(categoryDto.getCategoryName());	    
	    category.setUpdatedOn(new Date());
	    category.setDeletedFlag(true);
	    boolean  flag= categoryRepository.insertCategoryDao(category);
        
        return flag;
	}
	
	/*@Override
	public boolean insertCategoryService(CategoryDto categoryDto) {
		
		//1. duplicate category
		
		//Todo:CategoryName =  category
		
		Category objcategoryName = categoryRepository.getCategorybyNameDao(categoryDto.getCategoryName());
		
		if(objcategoryName!=null) {
			City city = cityRepository.getrecordCityIdDao(categoryDto.getCityId());
			
//			if(objcategoryName.get) {}
			
		}
		//insert category
		Category category = new Category();
		category.setCategoryName(categoryDto.getCategoryName());	    
	    category.setUpdatedOn(new Date());
	    category.setDeletedFlag(true);
	    categoryRepository.insertCategoryDao(category);

        //insert category_city
        City city = cityRepository.getrecordCityIdDao(categoryDto.getCityId());        
        CategoryCity categoryCity = new CategoryCity();
        categoryCity.setCategory(category);
        categoryCity.setCity(city);        
        categoryCity.setUpdatedOn(new Date());
        categoryCity.setDeletedFlag(true);
        
        boolean  flag=categoryRepository.insertCategoryCityDao(categoryCity);      
   
        
        return flag;
	} */
	
	
	@Override
	public List getAllRecordCategoryService() {
        List  list=categoryRepository.getCategoryListDao();
        return list;
    }
	
	@Override
    public boolean getRecordByCategoryIdService(CategoryDto categoryDto) {

       
       
        
        CategoryMaster category= new CategoryMaster();
        category.setCategoryId(categoryDto.getCategoryId());
       

        boolean flag=categoryRepository.getRecordByCategoryIdDao(category);
        
        categoryDto.setCategoryName(category.getCategoryName());
        

        System.out.println("neha"+category.getCategoryName());
    
    

        return flag;
    }
	
}
