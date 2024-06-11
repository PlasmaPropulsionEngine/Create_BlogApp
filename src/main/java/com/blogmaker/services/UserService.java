package com.blogmaker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blogmaker.dao.UserRepository;
import com.blogmaker.model.Users;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//Get all registered users
	public List<Users> getAlluser()
	{
		
		List<Users> userList = (List<Users>) this.userRepository.findAll();
		
		return userList;	
	}
	
	
//Get user by id
public Optional<Users> getUserbyId(int id)
{
	Optional<Users> user=null;
	
	try {
		 user = this.userRepository.findById(id);
		}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return user;
}
	
//Save user
public Users saveUser(Users user)
{
	
		Users save = this.userRepository.save(user);	
		return save;	
}
	

	
//Delete user
public void deleteUser(int id)
{
	this.userRepository.deleteById(id);
}
	
	
//get User by email
public Users getUserByEmail(String email)
{
	
	System.out.println(email);
	
	Users userByEmail = this.userRepository.getuserbyemail(email);
	
	System.out.println(userByEmail);
	
	return userByEmail;
}
	
	
//get All data 


public void getAll()
{
	
	
}










}
