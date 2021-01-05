package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.CategoryDto;

public interface CategoryService {

	boolean insertCategoryService(CategoryDto categoryDto);

	boolean getRecordByCategoryIdService(CategoryDto categoryDto);

	List getAllRecordCategoryService();

}
