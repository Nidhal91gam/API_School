package com.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping(path = "test")
	public String testApp() {
		return " this Test is well done";
	}

}
