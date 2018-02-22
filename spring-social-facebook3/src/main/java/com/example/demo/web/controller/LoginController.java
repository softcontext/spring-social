package com.example.demo.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.web.model.MyUser;
import com.example.demo.web.service.LoginService;

// 아이디/패스워드 정보를 받아 로그인 처리를 한다.
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@GetMapping
	public String getLoginView() {
		return "user/login";
	}
	
	@PostMapping
	public String doLogin(MyUser account, Model model, HttpSession session) {
		if (loginService.authorize(account)) {
			loginService.proceedLogin(account, session);
			return "redirect:";
		} else {
			model.addAttribute("error", "아이디/패스워드가 일치하지 않습니다.");
			return "user/login";
		}
	}

	@GetMapping(params="logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:";
	}
}
