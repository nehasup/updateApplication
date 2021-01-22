package com.upskillutoday.crmRoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "updated_on")
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private EmployeeMaster employeeMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private LeadMaster leadMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "remark_id", referencedColumnName = "remark_status_id")
    private RemarkMaster remarkMaster;

    public History() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
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

    public RemarkMaster getRemarkMaster() {
        return remarkMaster;
    }

    public void setRemarkMaster(RemarkMaster remarkMaster) {
        this.remarkMaster = remarkMaster;
    }

    public History(String comment, Date updatedOn, EmployeeMaster employeeMaster, LeadMaster leadMaster, RemarkMaster remarkMaster) {
        this.comment = comment;
        this.updatedOn = updatedOn;
        this.employeeMaster = employeeMaster;
        this.leadMaster = leadMaster;
        this.remarkMaster = remarkMaster;
    }
}
