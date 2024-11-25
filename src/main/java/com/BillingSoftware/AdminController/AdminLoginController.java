package com.BillingSoftware.AdminController;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import com.BillingSoftware.AdminEnity.Login;

@Controller
public class AdminLoginController {

    @Autowired
    private SessionFactory sf;

    @GetMapping("/login")
    public String showLoginPage() {
        return "page_login";  // Show login page
    }

    @GetMapping("/intel_introduction")
    public String showIntelIntroduction() {
        return "intel_introduction"; // Ensure this is the correct view name (intel_introduction.html)
    }
    
    @PostMapping("/loginp")
    public String loginp(Login login, Model model, RedirectAttributes redirectAttributes) {
        Session session = sf.openSession();
        Login dlogin = session.createQuery("from Login where username = :username", Login.class)
                              .setParameter("username", login.getUsername())
                              .uniqueResult();

        String page = "page_login";
        String msg;

        if (dlogin != null && dlogin.getPassword().equals(login.getPassword())) {
            msg = "Welcome";
            page = "redirect:intel_introduction";  // Redirect to another page upon successful login
        } else {
            msg = "Invalid username or password";
            redirectAttributes.addFlashAttribute("msg", msg);  // Set flash message for error
            page = "redirect:/login";  // Redirect back to login page with error message
        }

        session.close();
        return page;
    }

    @GetMapping("/CreateAccount")
    public String showRegistrationPage() {
        return "page_register";  // Show registration page
    }

    @PostMapping("/createaccountdata")
    public String createAccountData(Login login, Model model) {
        Session session = sf.openSession();
        String page = "page_login";
        String msg;

        if (session.createQuery("from Login where username = :username")
                .setParameter("username", login.getUsername())
                .uniqueResult() != null) {
            msg = "Username (email) is already registered.";
        } else {
            Transaction transaction = session.beginTransaction();
            session.save(login);
            transaction.commit();
            msg = "Account created successfully!";
        }

        model.addAttribute("msg", msg);
        session.close();
        return page;
    }
}
