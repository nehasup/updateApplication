package com.upskillutoday.crmRoot.dto;

import java.util.Date;

public class InstituteDto {
	
	private long instituteId;
	private String instituteName;
	private String legalName;
	private String contactNo;
	private String emailId;
	private String address;
	private String gstNo;
	private String upSkillExecutive;
	private String courseName;
	private String designMaker;
	private String headCounselor;
	private String counselor;
	private String eligibility;
	private String targetLocality;
	private String ageLimit;
	private String perDayLead;
	private int updatedBy;
	private Date updated_on;
	private boolean deleted_flag;
	private String userName;
	private String pass;

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

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getUpSkillExecutive() {
		return upSkillExecutive;
	}

	public void setUpSkillExecutive(String upSkillExecutive) {
		this.upSkillExecutive = upSkillExecutive;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDesignMaker() {
		return designMaker;
	}

	public void setDesignMaker(String designMaker) {
		this.designMaker = designMaker;
	}

	public String getHeadCounselor() {
		return headCounselor;
	}

	public void setHeadCounselor(String headCounselor) {
		this.headCounselor = headCounselor;
	}

	public String getCounselor() {
		return counselor;
	}

	public void setCounselor(String counselor) {
		this.counselor = counselor;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	public String getTargetLocality() {
		return targetLocality;
	}

	public void setTargetLocality(String targetLocality) {
		this.targetLocality = targetLocality;
	}

	public String getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}

	public String getPerDayLead() {
		return perDayLead;
	}

	public void setPerDayLead(String perDayLead) {
		this.perDayLead = perDayLead;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public boolean isDeleted_flag() {
		return deleted_flag;
	}

	public void setDeleted_flag(boolean deleted_flag) {
		this.deleted_flag = deleted_flag;
	}

	public long getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(long instituteId) {
		this.instituteId = instituteId;
	}
}
