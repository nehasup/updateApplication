package com.upskillutoday.crmRoot.response;

import java.util.Date;

public class EmpLeadResponseDto {
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
	  private String instituteName;
	  private String employeeName;
	  private boolean assignLeadFlag;

	public EmpLeadResponseDto(
			Long studentId,
			String studentName,
			String contactNo,
			String emailId,
			String courseName,
			String city,
			String area,
			String modeOfCourse,
			String address,
			String budget,
			String modificationStage,
			String remark,
			String comments,
			String instituteName,
			String employeeName,
			boolean assignLeadFlag) {
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
		this.employeeName = employeeName;
		this.assignLeadFlag = assignLeadFlag;
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
}
