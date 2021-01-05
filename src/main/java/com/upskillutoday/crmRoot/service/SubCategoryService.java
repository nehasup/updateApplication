package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.SubCategoryDto;
import com.upskillutoday.crmRoot.model.SubCategoryMaster;

public interface SubCategoryService {

	boolean insertSubCategoryService(SubCategoryDto subCategoryDto);

	List getAllSubCategoryService();

	SubCategoryDto getSubCatebyIdService(SubCategoryDto subCategoryDto);

	boolean updateService(SubCategoryDto subCategoryDto);

	

}
