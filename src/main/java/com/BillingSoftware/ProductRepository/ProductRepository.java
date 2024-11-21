package com.BillingSoftware.ProductRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingSoftware.ProductController.ProductController;
import com.BillingSoftware.ProductEntity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{


	

}
