package com.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query("select t from transactions t where t.id=?1")
	public List<Transaction> findTransctionById(int id);
}
