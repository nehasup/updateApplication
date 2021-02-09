package com.upskillutoday.crmRoot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.upskillutoday.crmRoot.dto.CategoryDto;
import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.repository.JPARepository.CategoryJpaRepository;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.CategoryService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryJpaRepository categoryJpaRepository;
	
	//add category
	@PostMapping("/saveCategory")
	@ResponseBody 
	public ResponseVO insertCategory(
			@RequestBody CategoryDto categoryDto
	) {
	  	ResponseVO response = new ResponseVO();
		boolean flag=categoryService.insertCategoryService(categoryDto);
		if(flag) {
			response.setMessage("Insert Category Sucessfully");
			response.setStatusCode(String.valueOf(HttpStatus.OK));
			response.setResult(flag);
		} else {
			response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
			response.setMessage("Insert Failed!!");
			response.setResult(flag);

		}
		return response;
    }

	//get category active list
	 @GetMapping("/getAllCategoryList")	  
	 @ResponseBody
	 public ResponseVO<List> getCategoryAllList() {
		ResponseVO<List> response=new ResponseVO<List>();
		System.out.println("List Successfully!!");
		List list=categoryService.getAllRecordCategoryService();
		response.setResult(list);

		if(list.size()!=0) {
			response.setStatusCode(String.valueOf(HttpStatus.OK));
			response.setMessage("Data is Present Successfully!!");
		} else {
			response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
			response.setMessage("Data is Not Present!!");
		}

		return response;
	}
	 
	 //getRecordByidForEdit.........By neha
	 @GetMapping("/getAllCategorybyid/{id}")	  
	 @ResponseBody public ResponseVO<CategoryDto> getRecordByCategoryIdController(
	 		@PathVariable(value = "id") Long categoryId
	 ) {
	     ResponseVO<CategoryDto> response = new ResponseVO<CategoryDto>();
	     CategoryDto categoryDto = new CategoryDto();
	     categoryDto.setCategoryId(categoryId);

	     boolean flag=categoryService.getRecordByCategoryIdService(categoryDto);
	     if(flag) {
	         response.setMessage("Search By Data Sucessfully");
	         response.setStatusCode(String.valueOf(HttpStatus.OK));
	         response.setResult(categoryDto);
	     } else {
	         response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	         response.setMessage("Search Failed!!");
	         response.setResult(categoryDto);
	     }
	     return response;
	 }
	
	 //update category by id
	 @PutMapping("/updateCategoryByid/{id}")
	 public ResponseEntity<CategoryMaster> updateCategorybyid(
	 		@PathVariable(value = "id") Long categoryId,
			@RequestBody CategoryMaster categoryDetails
	 ) throws ResourceNotFoundException {
	     CategoryMaster category = categoryJpaRepository
			 .findById(categoryId)
	     	.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
	     category.setCategoryName(categoryDetails.getCategoryName());
	     category.setUpdatedOn(new Date());
	     category.setDeletedFlag(true);
	     final CategoryMaster updatedCategory = categoryJpaRepository.save(category);
	     return ResponseEntity.ok(updatedCategory);
	 }

	//delete category by id
	 @DeleteMapping("/deleteCategory/{id}")
	 public Map<String, Boolean> deleteCategory(
	 		@PathVariable(value = "id") Long categoryId
	 ) throws ResourceNotFoundException {
		 CategoryMaster category = categoryJpaRepository.findById(categoryId)
	    .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

		 category.setDeletedFlag(false);
		 categoryJpaRepository.save(category);
	     Map<String, Boolean> response = new HashMap<>();
	     response.put("deletedFlag", Boolean.TRUE);
	     return response;
	 }
	  
	//get All Category list
	 @GetMapping("/getCategories")
	    public List<CategoryMaster> getAllCategories() {
	        return categoryJpaRepository.findAll();
	    }
}
