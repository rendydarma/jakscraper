package com.jakmall.jakscraper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.Supplier;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long>{

}
