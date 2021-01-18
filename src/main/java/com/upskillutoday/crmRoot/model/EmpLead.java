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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="emp_lead")
public class EmpLead {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idemp_lead")
	private Long empleadId;
	
	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column(name = "deleted_flag")
	private boolean deletedFlag;

	@JoinColumn(name="employee_id",referencedColumnName="employee_id")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private EmployeeMaster employeeMaster;

	@JoinColumn(name="student_id",referencedColumnName="student_id")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private LeadMaster leadMaster;

	public Long getEmpleadId() {
		return empleadId;
	}

	public void setEmpleadId(Long empleadId) {
		this.empleadId = empleadId;
	}

	public EmployeeMaster getEmployeeMaster() {
		return employeeMaster;
	}

	public void setEmployeeMaster(EmployeeMaster employeeMaster) {
		this.employeeMaster = employeeMaster;
	}

	public LeadMaster getLeadMaster() {
		return leadMaster;
	}

	public void setLeadMaster(LeadMaster leadMaster) {
		this.leadMaster = leadMaster;
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
