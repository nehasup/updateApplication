package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.CategoryDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;

public interface CategoryService {

	boolean insertCategoryService(CategoryDto categoryDto);

	boolean getRecordByCategoryIdService(CategoryDto categoryDto);

	List getAllRecordCategoryService();

	// Added By Laukik
	CategoryMaster getCatgoryById(Long id);
}
