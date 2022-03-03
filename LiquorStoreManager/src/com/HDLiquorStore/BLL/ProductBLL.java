package com.HDLiquorStore.BLL;

import java.util.ArrayList;

import com.HDLiquorStore.DAL.ProductDAL;
import com.HDLiquorStore.DTO.ProductDTO;

public class ProductBLL {
	ProductDAL prodDAL = new ProductDAL();
	
	public boolean add(ProductDTO p) {
		return prodDAL.add(p);
	}
	
	public boolean remove(String id) {
		return prodDAL.remove(id);
	}
	
	public boolean update(ProductDTO p) {
		return prodDAL.update(p);
	}
	
	public ArrayList<ProductDTO> selectAll() {
		return prodDAL.selectAll();
	}
	
	public int selectPrice(String id) {
		return prodDAL.selectPrice(id);
	}
	
	public ArrayList<ProductDTO> searchByName(String name) {
		return prodDAL.searchByName(name);
	}
	
	public ArrayList<ProductDTO> searchByType(String type) {
		return prodDAL.searchByType(type);
	}
	
	public ArrayList<ProductDTO> searchById(String id) {
		return prodDAL.searchByID(id);
	}
}
