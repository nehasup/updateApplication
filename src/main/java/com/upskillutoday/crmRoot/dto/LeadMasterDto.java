package com.upskillutoday.crmRoot.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upskillutoday.crmRoot.model.*;
import com.upskillutoday.crmRoot.repository.EmpLeadJpaRepository;
import com.upskillutoday.crmRoot.repository.EmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LeadMasterDto {

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
	private CategoryMaster categoryMaster;
	private SubCategoryMaster subCategoryMaster;
	private RemarkMaster remarkMaster;
	private String remarkName;
	private String employeeName;
	private boolean assignLeadFlag;
	private Long employeeId;

	public Long getEmployeeId() {
	return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
	this.employeeId = employeeId;
	}

	public boolean isAssignLeadFlag() {
	return assignLeadFlag;
	}

	public void setAssignLeadFlag(boolean assignLeadFlag) {
	this.assignLeadFlag = assignLeadFlag;
	}

	public String getEmployeeName() {
	return employeeName;
	}

	public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
	}

	public String getRemarkName() {
	return remarkName;
	}

	public void setRemarkName(String remarkName) {
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

	public int getUpdatedBy() {
	return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
	this.updatedBy = updatedBy;
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

	public Date getUpdatedOn() {
	return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
	this.updatedOn = updatedOn;
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

	public String getComments() {
	return comments;
	}

	public void setComments(String comments) {
	this.comments = comments;
	}

	public String getRemark() {
	return remark;
	}

	public void setRemark(String remark) {
	this.remark = remark;
	}

	public String getInstituteName() {
	return instituteName;
	}

	public void setInstituteName(String instituteName) {
	this.instituteName = instituteName;
	}

	public CategoryMaster getCategoryMaster() {
	return categoryMaster;
	}

	public void setCategoryMaster(CategoryMaster categoryMaster) {
	this.categoryMaster = categoryMaster;
	}

	public SubCategoryMaster getSubCategoryMaster() {
	return subCategoryMaster;
	}

	public void setSubCategoryMaster(SubCategoryMaster subCategoryMaster) {
	this.subCategoryMaster = subCategoryMaster;
	}

	public RemarkMaster getRemarkMaster() {
	return remarkMaster;
	}

	public void setRemarkMaster(RemarkMaster remarkMaster) {
	this.remarkMaster = remarkMaster;
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

	public LeadMasterDto(LeadMaster leadMaster) {
		this.setStudentId(leadMaster.getStudentId());
		this.setStudentName(leadMaster.getStudentName());
		this.setCourseName(leadMaster.getCourseName());
		this.setContactNo(leadMaster.getContactNo());
		this.setArea(leadMaster.getArea());
		this.setCity(leadMaster.getCity());
		this.setEmailId(leadMaster.getEmailId());
		this.setModeOfCourse(leadMaster.getModeOfCourse());
		this.setAddress(leadMaster.getAddress());
		this.setBudget(leadMaster.getBudget());
		this.setComments(leadMaster.getComments());
		this.setInstituteName(leadMaster.getInstituteName());
		this.setCategoryId(leadMaster.getCategoryMaster().getCategoryId());
		this.setCategoryName(leadMaster.getCategoryMaster().getCategoryName());
		this.setRemarkId(leadMaster.getRemarkMaster().getRemarkId());
		this.setRemarkName(leadMaster.getRemarkMaster().getRemarkName());
	}

	public LeadMasterDto () {}
} 
