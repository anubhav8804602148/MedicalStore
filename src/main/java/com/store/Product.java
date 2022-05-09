package com.store;

import java.sql.Date;  
import javax.persistence.*;

@Entity(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private double costPrice;
	@Column
	private Date mfgDate;
	@Column
	private Date expiryDate;
	@Column
	private int batchNo;
	@Column
	private Date purchasingDate;
	@Column
	private double sellingPrice=0.0;
	@Column
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public Date getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public Date getPurchasingDate() {
		return purchasingDate;
	}
	public void setPurchasingDate(Date purchasingDate) {
		this.purchasingDate = purchasingDate;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return String.format("Name : %s Batch No : %d Id : %d Expiry Date : %s",
				this.name,
				this.batchNo,
				this.id,
				this.expiryDate);
	}
}
