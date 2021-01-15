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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeMaster employeeMaster;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryMaster categoryMaster;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "remark_status_id")
    private RemarkMaster remarkMaster;


    @Column(name = "comment")
    private String comment;

    @Column(name = "updatedOn")
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

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

    public CategoryMaster getCategoryMaster() {
        return categoryMaster;
    }

    public void setCategoryMaster(CategoryMaster categoryMaster) {
        this.categoryMaster = categoryMaster;
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
}
