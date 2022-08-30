package com.godoro.ecommerce.presentation.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeClient {

	@GetMapping(path = {"/", "/home"})
	public String getHome() {
		
		return "HomePage";
	}
}
