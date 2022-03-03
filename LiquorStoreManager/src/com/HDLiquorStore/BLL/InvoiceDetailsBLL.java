package com.HDLiquorStore.BLL;

import java.util.ArrayList;

import com.HDLiquorStore.DAL.InvoiceDetailsDAL;
import com.HDLiquorStore.DTO.InvoiceDetailsDTO;

public class InvoiceDetailsBLL {
	InvoiceDetailsDAL deDAL = new InvoiceDetailsDAL();
	
	public boolean add(InvoiceDetailsDTO i) {
		return deDAL.add(i);
	}
	
	public boolean remove(String id) {
		return deDAL.remove(id);
	}
	
	public boolean update(InvoiceDetailsDTO i) {
		return deDAL.update(i);
	}
	
	public ArrayList<InvoiceDetailsDTO> selectAll() {
		return deDAL.selectAll();
	}
	
	public ArrayList<InvoiceDetailsDTO> selectByInvId(int id) {
		return deDAL.selectByInvId(id);
	}
}
