package com.BillingSoftware.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="supplier")
public class Supplier {
	 	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String email;
	    private String phone;
	    private String imagePath;
	   
	   
	public String getImagePath() {
	return imagePath;
	}
	public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
	}
	public Long getId() {
	return id;
	}
	public void setId(Long id) {
	this.id = id;
	}
	public String getName() {
	return name;
	}
	public void setName(String name) {
	this.name = name;
	}
	public String getEmail() {
	return email;
	}
	public void setEmail(String email) {
	this.email = email;
	}
	public String getPhone() {
	return phone;
	}
	public void setPhone(String phone) {
	this.phone = phone;
	}
	
	@Override
	public String toString() {
	return "supplier [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", imagePath=" + imagePath + "]";
	}

	   
	}