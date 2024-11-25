package com.BillingSoftware.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BillingSoftware.Entity.Supplier;
import com.BillingSoftware.Service.SupplierService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private static final String UPLOAD_DIR = "uploads/"; // Define the upload directory

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/add")
    public String addsupplierForm(Model model) {
        model.addAttribute("newsupplier", new Supplier());
        return "supplier-form"; // Return the form to add a new supplier
    }

    @PostMapping("/save")
    public String savesupplier(@ModelAttribute Supplier supplier,
                               @RequestParam("imageFile") MultipartFile file,
                               Model model) {
        try {
            if (file != null && !file.isEmpty()) {
                String uploadDir = System.getProperty("user.dir") + "/uploads";
                File uploadDirectory = new File(uploadDir);
                if (!uploadDirectory.exists()) {
                    uploadDirectory.mkdirs(); // Create directory if it doesn't exist
                }

                // Generate a unique filename
                String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File destinationFile = new File(uploadDirectory, uniqueFileName);
                file.transferTo(destinationFile);

                // Set the image path in the Supplier entity
                supplier.setImagePath("uploads/" + uniqueFileName);
            }

            // Save the Supplier entity
            supplierService.savesupplier(supplier);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "File upload failed: " + e.getMessage());
            return "supplier-form"; // Return to the form with an error message
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            return "supplier-form"; // Return to the form with an unexpected error message
        }

        return "redirect:/suppliers"; // Redirect to the supplier list page
    }

    @GetMapping("/edit/{id}")
    public String editsupplier(@PathVariable Long id, Model model) {
        model.addAttribute("newsupplier", supplierService.getsupplierById(id).orElse(new Supplier()));
        return "supplier-form"; // Return the form for editing the supplier
    }

    @PostMapping("/delete/{id}")
    public String deletesupplier(@PathVariable Long id) {
        supplierService.deletesupplier(id);
        return "redirect:/suppliers"; // Redirect to the supplier list page after deletion
    }

    @GetMapping
    public String listsupplier(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 5; // Set the number of suppliers per page
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<Supplier> supplierPage = supplierService.getPaginatedsupplier(pageable);

        model.addAttribute("suppliers", supplierPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", supplierPage.getTotalPages());
        return "supplier-list"; // Return the supplier list view
    }
}
