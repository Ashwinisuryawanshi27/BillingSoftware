package com.BillingSoftware.customerController;

import java.io.File;
import java.io.IOException;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BillingSoftware.customerEntity.CustomerEntity;
import com.BillingSoftware.customerService.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final String UPLOAD_DIR = "uploads/"; // Define the upload directory

    @Autowired
    private CustomerService customerService;

//    @GetMapping
//    public String getAllCustomers(Model model) {
//        model.addAttribute("customers", customerService.getAllCustomers());
//        model.addAttribute("newCustomer", new CustomerEntity());
//        return "customer-list";
//    }
    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("newCustomer", new CustomerEntity());
        return "add-customer"; // Display the add/update customer form page
    }


    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute CustomerEntity customer,
                               @RequestParam("imageFile") MultipartFile file,
                               Model model) {
        try {
            if (file != null && !file.isEmpty()) {
                // Specify the path where you want to store uploaded files (absolute or relative)
            	String uploadDir = System.getProperty("user.dir") + "/uploads"; // Absolute path example

                // Ensure the uploads directory exists
                File uploadDirectory = new File(uploadDir);
                if (!uploadDirectory.exists()) {
                    uploadDirectory.mkdirs(); // Create the directory if it doesn't exist
                }

                // Generate a unique file name
                String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                // Save the file to the uploads directory
                File destinationFile = new File(uploadDirectory, uniqueFileName);
                file.transferTo(destinationFile);

                // Set the file path for the customer entity (to store in the database)
                customer.setImagePath("uploads/" + uniqueFileName);
            }

            // Save the customer entity to the database
            customerService.saveCustomer(customer);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "File upload failed: " + e.getMessage());
            return "customer"; // Return with error message if something goes wrong
        }

        return "redirect:/customers"; // Redirect to the customer list page
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("newCustomer", customerService.getCustomerById(id).orElse(new CustomerEntity()));
        //model.addAttribute("customers", customerService.getAllCustomers());
        return "add-customer";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
    @GetMapping
    public String listCustomers(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 5; // Set the number of customers per page
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<CustomerEntity> customerPage = customerService.getPaginatedCustomers(pageable);

        model.addAttribute("customers", customerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", customerPage.getTotalPages());
        return "customer-list";
    }
}
