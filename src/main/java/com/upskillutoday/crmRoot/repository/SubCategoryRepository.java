package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.SubCategoryMaster;

public interface SubCategoryRepository {

	boolean insertSubCategoryDao(SubCategoryMaster subCategory);

	List getAllSubCategDao();

	SubCategoryMaster getBySubCatIdDao(SubCategoryMaster subCategory);

	CategoryMaster getCategorybyidDao(Long categoryId);

	boolean updateSubCategoryRepository(SubCategoryMaster subCategory);

	

	//City getrecordCityIdDao(Long cityId);
}
