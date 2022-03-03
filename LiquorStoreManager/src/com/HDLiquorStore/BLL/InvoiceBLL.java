package com.HDLiquorStore.BLL;

import java.util.ArrayList;

import com.HDLiquorStore.DAL.InvoiceDAL;
import com.HDLiquorStore.DTO.InvoiceDTO;

public class InvoiceBLL {
	InvoiceDAL invDAL = new InvoiceDAL();
	
	public boolean add(InvoiceDTO i) {
		return invDAL.add(i);
	}
	
	public boolean remove(int id) {
		return invDAL.remove(id);
	}
	
	public boolean update(InvoiceDTO i) {
		return invDAL.update(i);
	}
	
	public ArrayList<InvoiceDTO> selectAll() {
		return invDAL.selectAll();
	}
	
	public InvoiceDTO searchById(int id) {
		return invDAL.searchById(id);
	}
	
	public ArrayList<InvoiceDTO> searchByEmp(int id) {
		return invDAL.searchByEmp(id);
	}
}
