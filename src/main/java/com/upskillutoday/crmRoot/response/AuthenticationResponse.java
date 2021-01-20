package com.upskillutoday.crmRoot.response;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthenticationResponse {

    @Id
    private int id;
    private Long employeeId;
    private String employeeName;
    private Long roleId;
    private String roleName;
    private Long userId;
    private String username;
    private String contact;
    private String emailId;
    private final String token;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AuthenticationResponse() {
        this.token = "";
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public AuthenticationResponse(Long userId, String username, Long employeeId, String employeeName, Long roleId, String roleName, String contact, String emailId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.userId = userId;
        this.contact = contact;
        this.emailId = emailId;
        this.username = username;
        this.token = "";
    }

    public void setOthers(Long employeeId, String employeeName, Long roleId, String roleName, Long userId, String contact, String emailId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.userId = userId;
        this.contact = contact;
        this.emailId = emailId;
    }
}
