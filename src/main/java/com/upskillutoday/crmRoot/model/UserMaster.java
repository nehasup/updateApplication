package com.upskillutoday.crmRoot.model;



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="user_master")
public class UserMaster implements Serializable  {
	private static final long serialVersionUID = 5926468583005150707L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
		
	@Column(name="user_name")
	private String userName;
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="login_status")
	private String loginStatus;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	@Column(name="deleted_flag")
	private boolean deletedFlag;

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "user_role", 
//				joinColumns = @JoinColumn(name = "user_id"), 
//				inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<RoleMaster> role = new HashSet<>();
	  
	public UserMaster() {
	}
	
	
	public UserMaster(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}




//
//	public Set<RoleMaster> getRoles() {
//		return role;
//	}
//
//
//
//	public void setRoles(Set<RoleMaster> roles) {
//		this.role = roles;
//	}



	public boolean isDeletedFlag() {
		return deletedFlag;
	}



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public boolean isDeletedFlag(boolean b) {
		return deletedFlag;
	}

	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}





	

	



	
	
	
}
