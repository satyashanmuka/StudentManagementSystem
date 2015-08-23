package com.ku.eecs.beans;

import java.io.Serializable;


public class AccountRequestBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int requestId;
	String userName;
	String password;
	String emailId;
	String kuId;
	String kuOnlineId;
	String firstName;
	String lastName;
	String requestedDate;
	int departmentId;
	String departmentName;
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getKuId() {
		return kuId;
	}
	public void setKuId(String kuId) {
		this.kuId = kuId;
	}
	public String getKuOnlineId() {
		return kuOnlineId;
	}
	public void setKuOnlineId(String kuOnlineId) {
		this.kuOnlineId = kuOnlineId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
