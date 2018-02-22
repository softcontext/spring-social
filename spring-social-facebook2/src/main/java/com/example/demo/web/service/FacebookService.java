package com.example.demo.web.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

	@Value("${spring.social.facebook.appId}")
    String facebookAppId;
	
    @Value("${spring.social.facebook.appSecret}")
    String facebookSecret;
    
    String accessToken;
    
    public String createFacebookAuthorizationURL(){
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/facebook");
        params.setScope("email,public_profile,user_friends");
        
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        
        return oauthOperations.buildAuthorizeUrl(params);
    }

    /*
     * 이 메소드는 사용자가 Facebook에서 응용 프로그램으로 돌아와 스프링 코드의 도움으로 액세스 토큰을 만들 때 호출됩니다.
     * 해당 액세스 토큰을 사용하여 사용자를 대신하여 데이터를 요청할 수 있습니다.
     */
	public void createFacebookAccessToken(String code) {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
	    AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null);
	    accessToken = accessGrant.getAccessToken();		
	}

	public String getName() {
	    Facebook facebook = new FacebookTemplate(accessToken);
	    String[] fields = {"id", "name"};
	    return facebook.fetchObject("me", String.class, fields);
	}
	
}
