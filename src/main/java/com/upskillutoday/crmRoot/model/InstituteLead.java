package com.upskillutoday.crmRoot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "institute_lead")
public class InstituteLead {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idInstitute_lead")
    private Long instituteleadId;

    @Column(name = "sent_on")
    private Date sentOn;

    @Column(name = "should_count")
    private int shouldCount;

    @JoinColumn(name="institute_id",referencedColumnName="institute_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private InstituteMaster instituteMaster;

    @JoinColumn(name="student_id",referencedColumnName="student_id")

    @ManyToOne(fetch = FetchType.EAGER)
    private LeadMaster leadMaster;

    @JoinColumn(name="sent_by", referencedColumnName = "employee_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeeMaster employeeMaster;

    public InstituteLead() {
    }

    public Long getInstituteleadId() {
        return instituteleadId;
    }

    public void setInstituteleadId(Long instituteleadId) {
        this.instituteleadId = instituteleadId;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public InstituteMaster getInstituteMaster() {
        return instituteMaster;
    }

    public void setInstituteMaster(InstituteMaster instituteMaster) {
        this.instituteMaster = instituteMaster;
    }

    public LeadMaster getLeadMaster() {
        return leadMaster;
    }

    public void setLeadMaster(LeadMaster leadMaster) {
        this.leadMaster = leadMaster;
    }

    public InstituteLead(Date sentOn, InstituteMaster instituteMaster, LeadMaster leadMaster) {
        this.sentOn = sentOn;
        this.instituteMaster = instituteMaster;
        this.leadMaster = leadMaster;
    }

    public InstituteLead(Date sentOn, InstituteMaster instituteMaster, LeadMaster leadMaster, EmployeeMaster employeeMaster) {
        this.sentOn = sentOn;
        this.instituteMaster = instituteMaster;
        this.leadMaster = leadMaster;
        this.employeeMaster = employeeMaster;
    }

    public InstituteLead(Date sentOn, InstituteMaster instituteMaster, LeadMaster leadMaster, EmployeeMaster employeeMaster, int shouldCount) {
        this.sentOn = sentOn;
        this.instituteMaster = instituteMaster;
        this.leadMaster = leadMaster;
        this.employeeMaster = employeeMaster;
        this.shouldCount = shouldCount;
    }

    public EmployeeMaster getEmployeeMaster() {
        return employeeMaster;
    }

    public void setEmployeeMaster(EmployeeMaster employeeMaster) {
        this.employeeMaster = employeeMaster;
    }

    public int getShouldCount() {
        return shouldCount;
    }

    public void setShouldCount(int shouldCount) {
        this.shouldCount = shouldCount;
    }
}
