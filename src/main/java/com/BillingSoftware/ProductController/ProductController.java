package com.BillingSoftware.ProductController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BillingSoftware.ProductEntity.Product;
import com.BillingSoftware.ProductRepository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
//	 @GetMapping("/add1"
//	 		+ ""
//	 		+ "")
//	    public String showSignUpForm(Product product) {
////	        return "add_product";
//	        return "redirect:/add";
//	    }
	
	@GetMapping("/")
	public String showDashboard() {
        return "dashboard"; // Thymeleaf template for the dashboard
    }
	 @GetMapping("/products")
	 public String showProductOptions() {
	        return "products"; // A page showing "Add Product" and "All Products" options
	    }
	@GetMapping("/add_product")
//	public String addProduct(@RequestBody Product product,BindingResult result,Model model) {
//        productRepository.save(product);
	public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add_product";
    }
	
	@PostMapping("/add_product")
	 public String addProduct(Product product, Model model) {
        productRepository.save(product);
        model.addAttribute("message", "Product added successfully!");
        return "redirect:/products"; // Redirect to the product options page
    }
	
	@GetMapping("/all_products")
//	public List<Product> getAllProducts(){
//		return productRepository.findAll();
//		
	public String showAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "all_products";
    }
	@GetMapping("/update/{id}")
	public String updateProductForm(@PathVariable Long id, Model model) {
	    Product product = productRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
	    model.addAttribute("product", product);
	    return "update-product";
	}
//	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
//        Optional<Product> product = productRepository.findById(id);
//        if (product.isPresent()) {
//            model.addAttribute("product", product.get());
//            return "update-product";
//        } else {
//            return "redirect:/all"; // Redirect to "All Products" if the product ID is invalid
//        }
//    }

	@PostMapping("/update/{id}")
	 public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        product.setId(id); // Ensure the product ID remains unchanged
        productRepository.save(product);
        return "redirect:/all"; // Redirect back to "All Products"
    }
	
	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/all"; // Redirect back to "All Products"
    }
	
	
	
}
