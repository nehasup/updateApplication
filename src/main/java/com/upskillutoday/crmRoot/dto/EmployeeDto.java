package com.upskillutoday.crmRoot.dto;

import java.util.Date;
import java.util.Set;

import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.RoleMaster;
import com.upskillutoday.crmRoot.model.UserMaster;


public class EmployeeDto {

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
	private Long categoryId;
	private Long roleId;
	private String userName;
	private String pass;
	private UserMaster users;
	private Long userId;
	public Long[] categories;
	private RoleMaster roles;
	private Integer responseCode;
	private String message;

	public Long[] getCategories() {
		return categories;
	}

	public void setCategories(Long[] categories) {
		this.categories = categories;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public UserMaster getUsers() {
		return users;
	}

	public void setUsers(UserMaster users) {
		this.users = users;
	}

	public RoleMaster getRoles() {
		return roles;
	}

	public void setRoles(RoleMaster roles) {
		this.roles = roles;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
