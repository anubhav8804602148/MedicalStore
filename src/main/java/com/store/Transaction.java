package com.store;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;

import javax.persistence.*;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name="transactions")
public class Transaction implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;
	@Column
	private int productId;
	@Column
	private String productName;
	@Column
	private Date transactionDate;
	@Column
	private int quantity;
	@Column
	private int batchNumber;
	@Column
	private double sellingPrice;
	@Column
	private double costPrice;
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public String toString() {
		HashMap<String, String> transactionMap = new HashMap<String, String>();
		transactionMap.put("productName", productName);
		transactionMap.put("costPrice", costPrice+"");
		transactionMap.put("productId", productId+"");
		transactionMap.put("batchNumber", batchNumber+"");
		transactionMap.put("quantity", quantity+"");
		transactionMap.put("sellingPrice", sellingPrice+"");
		transactionMap.put("transactionId", transactionId+"");
		transactionMap.put("transactionDate", transactionDate==null?"":transactionDate.toString());
		return transactionMap.toString();
	}
	
}
