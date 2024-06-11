package com.blogmaker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogmaker.model.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {

	
		@Query("select u from Users u where u.email=:e")
		public Users getuserbyemail(@Param("e") String email);
	
	
		
		
		
		
		
}
