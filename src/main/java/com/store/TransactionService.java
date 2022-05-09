package com.store;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionRepo;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	BillRepository billRepo;
	public int parseTransactions(String transactionData) {
		System.out.println("Processing transaction : "+transactionData);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Bill bill = mapper.readValue(transactionData, Bill.class);
			boolean validated = validate(bill.allTransactions);
			System.out.println("Validated : "+validated);
			if(!validated) return -1;
			for(Transaction transaction : bill.allTransactions) {
				if(transaction==null) continue;
				Product product = productRepo.findProductByName(transaction.getProductName())
						.stream()
						.filter(x -> x.getQuantity()>=transaction.getQuantity())
						.sorted((x,y)-> x.getExpiryDate().compareTo(y.getExpiryDate()))
						.findFirst()
						.get();
				if(product==null) return -1;
				transaction.setCostPrice(productRepo.findProductByName(transaction.getProductName()).get(0).getCostPrice());
				transaction.setTransactionDate(bill.getTransactionDate());
				int q1 = transaction.getQuantity();
				int q2 = product.getQuantity();
				product.setQuantity(q2-q1);
				productRepo.save(product);
				transactionRepo.save(transaction);
				
			}
			Bill printedBill = billRepo.save(bill);
			System.out.println("Processed transaction");
			return printedBill.getBillId();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean validate(Transaction[] allTransactions) {
		boolean validated=true;
		for(Transaction transaction : allTransactions) {
			validated &= validate(transaction);
		}
		return validated;
	}
	public boolean validate(Transaction transaction) {
		if(transaction==null) return true;
		Product product = productRepo.findProductByName(transaction.getProductName())
				.stream()
				.filter(x -> x.getQuantity()>=transaction.getQuantity())
				.sorted((x,y)-> x.getExpiryDate().compareTo(y.getExpiryDate()))
				.findFirst()
				.get();
		if(product==null) {
			System.out.println("No product found for transaction : "+transaction.toString());
			return false;
		}
		int q1 = transaction.getQuantity();
		int b1 = transaction.getBatchNumber();
		int q2 = product.getQuantity();
		int b2 = product.getBatchNo();
		if(b2!=b1) {
			System.out.println("batch number not matching with product : "+b2 +" "+b1);
			return false;
		}
		if(q1>q2){
			System.out.println("not enough item in inventory : "+q1+" "+q2);
			return false;
		}
		return true;
	}
}
