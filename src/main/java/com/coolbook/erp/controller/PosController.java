package com.coolbook.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PosController {

	@RequestMapping("/pos")
	public String pos() {
		return "pos";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping("/dashBoard")
	public String dashBoard() {
		return "dashBoard";
	}

}
