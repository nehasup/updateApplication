package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.CategoryCity;

public interface CategoryRepository {

	boolean insertCategoryDao(CategoryMaster category);

	boolean insertCategoryCityDao(CategoryCity categoryCity);
	
	

	//Category getCategorybyNameDao(String categoryName);

	List getCategoryListDao();

	boolean getRecordByCategoryIdDao(CategoryMaster category);



}
