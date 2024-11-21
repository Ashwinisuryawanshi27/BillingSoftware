package com.BillingSoftware.ProductEntity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String supplierName;
	    private String supplierEmail;
	    private LocalDate date;
	    private String brand;
	    private String category;
	    private String name;
	    private String size;
	    private String description;
	    private Integer quantity;
	    private String color;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSupplierName() {
			return supplierName;
		}
		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}
		public String getSupplierEmail() {
			return supplierEmail;
		}
		public void setSupplierEmail(String supplierEmail) {
			this.supplierEmail = supplierEmail;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		@Override
		public String toString() {
			return "Product [id=" + id + ", supplierName=" + supplierName + ", supplierEmail=" + supplierEmail
					+ ", date=" + date + ", brand=" + brand + ", category=" + category + ", name=" + name + ", size="
					+ size + ", description=" + description + ", quantity=" + quantity + ", color=" + color + "]";
		}
		
		public Product() {
			super();
			this.id = id;
			this.supplierName = supplierName;
			this.supplierEmail = supplierEmail;
			this.date = date;
			this.brand = brand;
			this.category = category;
			this.name = name;
			this.size = size;
			this.description = description;
			this.quantity = quantity;
			this.color = color;
		}
	    
	
}
