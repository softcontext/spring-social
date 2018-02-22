package com.example.demo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.web.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
	
	// Query Method
	public MyUser findByEmail(String email);
	public MyUser findByEmailAndPassword(String email, String password);
	
}
