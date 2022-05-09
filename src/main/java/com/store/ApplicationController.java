package com.store;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ApplicationController {

	@Autowired
	ProductRepository productRepo;
	@Autowired
	TransactionRepository transactionRepo;
	@Autowired
	TransactionService service;
	@Autowired
	BillRepository billRepo;
	
	@RequestMapping(value={"/home","/"})
	public String showAppHome(Model model) {
		List<Product> allProducts = productRepo.findAll();
		Date expiryPlus120days = Date.from(Instant.now().minus(-120,ChronoUnit.DAYS));
		List<Product> expiringProducts = allProducts
				.stream()
				.filter(
					p -> p.getExpiryDate().compareTo(expiryPlus120days)<0
				)
				.collect(Collectors.toList());
		model.addAttribute("userMessage1", "You have total of "+ allProducts.size()+" items in your inventory");
		model.addAttribute("userMessage2", "Count of expiring products in next 4 months : "+expiringProducts.size());
		model.addAttribute("userMessage3", "List of products expiring in next 4 months : "+expiringProducts);
		model.addAttribute("userMessage4", "");
		return "home";
	}
	@GetMapping("/inventory")
	public String showAllItems(Model model) {
		model.addAttribute("products", productRepo.findAll());
		return "inventory";
	}

	@GetMapping("/purchase")
	public String purchaseItem(Model model) {
		model.addAttribute("product", new Product());
		return "purchase";
	}
	@GetMapping("/sellItem")
	public String sellItem(Model model) {
		model.addAttribute("transaction", new Transaction());
		return "sell";
	}
	@GetMapping("/transactions")
	public String showAllTransactions(Model model) {
		model.addAttribute("transactions", transactionRepo.findAll());
		return "transactions";
	}
	@PostMapping("/processTransaction")
	public ResponseEntity<String> processTransaction(@RequestBody String data, Model model){
		int id = service.parseTransactions(data);
		return new ResponseEntity<String>(id+"", HttpStatus.OK);
	}
	@GetMapping("/getProductBatchNumber/{name}")
	public ResponseEntity<String> getProductBatchNumber(@PathVariable String name){
		int batchNumber = productRepo.findAll()
				.stream()
				.filter(p -> p.getName().toLowerCase().equals(name.toLowerCase()))
				.findFirst()
				.get().getBatchNo();
		return new ResponseEntity<String>(batchNumber+"", HttpStatus.OK);
	}
	 
	@PostMapping("/processPurchasingForm")
	public String processPurchasingForm(Product product, Model model) {
		model.addAttribute("products", productRepo.findAll());
		productRepo.save(product);
		return "inventory";
	}
	
	@GetMapping("/bills")
	public String showBills(Model model) {
		model.addAttribute("bills", billRepo.findAll());
		return "bills";
	}

	@GetMapping("bills/{id}")
	public String getBillsById(@PathVariable int id, Model model){
		Bill bill = billRepo.findAll().stream().filter(x -> x.getBillId()==id&&x!=null).collect(Collectors.toList()).get(0);
		model.addAttribute("bill", bill);
		return "printBill";
	}
}
