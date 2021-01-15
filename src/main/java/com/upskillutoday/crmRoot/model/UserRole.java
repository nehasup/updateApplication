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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_role")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_role_id")
	private Long userroleId;
	
	
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private UserMaster users;
	
	@JoinColumn(name="role_id",referencedColumnName="role_id")
	@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private RoleMaster roles;
	
	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column(name = "deleted_flag")
	private boolean deletedFlag;

	@Transient
	private Long userId;
	
	@Transient
	private Long roleId;

	public RoleMaster getRoles() {
		return roles;
	}

	public void setRoles(RoleMaster roles) {
		this.roles = roles;
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

	public Long getUserroleId() {
		return userroleId;
	}

	public void setUserroleId(Long userroleId) {
		this.userroleId = userroleId;
	}

	public UserMaster getUsers() {
		return users;
	}

	public void setUsers(UserMaster users) {
		this.users = users;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
	

}
