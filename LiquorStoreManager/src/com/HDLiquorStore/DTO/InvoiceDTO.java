package com.HDLiquorStore.DTO;

import java.sql.Date;

public class InvoiceDTO {
	int id;
	Date saleDate;
	int empId;
	String empName;
	
	public InvoiceDTO() {
//		super();
	}

	public InvoiceDTO(int id, Date saleDate, int empId, String empName) {
//		super();
		this.id = id;
		this.saleDate = saleDate;
		this.empId = empId;
		this.empName = empName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
}
