package com.HDLiquorStore.BLL;

//import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

import com.HDLiquorStore.DAL.EmployeeDAL;
import com.HDLiquorStore.DTO.EmployeeDTO;

public class EmployeeBLL {
	EmployeeDAL empDAL = new EmployeeDAL();
	
	public boolean add(EmployeeDTO e) {
		return empDAL.add(e);
	}
	
	// removes an employee, returns true if successful
	public boolean remove(int id) {
		return empDAL.remove(id);
	}
	
	// updates an employee, returns true if successful
	public boolean update(EmployeeDTO e) {
		return empDAL.update(e);
	}
	
	public ArrayList<EmployeeDTO> selectAll() {	
		return empDAL.selectAll();
	}
	
	public ArrayList<EmployeeDTO> searchByName(String name) {
		return empDAL.searchByName(name);
	}
	
	public EmployeeDTO searchByID(int id) {	
		return empDAL.searchByID(id);
	}
	
	public boolean loginCheck(String username, String password) {
		boolean result = empDAL.loginCheck(username, password);
		
		return result;
	}
}
