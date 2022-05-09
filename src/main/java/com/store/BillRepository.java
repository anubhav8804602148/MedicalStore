package com.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillRepository extends JpaRepository<Bill, Integer> {
	@Query("select b from bill b order by b.id desc")
	public List<Bill> findAll();


	@Query("select b from bill b where b.billId=?1 order by b.id desc")
	public List<Bill> findAllById(int id);
}
