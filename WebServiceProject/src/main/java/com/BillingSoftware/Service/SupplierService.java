package com.BillingSoftware.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.BillingSoftware.Entity.Supplier;
import com.BillingSoftware.Repository.SupplierRepository;

@Service
public class SupplierService {

	 @Autowired

	    private SupplierRepository supplierRepository;



	    public List<Supplier> getAllsupplier() {

	        return supplierRepository.findAll();

	    }



	    public Optional<Supplier> getsupplierById(Long id) {

	        return supplierRepository.findById(id);

	    }



	    public Supplier savesupplier(Supplier supplier) {

	        return supplierRepository.save(supplier);

	    }



	    public void deletesupplier(Long id) {

	    	supplierRepository.deleteById(id);

	    }





	public Page<Supplier> getPaginatedsupplier(Pageable pageable) {

	    return supplierRepository.findAll(pageable);

	}

	}

