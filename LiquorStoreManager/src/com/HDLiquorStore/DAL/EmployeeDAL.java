package com.HDLiquorStore.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.HDLiquorStore.DTO.EmployeeDTO;

public class EmployeeDAL {
	Connection conn = ConnectToDB.establishConnection("my_pub", "root", "");
	
	// adds an employee, returns true if successful
	public boolean add(EmployeeDTO e) {
		String sqlAdd = "INSERT INTO nhanvien VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlAdd);
			
			cmd.setInt(1, e.getId());
			cmd.setString(2, e.getName());
			cmd.setDate(3, e.getDateOfBirth());
			cmd.setString(4, e.getPhone());
			cmd.setString(5, e.getCitizenId());
			cmd.setString(6, e.getUsername());
			cmd.setString(7, e.getPassword());
			cmd.setString(8, e.getRole());
			
			boolean added = cmd.execute();
			
			return !added;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	// removes an employee, returns true if successful
	public boolean remove(int id) {
		String sqlRemove = "DELETE FROM nhanvien WHERE MaNV = ?";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlRemove);
			
			cmd.setInt(1, id);
			
			boolean removed = cmd.execute();
			
			return !removed;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	// updates an employee, returns true if successful
	public boolean update(EmployeeDTO e) {
		String sqlUpdate = "UPDATE nhanvien SET HoTen = ?, NgaySinh = ?, SoDT = ?, CCCD = ?, TenDN = ?, MatKhau = ?, Quyen = ? WHERE MaNV = ?";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlUpdate);
			
			cmd.setString(1, e.getName());
			cmd.setDate(2, e.getDateOfBirth());
			cmd.setString(3, e.getPhone());
			cmd.setString(4, e.getCitizenId());
			cmd.setString(5, e.getUsername());
			cmd.setString(6, e.getPassword());
			cmd.setString(7, e.getRole());
			cmd.setInt(8, e.getId());
			
			boolean updated = cmd.execute();
			
			return !updated;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<EmployeeDTO> selectAll() {
		ArrayList<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		
		try {
			// Step 2
			Statement stmt = conn.createStatement();
			// Step 3
			String sqlSelect = "SELECT * FROM nhanvien";
			// Step 4
			ResultSet tblEmp = stmt.executeQuery(sqlSelect);
			// Step 5. Display results
			// While there still are lines in table
			while (tblEmp.next()) {
				// Get the data from each column
				int empId = tblEmp.getInt("MaNV");
				String empName = tblEmp.getString("HoTen");
				Date dateOfBirth = tblEmp.getDate("NgaySinh");
				String phone = tblEmp.getString("SoDT");
				String citId = tblEmp.getString("CCCD");
				String username = tblEmp.getString("TenDN");
				String password = tblEmp.getString("MatKhau");
				String role = tblEmp.getString("Quyen");
				
				EmployeeDTO employee = new EmployeeDTO(empId, empName, dateOfBirth, phone, citId, username, password, role);
				employeeList.add(employee);
			}
			
			return employeeList;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<EmployeeDTO> searchByName(String name) {
		ArrayList<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		String sqlSelectByName = "SELECT * FROM nhanvien WHERE HoTen LIKE ?";
//		String sqlSelectByName = "SELECT * FROM nhanvien WHERE HoTen LIKE '%" + name + "%'";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectByName);			
			cmd.setString(1, "%" + name + "%");
//			Statement cmd = conn.createStatement();
			
			ResultSet tblEmp = cmd.executeQuery();
//			ResultSet tblEmp = cmd.executeQuery(sqlSelectByName);
			
			while (tblEmp.next()) {
				// Get the data from each column
				int empId = tblEmp.getInt("MaNV");
				String empName = tblEmp.getString("HoTen");
				Date dateOfBirth = tblEmp.getDate("NgaySinh");
				String phone = tblEmp.getString("SoDT");
				String citId = tblEmp.getString("CCCD");
				String username = tblEmp.getString("TenDN");
				String password = tblEmp.getString("MatKhau");
				String role = tblEmp.getString("Quyen");
				
				EmployeeDTO employee = new EmployeeDTO(empId, empName, dateOfBirth, phone, citId, username, password, role);
				employeeList.add(employee);
			}
			
			return employeeList;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public EmployeeDTO searchByID(int id) {
		EmployeeDTO employee = new EmployeeDTO();
		String sqlSelectById = "SELECT * FROM nhanvien WHERE MaNV = ?";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectById);			
			cmd.setInt(1, id);
			
			ResultSet tblEmp = cmd.executeQuery();
			
			while (tblEmp.next()) {
				// Get the data from each column
				int empId = tblEmp.getInt("MaNV");
				String empName = tblEmp.getString("HoTen");
				Date dateOfBirth = tblEmp.getDate("NgaySinh");
				String phone = tblEmp.getString("SoDT");
				String citId = tblEmp.getString("CCCD");
				String username = tblEmp.getString("TenDN");
				String password = tblEmp.getString("MatKhau");
				String role = tblEmp.getString("Quyen");
				
				employee = new EmployeeDTO(empId, empName, dateOfBirth, phone, citId, username, password, role);
			}
			
			return employee;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public boolean loginCheck(String username, String password) {
		String sqlCheck = "select * from nhanvien where TenDN = ? and MatKhau = ?";
//		String sqlCheck = "select * from nhanvien where TenDN = '" + username + "' and MatKhau = '" + password + "'";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlCheck);
			cmd.setString(1, username);
			cmd.setString(2, password);			
//			Statement cmd = conn.createStatement();
			
			ResultSet result = cmd.executeQuery();
//			ResultSet result = cmd.executeQuery(sqlCheck);
			
			if (result.next())
				return true;
			else
				return false;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
}
