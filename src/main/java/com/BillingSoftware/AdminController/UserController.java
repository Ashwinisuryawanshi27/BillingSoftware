package com.BillingSoftware.AdminController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.*;

@Controller
public class UserController {	

	@GetMapping("/home")
	public String Home() {
		return "index";
	}

}
