package com.upskillutoday.crmRoot.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstituteReport {
    @JsonIgnore
    private Long id;
    private String name;
    private Long totalCount;
    private String employeeName;
    private Long count;
    private String commitedLead;

    public InstituteReport() {}

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public InstituteReport(Long id, String name, String employeeName, Long count) {
        this.id = id;
        this.name = name;
        this.employeeName = employeeName;
        this.count = count;
        this.commitedLead = "";
    }

    public InstituteReport(Long id, String name, String employeeName, Long count, String commitedLead) {
        this.id = id;
        this.name = name;
        this.employeeName = employeeName;
        this.count = count;
        this.commitedLead = commitedLead;
    }

    public InstituteReport(String name, Long totalCount, String employeeName, Long count) {
        this.name = name;
        this.totalCount = totalCount;
        this.employeeName = employeeName;
        this.count = count;
        this.commitedLead = "";
    }

    public InstituteReport(Long id, String name) {
        this.id = id;
        this.name = name;
        this.totalCount = 0L;
        this.employeeName = "";
        this.count = 0L;
        this.commitedLead = "";
    }

    public InstituteReport(Long id, String name, String commitedLead) {
        this.id = id;
        this.name = name;
        this.totalCount = 0L;
        this.employeeName = "";
        this.count = 0L;
        this.commitedLead = commitedLead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCommitedLead() {
        return commitedLead;
    }

    public void setCommitedLead(String commitedLead) {
        this.commitedLead = commitedLead;
    }
}
