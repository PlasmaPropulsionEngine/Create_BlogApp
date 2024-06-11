package com.blogmaker.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blogmaker.dao.UserRepository;
import com.blogmaker.model.Users;
import com.blogmaker.services.UserService;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
			Users getuserbyemail = service.getUserByEmail(username);
			
			if(getuserbyemail==null)
			{
				throw new UsernameNotFoundException("user not found");
			}
		
			CustomUserDetails customUserDetails=new CustomUserDetails(getuserbyemail);
			return customUserDetails;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
