package com.example.demo.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.web.service.FacebookService;
import com.google.gson.Gson;

// 페이스북 프로바이더가 제공하는 정보로 신규회원등록/로그인 처리를 한다.
@Controller
@RequestMapping("/connect")
public class FacebookController extends ConnectController {
	
	private String TARGET_URL;
	
	@Autowired
	private FacebookService facebookService;
	
//	@Resource(name = "inMemoryConnectionRepository")
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Inject
	public FacebookController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	/*
	 * 프런트엔드에서 소셜로그인을 위해 최초로 요청을 받게 되는 메서드이다.
	 * 요청을 보낸 페이지의 URL을 받아 TARGET_URL 변수에 보관한다.
	 * 페이스북 페이지에 가서 인증을 하고 난 후 TARGET_URL 값을 사용하여 원래 페이지로 리다이렉트한다. 
	 */
	@Override
	@RequestMapping(value="/{providerId}", method=RequestMethod.POST)
	public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
		System.out.println("FacebookController # connect() called.");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request.getNativeRequest();
		TARGET_URL = httpServletRequest.getHeader("REFERER");
		System.out.println("TARGET_URL = " + TARGET_URL); // TARGET_URL = http://localhost:8080/login
		
		return super.connect(providerId, request);
	}

	@Override
	@RequestMapping(value="/{providerId}", method=RequestMethod.GET, params="code")
	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
		System.out.println("FacebookController # oauth2Callback() called.");
		
		RedirectView redirectView = super.oauth2Callback(providerId, request);
		
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		Facebook facebook = connection.getApi();
		
		String[] fields = { 
				"id", "age_range", "email", 
				"first_name", "gender", "last_name", 
				"link", "locale", "name",
				"third_party_id", "verified" 
		};
		User userProfile = facebook.fetchObject("me", User.class, fields);
		
		System.out.println(new Gson().toJson(userProfile));
//		{
//		  "id": "1842567725817755",
//		  "ageRange": "AGE_21_PLUS",
//		  "email": "lightel@naver.com",
//		  "firstName": "Seokwon",
//		  "gender": "male",
//		  "installed": false,
//		  "isIdentityVerified": false,
//		  "lastName": "Song",
//		  "link": "https://www.facebook.com/app_scoped_user_id/1842567725817755/",
//		  "locale": "ko_KR",
//		  "name": "Seokwon Song",
//		  "testGroup": 0,
//		  "thirdPartyId": "8CGAip3oR29tUITwVFVirekcwDg",
//		  "verified": true,
//		  "viewerCanSendGift": false,
//		  "extraData": {
//		    
//		  }
//		}
		
		// 신규 회원이면 등록 후 로그인 처리, 기존 회원이면 로그인 처리만 진행한다.
		facebookService.registerAndLogin(userProfile, "facebook");
		
		// 로그인 처리 후 사용자에게 보여주고 싶은 리다이렉트 URL을 설정할 수 있다.
		redirectView.setUrl(TARGET_URL);
		
		return redirectView;
	}

}
