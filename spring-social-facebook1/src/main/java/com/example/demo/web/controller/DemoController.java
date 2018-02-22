package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.service.FacebookService;

@RestController
@RequestMapping("/")
public class DemoController {

	@Autowired
    FacebookService facebookService;

    @GetMapping("/createFacebookAuthorization")
    public String createFacebookAuthorization(){
        return facebookService.createFacebookAuthorizationURL();
    }
    
    /*
     * https://www.facebook.com/v2.5/dialog/oauth
     * ?
     * client_id=1769408656636461 // 컨슈머(spring.social.facebook.appId)
     * &
     * response_type=code
     * &
     * redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ffacebook // RedirectUri("http://localhost:8080/facebook")
     * &
     * scope=public_profile%2Cemail%2Cuser_birthday // Scope("public_profile,email,user_birthday")
     */
    
//	로그인 검수를 위해 제출
//	아래 권한 중 Facebook의 승인을 받지 못한 권한이 있습니다.
//	지금 검수를 위해 제출하거나 자세히 알아보세요.
}
