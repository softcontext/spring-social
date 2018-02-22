# Accessing Facebook Data

https://spring.io/guides/gs/accessing-facebook/

## 처리순서

루트 접근 시 Facebook 객체가 존재하지 않는 경우 다음 주소로 리다이렉트 된다.

**GET**

```
http://localhost:8080/connect/facebook >> connect/facebookConnect.jsp
```

**POST**

```
http://localhost:8080/connect/facebook >> FaceBook
```

```
1. 페이스북 로그인 상태인 경우
페이스북이 발급한 토큰을 가지고 바로 http://localhost:8080/connect/facebook#_=_ 주소로 리다이렉트 된다.

2. 페이스북 로그인 상태가 아닌 경우
페이스북이 제공하는 로그인 인증과정을 거친 후, #1 번 과정이 진행된다.
```

**GET**

```
http://localhost:8080/connect/facebook#_=_ >> connect/facebookConnected.jsp
```
