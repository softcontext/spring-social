package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.service.FacebookService;

@RestController
@RequestMapping("/")
public class DemoController {

	@Autowired
	FacebookService facebookService;

	// 1. Call localhost:8080/createFacebookAuthorization
	// 페이스북 접근 URL을 획득하자.
	@GetMapping("/createFacebookAuthorization")
	public String createFacebookAuthorization() {
		return facebookService.createFacebookAuthorizationURL();
	}

	// 이 URL은 페이스북에서 리다이렉트 될 때 사용된다.
	@GetMapping("/facebook")
	public void createFacebookAccessToken(@RequestParam("code") String code) {
		facebookService.createFacebookAccessToken(code);
	}

	// 5. Call localhost:8080/getName to request the name of the Facebook user
	// 획득한 액세스 토큰으로 페이스북에 요청하여 사용자 정보를 획득해 보자.
	@GetMapping("/getName")
	public String getNameResponse() {
		return facebookService.getName();
	}

}
