package com.upskillutoday.crmRoot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "updatedOn")
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empId", referencedColumnName = "employee_id")
    private EmployeeMaster employeeMaster;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leadId", referencedColumnName = "student_id")
    private LeadMaster leadMaster;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "remark_id", referencedColumnName = "remark_status_id")
    private RemarkMaster remarkMaster;

    public History() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public History(String comment, Date updatedOn, EmployeeMaster employeeMaster, LeadMaster leadMaster, RemarkMaster remarkMaster) {
        this.comment = comment;
        this.updatedOn = updatedOn;
        this.employeeMaster = employeeMaster;
        this.leadMaster = leadMaster;
        this.remarkMaster = remarkMaster;
    }
}
