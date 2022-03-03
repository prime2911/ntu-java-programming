package com.HDLiquorStore.DTO;

public class ProductTypeDTO {
	String id;
	String name;
	
	public ProductTypeDTO() {
//		super();
	}

	public ProductTypeDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
