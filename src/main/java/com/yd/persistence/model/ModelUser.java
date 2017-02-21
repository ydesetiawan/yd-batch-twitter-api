package com.yd.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.yd.persistence.repository.model.User;
import com.yd.persistence.repository.model.UserRole;

@Component
public class ModelUser {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(ModelUser.class);

	private String uuid;

	@Email(message = "Email address is not in the correct format.")
	@Size(max = 50, message = "Maximum size for email address is 50.")
	private String emailAddress;

	@NotEmpty(message = "Full name should contain a value.")
	@Size(max = 50, message = "Maximum size for full name is 50.")
	private String fullName;

	@Size(max = 50, message = "Maximum size for job title is 50.")
	private String jobTitle;

	@Size(max = 20, message = "Maximum size for mobile number is 20.")
	private String mobileNumber;

	private Set<UserRole> userRoles = new HashSet<UserRole>();

	private Set<String> roleNames = new HashSet<String>();

	@NotEmpty(message = "User name should contain a value.")
	@Size(max = 50, message = "Maximum size for user name is 50.")
	private String username;

	private String action;

	private boolean disabled = false;

	public ModelUser() {

	}

	public ModelUser(User user, String action) {
		this.uuid = user.getUuid();
		this.emailAddress = user.getEmailAddress();
		this.fullName = user.getFullName();
		this.mobileNumber = user.getMobileNumber();
		this.username = user.getUsername();
		this.userRoles = user.getUserRole();
		this.roleNames = getRoleNames();
		this.action = action;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}