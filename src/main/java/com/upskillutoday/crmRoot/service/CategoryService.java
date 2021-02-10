package com.upskillutoday.crmRoot.service;

import java.util.Date;
import java.util.List;

import com.upskillutoday.crmRoot.dto.CategoryDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.repository.CategoryRepository;
import com.upskillutoday.crmRoot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface CategoryService {
	boolean insertCategoryService(CategoryDto categoryDto);
	boolean getRecordByCategoryIdService(CategoryDto categoryDto);
	List getAllRecordCategoryService();
	CategoryMaster getCatgoryById(Long id);
}

@Service
@Transactional
class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean insertCategoryService(CategoryDto categoryDto) {
		CategoryMaster category = new CategoryMaster();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setUpdatedOn(new Date());
		category.setDeletedFlag(true);
		boolean  flag= categoryRepository.insertCategoryDao(category);
		return flag;
	}

	@Override
	public List getAllRecordCategoryService() {
		List  list=categoryRepository.getCategoryListDao();
		return list;
	}

	@Override
	public boolean getRecordByCategoryIdService(CategoryDto categoryDto) {
		CategoryMaster category= new CategoryMaster();
		category.setCategoryId(categoryDto.getCategoryId());
		boolean flag = categoryRepository.getRecordByCategoryIdDao(category);
		categoryDto.setCategoryName(category.getCategoryName());
		System.out.println("neha"+category.getCategoryName());
		return flag;
	}

	// Added By Laukik
	@Override
	public CategoryMaster getCatgoryById(Long id) {
		List list = categoryRepository.getCategoryListDao();
		for(CategoryMaster categoryMaster : ((List<CategoryMaster>) list)) {
			if(id.equals(categoryMaster.getCategoryId()))
				return categoryMaster;
		}
		return null;
	}
}
