package com.yoctotta.train;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	@Column(name = "user_name")
	private String user_name;
	@Column(name = "password")
	private String password;
	@Column(name = "email_id")
	private String email_id;
	@Column(name = "mob_no")
	private String mob_no;
	@Column(name = "dp")
	private String dp;

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String userName) {
		this.user_name = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailID() {
		return email_id;
	}

	public void setEmailID(String emailID) {
		this.email_id = emailID;
	}

	public String getMob() {
		return mob_no;
	}

	public void setMob(String mob) {
		this.mob_no = mob;
	}

	public String getDp() {
		return dp;
	}

	public void setDp(String dp) {
		this.dp = dp;
	}

}
