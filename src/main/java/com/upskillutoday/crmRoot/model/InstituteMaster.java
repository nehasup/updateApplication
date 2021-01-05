package com.upskillutoday.crmRoot.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "institute_master")
public class InstituteMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "institute_id")
	private Long instituteId;

	@Column(name = "institute_name")
	private String instituteName;

	@Column(name = "legal_name")
	private String legalName;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "address")
	private String address;

	@Column(name = "gst_no")
	private String gstNo;

	@Column(name = "upskill_executive")
	private String upSkillExecutive;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "design_maker")
	private String designMaker;

	@Column(name = "head_counselor")
	private String headCounselor;

	@Column(name = "counselor")
	private String counselor;

	@Column(name = "eligibility")
	private String eligibility;

	@Column(name = "target_locality")
	private String targetLocality;

	@Column(name = "age_limit")
	private String ageLimit;

	@Column(name = "per_day_lead")
	private String perDayLead;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column(name = "deleted_flag")
	private boolean deletedFlag;

	
//	  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, 
//			  mappedBy ="institute")
//	  private UserMaster user;
//	 
//	

	public Long getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(Long instituteId) {
		this.instituteId = instituteId;
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

	
//	  public UserMaster getUser() { return user; }
//	  
//	  
//	  
//	  public void setUser(UserMaster user) { this.user = user; }
//	  
//	  
	  
	 

}
