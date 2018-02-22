package com.example.demo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.web.form.dto.RegistrationForm;
import com.example.demo.web.model.MyUser;
import com.example.demo.web.repository.UserRepository;

@Transactional
@Service
public class RegistrationService {

	@Autowired
	private UserRepository userRepository;

	public MyUser registerNewUserAccount(RegistrationForm userAccountData) {
		if (emailExist(userAccountData.getEmail())) {
			throw new RuntimeException("The email address: " + userAccountData.getEmail() + " is already in use.");
		}

		MyUser user = new MyUser();
		user.setEmail(userAccountData.getEmail());
		user.setPassword(userAccountData.getPassword());

		return userRepository.save(user);
	}

	private boolean emailExist(String email) {
		MyUser user = userRepository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

}
