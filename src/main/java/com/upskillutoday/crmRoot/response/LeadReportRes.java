package com.upskillutoday.crmRoot.response;

public class LeadReportRes {
    private String studentName;
    private String contactNo;
    private String city;
    private String area;
    private String address;
    private String emailId;
    private String courseName;
    private String comments;
    private String instituteName;
    private String leadStatus;
    private String updatedOn;
    private String employeeName;

    public LeadReportRes() {}

    public LeadReportRes(
            String studentName,
            String contactNo,
            String city,
            String area,
            String address,
            String emailId,
            String courseName,
            String comments,
            String instituteName,
            String leadStatus,
            String employeeName) {
        this.studentName = studentName;
        this.contactNo = contactNo;
        this.city = city;
        this.area = area;
        this.address = address;
        this.emailId = emailId;
        this.courseName = courseName;
        this.comments = comments;
        this.instituteName = instituteName;
        this.leadStatus = leadStatus;
        this.employeeName=employeeName;
    }

    public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
}
