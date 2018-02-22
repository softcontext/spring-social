package com.example.demo.facebook.controller;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FacebookController {
	
	@Inject
	private Facebook facebook;
	@Inject
	private ConnectionRepository connectionRepository;

	@GetMapping
	public String helloFacebook(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			// org.springframework.social.connect.web.ConnectController 클래스가 다음 URL을 처리한다.
			// ConnectController 클래스의 connectView, connectedView 메소드를 참고하자.
			return "redirect:/connect/facebook";
		}

		PagedList<Post> feed = facebook.feedOperations().getFeed();
		model.addAttribute("feed", feed);
		model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
		
		return "helloFacebook";
	}

}
