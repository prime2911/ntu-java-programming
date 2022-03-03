package com.HDLiquorStore.DTO;

public class InvoiceDetailsDTO {
	int invId;
	String prodId;
	String prodName;
	int prodPrice;
	int quantity;
	int totalPrice;
	
	public InvoiceDetailsDTO() {
//		super();
	}

	public InvoiceDetailsDTO(int invId, String prodId, String prodName, int prodPrice, int quantity, int totalPrice) {
//		super();
		this.invId = invId;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public int getInvId() {
		return invId;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodName) {
		this.prodId = prodName;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
