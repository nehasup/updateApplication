package com.upskillutoday.crmRoot.dto;


import java.util.Date;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;

public class EmployeeLeadDto {

	private Long empleadId;
    private EmployeeMaster employeeMaster;
    private LeadMaster leadMaster;
	private int updatedBy;
	private Date updatedOn;
	private boolean deletedFlag;
	private Long employeeId;
	private String[] studentId;

	public String[] getStudentId() {
		return studentId;
	}

	public void setStudentId(String[] studentId) {
		this.studentId = studentId;
	}

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

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
