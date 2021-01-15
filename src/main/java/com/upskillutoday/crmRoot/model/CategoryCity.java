package com.upskillutoday.crmRoot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "category_city")
public class CategoryCity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_city_id")
	private Long categoryCityId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="city_id")
	private CityMaster city;
	
	@Column(name="city_id",insertable = false, updatable = false)
	private Long cityId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private CategoryMaster category;
	
	@Column(name="category_id",insertable = false, updatable = false)
	private Long categoryId;
	
	
	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column(name = "deleted_flag")
	private boolean deletedFlag;

	public Long getCategoryCityId() {
		return categoryCityId;
	}

	public void setCategoryCityId(Long categoryCityId) {
		this.categoryCityId = categoryCityId;
	}

	public CityMaster getCity() {
		return city;
	}

	public void setCity(CityMaster city) {
		this.city = city;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public CategoryMaster getCategory() {
		return category;
	}

	public void setCategory(CategoryMaster category) {
		this.category = category;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	
	

}
