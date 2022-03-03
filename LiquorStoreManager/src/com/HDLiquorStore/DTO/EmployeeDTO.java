package com.HDLiquorStore.DTO;

import java.sql.Date;

public class EmployeeDTO {
	private int id;
	private String name;
	private Date dateOfBirth;
	private String phone, citizenId;
	private String username, password, role;
	
	public EmployeeDTO() {
//		super();
	}

	public EmployeeDTO(int id, String name, Date dateOfBirth, String phone, String citizenId, String username, String password, String role) {
//		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.citizenId = citizenId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
