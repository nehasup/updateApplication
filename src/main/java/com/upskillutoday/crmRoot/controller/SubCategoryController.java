package com.upskillutoday.crmRoot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


import com.upskillutoday.crmRoot.dto.SubCategoryDto;
import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.SubCategoryMaster;
import com.upskillutoday.crmRoot.repository.CategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.SubCategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.SubCategoryRepository;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.SubCategoryService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private SubCategoryJpaRepository subCategoryJpaRepository;
	
	@Autowired
	private SubCategoryRepository subcategoryRepository;
	
	

	@PostMapping("/saveSubCategory")
	@ResponseBody 
	public ResponseVO insertSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
		  ResponseVO response = new ResponseVO();
	      
	        boolean flag=subCategoryService.insertSubCategoryService(subCategoryDto);
	        if(flag)
	        {
	            response.setMessage("Insert SubCategory Sucessfully");
	            response.setStatusCode(String.valueOf(HttpStatus.OK));
	            response.setResult(flag);
	        }
	        else {
	            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	            response.setMessage("Insert Failed!!");
	            response.setResult(flag);

	        }
	        return response;

    }
	
 @GetMapping("/getAllSubCateList")	  
 @ResponseBody  public ResponseVO<List> getSubCategoryAllList() {
        ResponseVO<List> response=new ResponseVO<List>();
        System.out.println("List Successfully!!");
        
        List list=subCategoryService.getAllSubCategoryService();
        response.setResult(list);

        if(list.size()!=0){
            response.setStatusCode(String.valueOf(HttpStatus.OK));
            response.setMessage("Data is Present Successfully!!");
        }
        else {
            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            response.setMessage("Data is Not Present!!");
        }

        return response;
    }
 
 //getRecordByidForEdit.........By neha
 @GetMapping("/getAllSubCatbyid/{id}")	  
 @ResponseBody public ResponseVO<SubCategoryDto> getBySubCatIdController(@PathVariable(value = "id") Long subCategoryId) {
     ResponseVO<SubCategoryDto> response = new ResponseVO<SubCategoryDto>();

     SubCategoryDto subCategoryDto = new SubCategoryDto();
     subCategoryDto.setSubCategoryId(subCategoryId);
    
     SubCategoryDto resultSubCatgdto=subCategoryService.getSubCatebyIdService(subCategoryDto);
    

     if(resultSubCatgdto!=null)
     {
         response.setMessage("Search By Data Sucessfully");
         response.setStatusCode(String.valueOf(HttpStatus.OK));
         response.setResult(resultSubCatgdto);
     }
     else {
         response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
         response.setMessage("Search Failed!!");
         response.setResult(resultSubCatgdto);

     }
     return response;
 }
 
 //update sub category by id
 @PutMapping("/updateSubCategoryByid/{id}")
 @ResponseBody public ResponseVO update(@PathVariable(value = "id") Long subCategoryId,@RequestBody SubCategoryDto subCategoryDetails) throws ResourceNotFoundException {
	 
	 ResponseVO<SubCategoryDto> responseVO = new ResponseVO<SubCategoryDto>();
	 
	// System.out.println("Cata : "+subCategoryDetails.getCategory().getCategoryId() +" // "+subCategoryDetails.getCategory().getCategoryName()+" // "+subCategoryDetails.getCategoryId());
	 //subCategoryDetails.setCategoryId(subCategoryDetails.getCategoryId());
	 
	 boolean flag = subCategoryService.updateService(subCategoryDetails);
	 
	
	      if(flag){
	          responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
	          responseVO.setMessage("Update Successfully!!");
	      }
	      else {
	          responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	          responseVO.setMessage("Updation Failed!!");
	      }
      return responseVO;
	 
	
	 
 }
 

 
//delete Sub category by id
 @DeleteMapping("/deleteSubCategory/{id}")
 public Map<String, Boolean> deleteSubCategory(@PathVariable(value = "id") Long subCategoryId)
      throws ResourceNotFoundException {
	 SubCategoryMaster subCategory = subCategoryJpaRepository.findById(subCategoryId)
    .orElseThrow(() -> new ResourceNotFoundException("SubCategory not found for this id :: " + subCategoryId));

	 subCategory.setDeletedFlag(false);
	 subCategoryJpaRepository.save(subCategory);
     Map<String, Boolean> response = new HashMap<>();
     response.put("deletedFlag", Boolean.TRUE);
     return response;
 }
  
//get All SubCategory list
 @GetMapping("/getSubCategories")
    public List<SubCategoryMaster> getAllSubCategories() {
        return subCategoryJpaRepository.findAll();
    }

 
	
	
}
