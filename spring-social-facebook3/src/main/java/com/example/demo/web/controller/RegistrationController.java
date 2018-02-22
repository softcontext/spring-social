package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.web.form.dto.RegistrationForm;
import com.example.demo.web.model.MyUser;
import com.example.demo.web.service.RegistrationService;

// 신규 회원을 등록하는 처리를 한다.
@Controller
@RequestMapping("/user")
public class RegistrationController {

	@Autowired
	private RegistrationService userService;

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "user/registrationForm";
	}

	@PostMapping("/register")
	public String registerUserAccount(RegistrationForm userAccountData) {
		System.out.println(userAccountData);
		
		createUserAccount(userAccountData);
		return "redirect:/login";
	}

	private MyUser createUserAccount(RegistrationForm userAccountData) {
		MyUser registered = null;
		try {
			registered = userService.registerNewUserAccount(userAccountData);
			System.out.println(registered);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return registered;
	}

}
