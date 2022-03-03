package com.HDLiquorStore.DTO;

public class ProductDTO {
	private String id;
	private String name;
	private int price;
	private String origins;
	private String typeId;
	private String type;
	
	public ProductDTO() {
//		super();
	}

	public ProductDTO(String id, String name, int price, String origins, String typeId, String type) {
//		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.origins = origins;
		this.typeId = typeId;
		this.type = type;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getOrigins() {
		return origins;
	}

	public void setOrigins(String origins) {
		this.origins = origins;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
