package com.example.demo.web.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

	@Value("${spring.social.facebook.appId}")
    String facebookAppId;
	
    @Value("${spring.social.facebook.appSecret}")
    String facebookSecret;
    
    public String createFacebookAuthorizationURL(){
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/facebook");
        params.setScope("public_profile,email,user_birthday");
        
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        
        return oauthOperations.buildAuthorizeUrl(params);
    }
}
