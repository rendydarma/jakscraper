package com.jakmall.jakscraper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.User;
import com.jakmall.jakscraper.repo.UserRepo;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepo userRepo;
	
	public User getByEmail(String email) {
		final User user = userRepo.getByEmail(email);
		return user;
	}

}
