package com.upskillutoday.crmRoot.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "institute_master")
public class InstituteMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "institute_id")
	private Long instituteId;

	@Column(name = "institute_name")
	private String instituteName;

	@Column(name = "lead_comitted")
	private String commitedLead;

	@Column(name = "conversion_per_comited")
	private String conversion;

	@Column(name = "addmission_comitted")
	private String addmissionComitted;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "decision_maker_name")
	private String designMaker;

	@Column(name = "complete_address")
	private String address;

	@Column(name = "additional_comitted")
	private String additionalCommited;

	@Column(name = "usps")
	private String usps;

	@Column(name = "city_to_target")
	private String city;

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	@Column(name = "locality_targeted")
	private String locality;


	@Column(name = "course_name")
	private String courseName;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private CategoryMaster categoryMaster;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column(name = "deleted_flag")
	private boolean deletedFlag;

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

	public String getCommitedLead() {
		return commitedLead;
	}

	public void setCommitedLead(String commitedLead) {
		this.commitedLead = commitedLead;
	}

	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
	}

	public String getAddmissionComitted() {
		return addmissionComitted;
	}

	public void setAddmissionComitted(String addmissionComitted) {
		this.addmissionComitted = addmissionComitted;
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

	public String getDesignMaker() {
		return designMaker;
	}

	public void setDesignMaker(String designMaker) {
		this.designMaker = designMaker;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdditionalCommited() {
		return additionalCommited;
	}

	public void setAdditionalCommited(String additionalCommited) {
		this.additionalCommited = additionalCommited;
	}

	public CategoryMaster getCategoryMaster() {
		return categoryMaster;
	}

	public void setCategoryMaster(CategoryMaster categoryMaster) {
		this.categoryMaster = categoryMaster;
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

	public String getUsps() {
		return usps;
	}

	public void setUsps(String usps) {
		this.usps = usps;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
