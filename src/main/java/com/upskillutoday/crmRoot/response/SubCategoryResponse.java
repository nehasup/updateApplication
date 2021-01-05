package com.upskillutoday.crmRoot.response;

import java.util.Date;

import com.upskillutoday.crmRoot.model.CategoryMaster;

public class SubCategoryResponse {
private Long subCategoryId;
	
	private String subCategoryName;
	
	private int updatedBy;

	private Date updatedOn;

	private boolean deletedFlag;
	
    private String categoryName;
    
	private Long categoryId;
	
	
	

	public SubCategoryResponse(Long subCategoryId, String subCategoryName,Long categoryId,String categoryName) {
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
	
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public boolean isDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	

}
