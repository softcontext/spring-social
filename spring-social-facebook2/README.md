# SPRING SOCIAL FACEBOOK TUTORIAL

https://www.ximedes.com/spring-social-facebook-tutorial/

## 1. Call localhost:8080/createFacebookAuthorization
페이스북 접근 URL을 획득하자.
브라우저 >> http://localhost:8080/createFacebookAuthorization
결과예시 >> https://www.facebook.com/v2.5/dialog/oauth?client_id=869697233201780&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ffacebook&scope=public_profile%2Cemail%2Cuser_birthday

## 2. Visit the authorization url
페이스북 접근 URL로 브라우저를 사용하여 접속한다.
에러발생 >> 
```
URL을 읽어들일 수 없음: 앱 도메인에 포함되어 있지 않은 URL입니다. 
이 URL을 읽어들이려면 앱 설정에서 앱 도메인 필드에 앱의 모든 도메인과 서브 도메인을 추가하세요.
```

## 3. Give the facebook app permission
앞에서 발생한 에러를 해결하자.
페이스북개발자 사이트 접속 >> https://developers.facebook.com/apps/ >> 프로젝트 선택 >> 
유효한 OAuth 리다이렉션 URI 등록 >> http://localhost:8080/facebook

## 4. Redirect to our application to obtain the access token
리다이렉션 설정을 한 후 페이스북 접근 URL로 다시 접속한다. 이 때, 페이스북에서 우리가 요청한 URL로 리다이렉트 되면서 액세스 토큰을 획득한다.

## 5. Call localhost:8080/getName to request the name of the Facebook user
획득한 액세스 토큰으로 페이스북에 요청하여 사용자 정보를 획득해 보자.

## 6. 추가적인 정보를 조회하기 위해서는 별도의 승인과정을 거쳐야 한다.
다음은 그것과 관련한 안내 메시지이다.
```
로그인 검수를 위해 제출
아래 권한 중 Facebook의 승인을 받지 못한 권한이 있습니다.
지금 검수를 위해 제출하거나 자세히 알아보세요.
```

----------------------------------------------------------------------

## 코드분석

### 1. 사용자 >> 우리 사이트 메인 페이지 >> 로그인 페이지 >> 페이스북 로그인 선택

페이스북 사이트 리다이렉트 URL 획득 방법

```
    public String createFacebookAuthorizationURL(){
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/facebook");
        params.setScope("email,public_profile,user_friends");
        
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        
        return oauthOperations.buildAuthorizeUrl(params);
    }
```

### 2. 코드적으로 리다이렉트 >> 페이스북 페이지 >> 페이스북 로그인 >> 페이스북에서 우리 사이트에 정보제공 동의 선택

우리 사이트로 리다이렉트, 리다이렉트 URL = http://localhost:8080/facebook

암묵적으로 페이스북 프로그램이 리다이렉트 >> /facebook URL 핸들러 기동 

```
	@GetMapping("/facebook")
	public void createFacebookAccessToken(@RequestParam("code") String code) {
		facebookService.createFacebookAccessToken(code);
	}
```

### 3. >> 액세스 토큰 획득 

액세스 토큰 획득 방법

```
	public void createFacebookAccessToken(String code) {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
	    AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null);
	    accessToken = accessGrant.getAccessToken();		
	}
```

### 4. >> 액세스 토큰으로 페이스북에 사용자 데이터를 요청하여 사용자 데이터를 획득 

```
	public String getName() {
	    Facebook facebook = new FacebookTemplate(accessToken);
	    String[] fields = {"id", "name"};
	    return facebook.fetchObject("me", String.class, fields);
	}
```

### 5. >> 이메일 정보로 로그인 처리

----------------------------------------------------------------------

org.springframework.social.connect.web.ConnectController 를 스프링 소셜은 사용한다.

