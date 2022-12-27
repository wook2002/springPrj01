package com.wook.prj01.web.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("homController")
@RequestMapping("/")
public class HomeController {

	public void index() {
		System.out.println("F");
	}
	
}
