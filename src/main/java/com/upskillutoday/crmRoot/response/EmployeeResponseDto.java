package com.upskillutoday.crmRoot.response;

import java.util.Date;

public class EmployeeResponseDto {
	
	private Long employeeId;
	
	private String employeeName;
	
	private String contactNo;
	
	private String guardianNo;
	
	private String emailId;
	
	private String address;
	
	private Date birthDate;
	
	private String gender;
	
	private int updatedBy;

	private Date updatedOn;

	private boolean deletedFlag;
	
	private String categoryName;
    
	private Long categoryId;
	
	

	public EmployeeResponseDto(Long employeeId, String employeeName, String contactNo, String guardianNo,
			String emailId, String address, Date birthDate, String gender, String categoryName, Long categoryId) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.contactNo = contactNo;
		this.guardianNo = guardianNo;
		this.emailId = emailId;
		this.address = address;
		this.birthDate = birthDate;
		this.gender = gender;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getGuardianNo() {
		return guardianNo;
	}

	public void setGuardianNo(String guardianNo) {
		this.guardianNo = guardianNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
