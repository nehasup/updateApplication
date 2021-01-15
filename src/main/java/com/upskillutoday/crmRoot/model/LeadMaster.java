package com.upskillutoday.crmRoot.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="lead_master")
public class LeadMaster {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column(name="student_id")
	  private Long studentId;
	
	  @Column(name="student_name")
	  private String studentName;
	
	  @Column(name="contact_no")
	  private String contactNo;
	
	  @Column(name="email_id")
	  private String emailId;
	
	  @Column(name="course_name")
	  private String courseName;
	  
	  @Column(name="city") 
	  private String city;
	  
	  @Column(name="area")
	  private String area;
	  
	  @Column(name="mode_of_course")
	  private String modeOfCourse;
	  
	  @Column(name="address")
	  private String address;
	  
	  @Column(name="budget")
	  private String budget;
	  
	  @Column(name="modification_stage")
	  private String modificationStage;
	  
	  @Column(name="remark")
	  private String remark;
	  
	  @Column(name="comments")
	  private String comments;
	  
	  @Column(name="institute_name")
	  private String instituteName;
	 
	  @Column(name="updated_by") 
	  private Long updatedBy;
	  
	  @Column(name="updatedOn")	 
	  @Temporal(TemporalType.DATE)
	  private Date updatedOn;
	  
	  @Column(name="deleted_flag") 
	  private boolean deletedFlag;
	 

	  @Column(name="file_type")
	  private String fileType;
	  
	  @Column(name="assign_lead_flag")
	  private boolean assignLeadFlag;
	
	

	@Transient
	  private MultipartFile file;


	  public LeadMaster() {

	   }


	  @JoinColumn(name="category_id",referencedColumnName="category_id")
		@JsonIgnore
	    @ManyToOne(fetch = FetchType.EAGER)
	    private CategoryMaster categoryMaster;
	  
	  @JoinColumn(name="subcategory_id",referencedColumnName="subcategory_id")
		@JsonIgnore
	    @ManyToOne(fetch = FetchType.EAGER)
	    private SubCategoryMaster subCategoryMaster;
	  
	  @JoinColumn(name="remark_status_id",referencedColumnName="remark_status_id")
		@JsonIgnore
	    @ManyToOne(fetch = FetchType.EAGER)
	    private RemarkMaster remarkMaster;

	
@Transient
private Long categoryId;

@Transient
private Long subCategoryId;

@Transient
private Long remarkId;

@Transient
private String remarkName;


@Transient
private String employeeName;








	

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




	public Long getUpdatedBy() {
		return updatedBy;
	}




	public void setUpdatedBy(Long updatedBy) {
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



	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}


	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}


	public LeadMaster(Long studentId, String studentName, String contactNo, String emailId, String fileType
			) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.fileType = fileType;
		
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

	public boolean isAssignLeadFlag() {
		return assignLeadFlag;
	}

	public void setAssignLeadFlag(boolean assignLeadFlag) {
		this.assignLeadFlag = assignLeadFlag;
	}

	
		
	

}
