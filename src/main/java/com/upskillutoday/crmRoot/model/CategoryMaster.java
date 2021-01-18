package com.upskillutoday.crmRoot.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "category")
public class CategoryMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column(name = "deleted_flag")
	private boolean deletedFlag;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SubCategoryMaster> subCategoryList;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EmployeeMaster> employeeList;
	
	public Long getCategoryId() {
		return categoryId;
	}

	public  void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public List<SubCategoryMaster> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SubCategoryMaster> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public List<EmployeeMaster> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeMaster> employeeList) {
		this.employeeList = employeeList;
	}
}
