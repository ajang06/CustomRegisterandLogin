package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	@Query("SELECT u FROM User u where u.email = ?1")
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u where u.username = ?1")
	User findByUsername(String username);
}
