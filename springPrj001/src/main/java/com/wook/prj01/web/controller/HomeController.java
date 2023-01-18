package com.wook.prj01.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*")
@RestController("homController")
public class HomeController {

	@RequestMapping("/index")
	public void index() {
		System.out.println("Home");
	}
	
	@RequestMapping("/help")
	public void help() {
		System.out.println("help");
	}
	
}
