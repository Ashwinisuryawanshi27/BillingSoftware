package com.BillingSoftware.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingSoftware.Entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}



