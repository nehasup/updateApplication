package com.upskillutoday.crmRoot.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeNameWithId {

    @Id
    @JsonIgnore
    private Long id;

    private Long empId;
    private String employeeName;

    public EmployeeNameWithId(Long empId, String employeeName) {
        this.empId = empId;
        this.employeeName = employeeName;
    }

    public EmployeeNameWithId() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
