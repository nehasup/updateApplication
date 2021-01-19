package com.upskillutoday.crmRoot.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class LeadResponseDto {

	@Id
	@JsonIgnore
	private Long Id;

	private Long studentId;
	private String studentName;
	private String courseName;
	private String contactNo;
	private String area;
	private String city;
	private String emailId;
	private String modeOfCourse;
	private String modificationStage;
	private String address;
	private String budget;
	private Long remarkId;
	private String remarkName;
	private String comments;
	private String instituteName;
	private Date updatedOn;
	private String categoryName;
	private Long categoryId;
	private String employeeName;
	private String roleName;
	private String remark;
	private Long updatedBy;

	public LeadResponseDto(
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
			Long categoryId,
			String categoryName,
			Long remarkId,
			String remarkName,
			Long updatedBy,
			Date updatedOn) {
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
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public LeadResponseDto(Long studentId, String studentName, String courseName, String contactNo, String area, String city, String emailId, String modeOfCourse, String modificationStage, String address, String budget, Long remarkId, String remarkName, String comments, String instituteName, Long categoryId, String categoryName, Date updatedOn, String employeeName, String roleName) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.courseName = courseName;
		this.contactNo = contactNo;
		this.area = area;
		this.city = city;
		this.emailId = emailId;
		this.modeOfCourse = modeOfCourse;
		this.modificationStage = modificationStage;
		this.address = address;
		this.budget = budget;
		this.remarkId = remarkId;
		this.remarkName = remarkName;
		this.comments = comments;
		this.instituteName = instituteName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.updatedOn = updatedOn;
		this.employeeName = employeeName;
		this.roleName = roleName;
	}

	public LeadResponseDto() {}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getModeOfCourse() {
		return modeOfCourse;
	}

	public void setModeOfCourse(String modeOfCourse) {
		this.modeOfCourse = modeOfCourse;
	}

	public String getModificationStage() {
		return modificationStage;
	}

	public void setModificationStage(String modificationStage) {
		this.modificationStage = modificationStage;
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

	public Long getRemarkId() {
		return remarkId;
	}

	public void setRemarkId(Long remarkId) {
		this.remarkId = remarkId;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
