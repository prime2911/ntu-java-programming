package com.HDLiquorStore.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.HDLiquorStore.DTO.ProductDTO;

public class ProductDAL {
	Connection conn = ConnectToDB.establishConnection("my_pub", "root", "");
	
	public boolean add(ProductDTO p) {
		String sqlAdd = "INSERT INTO mathang VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlAdd);
			
			cmd.setString(1, p.getId());
			cmd.setString(2, p.getName());
			cmd.setInt(3, p.getPrice());
			cmd.setString(4, p.getOrigins());
			cmd.setString(5, p.getTypeId());
			
			boolean added = cmd.execute();
			
			return !added;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean remove(String id) {
		String sqlRemove = "DELETE FROM mathang WHERE MaMH = ?";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlRemove);
			
			cmd.setString(1, id);
			
			boolean removed = cmd.execute();
			
			return !removed;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean update(ProductDTO p) {
		String sqlUpdate = "UPDATE mathang mh SET TenMH = ?, DonGia = ?, XuatXu = ?, MaLoai = ? WHERE MaMH = ?";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlUpdate);
			
			cmd.setString(1, p.getName());
			cmd.setInt(2, p.getPrice());
			cmd.setString(3, p.getOrigins());
			cmd.setString(4, p.getTypeId());
			cmd.setString(5, p.getId());
			
			boolean updated = cmd.execute();
			
			return !updated;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<ProductDTO> selectAll() {
		ArrayList<ProductDTO> prodList = new ArrayList<ProductDTO>();
		
		try {
			// Step 2
			Statement stmt = conn.createStatement();
			// Step 3
			String sqlSelect = "SELECT * FROM mathang mh JOIN loaimh lmh ON mh.MaLoai = lmh.MaLoai ORDER BY MaMH";
			// Step 4
			ResultSet tblProduct = stmt.executeQuery(sqlSelect);
			// Step 5. Display results
			// While there still are lines in table
			while (tblProduct.next()) {
				// Get the data from each column
				String id = tblProduct.getString("MaMH");
				String name = tblProduct.getString("TenMH");
				int price = tblProduct.getInt("DonGia");
				String origins = tblProduct.getString("XuatXu");
				String typeId = tblProduct.getString("MaLoai");
				String type = tblProduct.getString("TenLoai");
				
				ProductDTO product = new ProductDTO(id, name, price, origins, typeId, type);
				prodList.add(product);
			}
			
			return prodList;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public int selectPrice(String id) {
		int price = 0;
		String sqlSelectById = "SELECT * FROM mathang WHERE MaMH = ?";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectById);			
			cmd.setString(1, id);
			
			ResultSet tblPrice = cmd.executeQuery();
			
			while (tblPrice.next()) {
				// Get the data from each column
				price = tblPrice.getInt("DonGia");
			}
			
			return price;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return price;
		}
	}
	
	public ArrayList<ProductDTO> searchByName(String name) {
		ArrayList<ProductDTO> prodList = new ArrayList<ProductDTO>();
		String sqlSelectByName = "SELECT * FROM mathang mh JOIN loaimh lmh ON mh.MaLoai = lmh.MaLoai WHERE TenMH LIKE ? ORDER BY MaMH";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectByName);			
			cmd.setString(1, "%" + name + "%");
			
			ResultSet tblProduct = cmd.executeQuery();
			
			while (tblProduct.next()) {
				// Get the data from each column
				String prodId = tblProduct.getString("MaMH");
				String prodName = tblProduct.getString("TenMH");
				int price = tblProduct.getInt("DonGia");
				String origins = tblProduct.getString("XuatXu");
				String prodTypeId = tblProduct.getString("MaLoai");
				String type = tblProduct.getString("TenLoai");
				
				ProductDTO product = new ProductDTO(prodId, prodName, price, origins, prodTypeId, type);
				prodList.add(product);
			}
			
			return prodList;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<ProductDTO> searchByID(String id) {
		ArrayList<ProductDTO> prodList = new ArrayList<ProductDTO>();
		String sqlSelectById = "SELECT * FROM mathang mh JOIN loaimh lmh ON mh.MaLoai = lmh.MaLoai WHERE MaMH LIKE ? ORDER BY MaMH";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectById);			
			cmd.setString(1, "%" + id + "%");
			
			ResultSet tblProduct = cmd.executeQuery();
			
			while (tblProduct.next()) {
				// Get the data from each column
				String prodId = tblProduct.getString("MaMH");
				String prodName = tblProduct.getString("TenMH");
				int price = tblProduct.getInt("DonGia");
				String origins = tblProduct.getString("XuatXu");
				String prodTypeId = tblProduct.getString("MaLoai");
				String type = tblProduct.getString("TenLoai");
				
				ProductDTO product = new ProductDTO(prodId, prodName, price, origins, prodTypeId, type);
				prodList.add(product);
			}
			
			return prodList;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<ProductDTO> searchByType(String typeId) {
		ArrayList<ProductDTO> prodList = new ArrayList<ProductDTO>();
		String sqlSelectByName = "SELECT * FROM mathang mh JOIN loaimh lmh ON mh.MaLoai = lmh.MaLoai WHERE mh.MaLoai = ? ORDER BY MaMH";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectByName);			
			cmd.setString(1, typeId);
			
			ResultSet tblProduct = cmd.executeQuery();
			
			while (tblProduct.next()) {
				// Get the data from each column
				String prodId = tblProduct.getString("MaMH");
				String prodName = tblProduct.getString("TenMH");
				int price = tblProduct.getInt("DonGia");
				String origins = tblProduct.getString("XuatXu");
				String prodTypeId = tblProduct.getString("MaLoai");
				String type = tblProduct.getString("TenLoai");
				
				ProductDTO product = new ProductDTO(prodId, prodName, price, origins, prodTypeId, type);
				prodList.add(product);
			}
			
			return prodList;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
