package com.BillingSoftware.ProductController;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.BillingSoftware.ProductEntity.Product;
import com.BillingSoftware.ProductRepository.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    @GetMapping("/")
    public String showDashboard() {
        return "dashboard"; // Dashboard page
    }

    @GetMapping("/products")
    public String showProductOptions() {
        return "products"; // Page with "Add Product" and "All Products" options
    }

    @GetMapping("/add_product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping("/add_product")
    public String addProduct(@ModelAttribute Product product, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, imageFile.getBytes());
            product.setImagePath("/uploads/" + fileName);
        }
        productRepository.save(product);
        return "redirect:/all_products";
    }

    @GetMapping("/all_products")
    public String showAllProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/update/{id}")
    public String updateProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "update-product";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product,
                                @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));

        // Handle image upload if a new image is provided
        if (!imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath); // Ensure directory exists
            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, imageFile.getBytes());
            product.setImagePath("/uploads/" + fileName);
        } else {
            product.setImagePath(existingProduct.getImagePath());
        }

        // Set the product ID to ensure it's updated correctly
        product.setId(id);
        productRepository.save(product);

        // Redirect to the add product page after update
        return "redirect:/all_products";
    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/all_products";
    }
}
