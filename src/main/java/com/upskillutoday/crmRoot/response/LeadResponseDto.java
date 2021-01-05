package com.upskillutoday.crmRoot.response;

import java.util.Date;



public class LeadResponseDto {

	 private Long studentId;
		
	  private String studentName;
	
	  private String contactNo;
	
	  private String emailId;
	
	  private String courseName;
	  
	  private String city;
	  
	  private String area;
	  
	  private String modeOfCourse;
	  
	  private String address;
	  
	  private String budget;
	  
	  private String modificationStage;
	  
	  private String remark;
	  
	  private String comments;

	  private int updatedBy;
	  
	  private Date updatedOn;
	  
	  private boolean deletedFlag;

	  private String fileType;
	  
	  private String instituteName;
	  
	  private Long categoryId;
	  
	  private Long subCategoryId;
	  
	  private Long remarkId;
	  	  
	  private String categoryName;
		
	  private String subCategoryName;
		
	  private String remarkName;
	  
	  

	public LeadResponseDto(Long studentId, String studentName, String contactNo, String emailId, String courseName,
			String city, String area, String modeOfCourse, String address, String budget, String modificationStage,
			String remark, String comments,String instituteName, Long categoryId,String categoryName,Long remarkId,String remarkName) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.courseName = courseName;
		this.city = city;
		this.area = area;
		this.modeOfCourse = modeOfCourse;
		this.address = address;
		this.budget = budget;
		this.modificationStage = modificationStage;
		this.remark = remark;
		this.comments = comments;
		this.instituteName = instituteName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.remarkId= remarkId;
		this.remarkName = remarkName;
	
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getModeOfCourse() {
		return modeOfCourse;
	}

	public void setModeOfCourse(String modeOfCourse) {
		this.modeOfCourse = modeOfCourse;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getModificationStage() {
		return modificationStage;
	}

	public void setModificationStage(String modificationStage) {
		this.modificationStage = modificationStage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Long getRemarkId() {
		return remarkId;
	}

	public void setRemarkId(Long remarkId) {
		this.remarkId = remarkId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
		
		
	
}
