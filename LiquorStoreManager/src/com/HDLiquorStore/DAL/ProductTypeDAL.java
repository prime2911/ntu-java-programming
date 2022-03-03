package com.HDLiquorStore.DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.HDLiquorStore.DTO.ProductTypeDTO;

public class ProductTypeDAL {
	Connection conn = ConnectToDB.establishConnection("my_pub", "root", "");
	
	public ArrayList<ProductTypeDTO> selectAll() {
		ArrayList<ProductTypeDTO> typeList = new ArrayList<ProductTypeDTO>();
		
		try {
			// Step 2
			Statement stmt = conn.createStatement();
			// Step 3
			String sqlSelect = "SELECT MaLoai, TenLoai FROM loaimh";
			// Step 4
			ResultSet tblProduct = stmt.executeQuery(sqlSelect);
			// Step 5. Display results
			// While there still are lines in table
			while (tblProduct.next()) {
				// Get the data from each column
				String id = tblProduct.getString("MaLoai");
				String name = tblProduct.getString("TenLoai");
				
				ProductTypeDTO type = new ProductTypeDTO(id, name);
				typeList.add(type);
			}
			
			return typeList;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
