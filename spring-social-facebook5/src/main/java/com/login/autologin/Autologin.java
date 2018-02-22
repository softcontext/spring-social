package com.login.autologin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.login.model.UserBean;

@Service
public class Autologin {

//	사용자가 사회 공급자에 의해 또는 사용자를 등록함으로써 로그인되면 인증을 설정하여 보안 컨텍스트를 업데이트해야합니다.
//	Spring API에 따르면 - "AuthenticationManager.authenticate 메소드에 의해 요청이 처리되면 
//	인증은 인증 요청 또는 인증 된 주체에 대한 토큰을 나타냅니다.
//	요청이 인증되면, 인증은 일반적으로 사용되는 인증 메커니즘에 의해 SecurityContextHolder에 의해 관리되는 스레드 로컬 SecurityContext에 저장됩니다.
//	명시적 인증은 Spring Security의 인증 메커니즘 중 하나를 사용하지 않고 인증 인스턴스를 생성하고 코드를 사용하여 수행 할 수 있습니다.
//	정확히 우리는 우리의 방법으로 할 것입니다.
	
	public void setSecuritycontext(UserBean userForm) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userForm.getProvider().toUpperCase()));
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userForm.getEmail(), userForm.getPassword(), grantedAuthorities);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
}