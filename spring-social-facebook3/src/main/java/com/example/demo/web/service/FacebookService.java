package com.example.demo.web.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.web.model.MyUser;
import com.example.demo.web.repository.UserRepository;

@Transactional
@Service
public class FacebookService {
	
	@Autowired
	private UserRepository userRepository;

	public void registerAndLogin(User userProfile, String snsProvider) {
		MyUser user = userRepository.findByEmail(userProfile.getEmail());
		
		// 신규 회원이면 로그인 처리 전 먼저 등록한다.
		if (user == null) { 
			System.out.println("Facebook이 제공한 정보로 신규회원 등록");
			
			user = new MyUser();
			user.setEmail(userProfile.getEmail());
			user.setPassword(null);
			user.setSnsProvider(snsProvider);
			
			user = userRepository.save(user);
		}
		
		// 로그인 처리
		proceedLogin(user, snsProvider);
		System.out.println("Facebook 인증으로 요청한 사용자의 로그인을 처리");
	}
	
	public void proceedLogin(MyUser user, String snsProvider) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(false);

		session.setAttribute("loginUser", user);
	}
	
}
