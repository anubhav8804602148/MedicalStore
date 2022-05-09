package com.store;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;

import javax.persistence.*;

@Entity(name="bill")
public class Bill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int billId;
	
	@Column(nullable=false)
	public String customerName;
	
	@Column(nullable=false)
	public Date transactionDate;
	
	@Column(nullable=false)
	public Transaction[] allTransactions;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String toString() {
		HashMap<String, String> billMap = new HashMap<String, String>();
		billMap.put("customerName", customerName);
		billMap.put("billId", billId+"");
		billMap.put("transactionDate", transactionDate.toString());
		billMap.put("allTransactions", Arrays.toString(allTransactions));
		return billMap.toString();
	}

	public Transaction[] getAllTransactions() {
		return allTransactions;
	}

	public void setAllTransactions(Transaction[] allTransactions) {
		this.allTransactions = allTransactions;
	}
}
