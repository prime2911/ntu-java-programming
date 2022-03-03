package com.HDLiquorStore.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HDLiquorStore.DTO.InvoiceDetailsDTO;

public class InvoiceDetailsDAL {
	Connection conn = ConnectToDB.establishConnection("my_pub", "root", "");

	public boolean add(InvoiceDetailsDTO i) {
		String sqlAdd = "INSERT INTO cthd VALUES (?, ?, ?)";

		try {
			PreparedStatement cmd = conn.prepareStatement(sqlAdd);

			cmd.setInt(1, i.getInvId());
			cmd.setString(2, i.getProdId());
			cmd.setInt(3, i.getQuantity());

			boolean added = cmd.execute();

			return !added;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean remove(String id) {
		String sqlRemove = "DELETE FROM cthd WHERE MaMH = ?";

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

	public boolean update(InvoiceDetailsDTO i) {
//		String sqlUpdate = "UPDATE cthd SET MaMH = ?, SoLuong = ? WHERE MaHD = ?";
		String sqlUpdate1 = "DELETE FROM cthd WHERE MaMH = ?";
		String sqlUpdate2 = "INSERT INTO cthd VALUES (?, ?, ?)";

		try {
//			PreparedStatement cmd = conn.prepareStatement(sqlUpdate);

//			cmd.setString(1, i.getProdId());
//			cmd.setInt(2, i.getQuantity());
//			cmd.setInt(3, i.getInvId());
			
			PreparedStatement cmd1 = conn.prepareStatement(sqlUpdate1);
			
			cmd1.setString(1, i.getProdId());
			
			PreparedStatement cmd2 = conn.prepareStatement(sqlUpdate2);
			
			cmd2.setInt(1, i.getInvId());
			cmd2.setString(2, i.getProdId());
			cmd2.setInt(3, i.getQuantity());

//			boolean updated = cmd.execute();
			boolean updated = cmd1.execute();
			updated = cmd2.execute();

			return !updated;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public ArrayList<InvoiceDetailsDTO> selectAll() {
		ArrayList<InvoiceDetailsDTO> detailsList = new ArrayList<InvoiceDetailsDTO>();
		String sqlSelect = "SELECT cthd.MaHD, cthd.MaMH, TenMH, DonGia, SoLuong, DonGia * SoLuong as ThanhTien "
				+ "FROM cthd JOIN hoadon hd ON cthd.MaHD = hd.MaHD JOIN mathang mh ON cthd.MaMH = mh.MaMH ";
//		String sqlSelect = "SELECT cthd.MaHD, cthd.MaMH, TenMH, DonGia, SoLuong, DonGia * SoLuong as ThanhTien "
//				+ "FROM cthd JOIN hoadon hd on cthd.MaHD = hd.MaHD JOIN mathang mh on cthd.MaMH = mh.MaMH "
//				+ "WHERE cthd.SoHD = ?";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelect);
			
			// Step 4
			ResultSet tblDetails = cmd.executeQuery(sqlSelect);
			// Step 5. Display results
			// While there still are lines in table
			while (tblDetails.next()) {
				// Get the data from each column
				int invId = tblDetails.getInt("MaHD");
				String prodId = tblDetails.getString("MaMH");
				String prodName = tblDetails.getString("TenMH");
				int prodPrice = tblDetails.getInt("DonGia");
				int quantity = tblDetails.getInt("SoLuong");
				int totalPrice = tblDetails.getInt("ThanhTien");

				InvoiceDetailsDTO detail = new InvoiceDetailsDTO(invId, prodId, prodName, prodPrice, quantity, totalPrice);
				detailsList.add(detail);
			}

			return detailsList;

		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<InvoiceDetailsDTO> selectByInvId(int id) {
		ArrayList<InvoiceDetailsDTO> detailsList = new ArrayList<InvoiceDetailsDTO>();
		String sqlSelect = "SELECT cthd.MaHD, cthd.MaMH, TenMH, DonGia, SoLuong, DonGia * SoLuong as ThanhTien "
				+ "FROM cthd JOIN hoadon hd on cthd.MaHD = hd.MaHD JOIN mathang mh on cthd.MaMH = mh.MaMH "
				+ "WHERE cthd.MaHD = ?";
		
		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelect);
			cmd.setInt(1, id);
			
			// Step 4
			ResultSet tblDetails = cmd.executeQuery();
			// Step 5. Display results
			// While there still are lines in table
			while (tblDetails.next()) {
				// Get the data from each column
				int invId = tblDetails.getInt("MaHD");
				String prodId = tblDetails.getString("MaMH");
				String prodName = tblDetails.getString("TenMH");
				int prodPrice = tblDetails.getInt("DonGia");
				int quantity = tblDetails.getInt("SoLuong");
				int totalPrice = tblDetails.getInt("ThanhTien");

				InvoiceDetailsDTO detail = new InvoiceDetailsDTO(invId, prodId, prodName, prodPrice, quantity, totalPrice);
				detailsList.add(detail);
			}

			return detailsList;

		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
