package com.eve.spring.userone.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@Table(name = "user1")
@NamedQueries({ @NamedQuery(name = "UserOne.findAll", query = "SELECT u FROM UserOne u"),
//		@NamedQuery(name = "UserOne.findByUserId", query = "SELECT u FROM UserOne u WHERE u.userId = :userId"),
		@NamedQuery(name = "UserOne.findByEmail", query = "SELECT u FROM UserOne u WHERE u.email = :email"),
		@NamedQuery(name = "UserOne.findByUsername", query = "SELECT u FROM UserOne u WHERE u.username = :username") })
public class UserOne implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_num")
	private int userNum;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_success_login")
	private Date lastSuccessLogin;

	private String email;
	private String pwd;

	@Column(name = "pwd_attempts")
	private Integer pwdAttempts = 0;

	private String role = "ROLE_USER";
	private String status;
	private String username;
	private String displayName;

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Transient
	private String verifyPwd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return pwd;
	}

	public void setPassword(String password) {
		setPwd(password);
	}

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	//
	// TODO - End of profile fields
	//

	public UserOne() {
		this.status = "ACTIVE";
		this.created = new Date();
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	private String capitalize(final String line) {
		return line == null || line.length() == 0 ? null : Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	public String getVerifyPwd() {
		return verifyPwd;
	}

	public void setVerifyPwd(String verifyPwd) {
		this.verifyPwd = verifyPwd;
	}

	public String getFirstName() {
		return capitalize(firstName);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return capitalize(lastName);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getPwdAttempts() {
		if (pwdAttempts == null) {
			pwdAttempts = 0;
		}
		return pwdAttempts;
	}

	public void setPwdAttempts(Integer pwdAttempts) {
		this.pwdAttempts = pwdAttempts;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLastSuccessLogin() {
		return lastSuccessLogin;
	}

	public void setLastSuccessLogin(Date lastSuccessLogin) {
		this.lastSuccessLogin = lastSuccessLogin;
	}

	public boolean isActive() {
		return "ACTIVE".equalsIgnoreCase(status);
	}

	@Column(name = "reset_token")
	private String resetToken;

	@Column(name = "reset_token_expiry")
	private Instant resetTokenExpiry;

	// Add getters and setters
	public String getResetToken() {
	    return resetToken;
	}

	public void setResetToken(String resetToken) {
	    this.resetToken = resetToken;
	}

	public Instant getResetTokenExpiry() {
	    return resetTokenExpiry;
	}

	public void setResetTokenExpiry(Instant resetTokenExpiry) {
	    this.resetTokenExpiry = resetTokenExpiry;
	}


}