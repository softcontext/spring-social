package com.example.demo.web.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.web.model.MyUser;
import com.example.demo.web.repository.UserRepository;

@Transactional
@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public boolean authorize(MyUser account) {
		MyUser user = userRepository.findByEmailAndPassword(account.getEmail(), account.getPassword());

		if (user != null) {
			return true;
		}

		return false;
	}

	public void proceedLogin(MyUser account, HttpSession session) {
		MyUser loginUser = new MyUser();
		loginUser.setEmail(account.getEmail());
		loginUser.setPassword(account.getPassword());

		session.setAttribute("loginUser", loginUser);
	}

}
