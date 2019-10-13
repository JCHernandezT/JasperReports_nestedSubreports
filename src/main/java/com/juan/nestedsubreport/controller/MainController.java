package com.juan.nestedsubreport.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@GetMapping("/")
	public String getMain(Model model) {
		logger.debug("getMain");
		return "index";
	}

}
