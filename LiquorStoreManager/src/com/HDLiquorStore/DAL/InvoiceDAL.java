package com.HDLiquorStore.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.HDLiquorStore.DTO.InvoiceDTO;

public class InvoiceDAL {
	Connection conn = ConnectToDB.establishConnection("my_pub", "root", "");

	public boolean add(InvoiceDTO i) {
		String sqlAdd = "INSERT INTO hoadon VALUES (?, ?, ?)";

		try {
			PreparedStatement cmd = conn.prepareStatement(sqlAdd);

			cmd.setInt(1, i.getId());
			cmd.setDate(2, i.getSaleDate());
			cmd.setInt(3, i.getEmpId());

			boolean added = cmd.execute();

			return !added;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean remove(int id) {
		String sqlRemove = "DELETE FROM hoadon WHERE MaHD = ?";

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

	public boolean update(InvoiceDTO i) {
		String sqlUpdate = "UPDATE hoadon SET NgayLap = ?, MaNV = ? WHERE MaHD = ?";

		try {
			PreparedStatement cmd = conn.prepareStatement(sqlUpdate);

			cmd.setDate(1, i.getSaleDate());
			cmd.setInt(2, i.getEmpId());
			cmd.setInt(3, i.getId());

			boolean updated = cmd.execute();

			return !updated;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public ArrayList<InvoiceDTO> selectAll() {
		ArrayList<InvoiceDTO> invoiceList = new ArrayList<InvoiceDTO>();

		try {
			// Step 2
			Statement stmt = conn.createStatement();
			// Step 3
			String sqlSelect = "SELECT * FROM hoadon hd JOIN nhanvien nv ON hd.MaNV = nv.MaNV ORDER BY MaHD";
			// Step 4
			ResultSet tblInvoice = stmt.executeQuery(sqlSelect);
			// Step 5. Display results
			// While there still are lines in table
			while (tblInvoice.next()) {
				// Get the data from each column
				int id = tblInvoice.getInt("MaHD");
				Date date = tblInvoice.getDate("NgayLap");
				int empId = tblInvoice.getInt("MaNV");
				String empName = tblInvoice.getString("HoTen");

				InvoiceDTO invoice = new InvoiceDTO(id, date, empId, empName);
				invoiceList.add(invoice);
			}

			return invoiceList;

		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	public InvoiceDTO searchById(int id) {
		InvoiceDTO invoice = new InvoiceDTO();
		String sqlSelectById = "SELECT * FROM hoadon hd JOIN nhanvien nv ON hd.MaNV = nv.MaNV WHERE MaHD = ?";
//		String sqlSelectById = "SELECT * FROM hoadon WHERE MaHD = ?";

		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectById);			
			cmd.setInt(1, id);
			
			ResultSet tblInvoice = cmd.executeQuery();

			while (tblInvoice.next()) {
				// Get the data from each column
				int invId = tblInvoice.getInt("MaHD");
				Date invDate = tblInvoice.getDate("NgayLap");
				int empId = tblInvoice.getInt("MaNV");
				String empName = tblInvoice.getString("HoTen");

				invoice = new InvoiceDTO(invId, invDate, empId, empName);
			}

			return invoice;

		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	public ArrayList<InvoiceDTO> searchByDate(Date date) {
		ArrayList<InvoiceDTO> invoiceList = new ArrayList<InvoiceDTO>();
		String sqlSelectByDate = "SELECT * FROM hoadon hd JOIN nhanvien nv ON hd.MaNV = nv.MaNV WHERE NgayLap = ? ORDER BY MaHD";

		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectByDate);			
			cmd.setDate(1, date);
			
			ResultSet tblInvoice = cmd.executeQuery();

			while (tblInvoice.next()) {
				// Get the data from each column
				int invId = tblInvoice.getInt("MaHD");
				Date invDate = tblInvoice.getDate("NgayLap");
				int empId = tblInvoice.getInt("MaNV");
				String empName = tblInvoice.getString("HoTen");

				InvoiceDTO invoice = new InvoiceDTO(invId, invDate, empId, empName);
				invoiceList.add(invoice);
			}

			return invoiceList;

		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	public ArrayList<InvoiceDTO> searchByEmp(int id) {
		ArrayList<InvoiceDTO> invoiceList = new ArrayList<InvoiceDTO>();
		String sqlSelectByEmp = "SELECT * FROM hoadon hd JOIN nhanvien nv ON hd.MaNV = nv.MaNV WHERE hd.MaNV = ? ORDER BY MaHD";
//		String sqlSelectByEmp = "SELECT * FROM hoadon WHERE MaNV = ?";

		try {
			PreparedStatement cmd = conn.prepareStatement(sqlSelectByEmp);			
			cmd.setInt(1, id);
			
			ResultSet tblInvoice = cmd.executeQuery();

			while (tblInvoice.next()) {
				// Get the data from each column
				int invId = tblInvoice.getInt("MaHD");
				Date invDate = tblInvoice.getDate("NgayLap");
				int empId = tblInvoice.getInt("MaNV");
				String empName = tblInvoice.getString("HoTen");

				InvoiceDTO invoice = new InvoiceDTO(invId, invDate, empId, empName);
				invoiceList.add(invoice);
			}

			return invoiceList;

		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
