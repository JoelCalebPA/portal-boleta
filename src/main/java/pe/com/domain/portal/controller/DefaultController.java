package pe.com.domain.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/greeting")
	public String getGreeting() {
		return "greeting";
	}
	
	@GetMapping("/error")
	public String getError() {
		return "error";
	}
	
	@GetMapping("/documents")
	public String getDocuments() {
		return "template/documents";
	}
	
}
