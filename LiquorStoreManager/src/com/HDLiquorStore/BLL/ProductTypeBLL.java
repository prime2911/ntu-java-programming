package com.HDLiquorStore.BLL;

import java.util.ArrayList;

import com.HDLiquorStore.DAL.ProductTypeDAL;
import com.HDLiquorStore.DTO.ProductTypeDTO;

public class ProductTypeBLL {
	ProductTypeDAL typeDAL = new ProductTypeDAL();
	
	public ArrayList<ProductTypeDTO> selectAll() {
		return typeDAL.selectAll();
	}
}
