package com.jakmall.jakscraper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	User getByEmail(String email);
}
