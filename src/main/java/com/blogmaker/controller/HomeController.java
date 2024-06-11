package com.blogmaker.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blogmaker.dao.BlogRepository;
import com.blogmaker.model.Blog;
import com.blogmaker.model.Users;
import com.blogmaker.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController 
{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired	
	private BlogRepository blogRepository;
	
@RequestMapping("/")	
public String HomePage(Model model)
{
     List<Blog> publicBlogs = this.blogRepository.getPublicBlogs("public");	
	System.out.println(publicBlogs);
	model.addAttribute("blog", publicBlogs);
	return "home"; 	 
} 


@RequestMapping("/login")
public String LoginPage() 
{
 
return "login";

}

 
@RequestMapping("/register")
public String DisplayRegisterPage(Model model)
{
	//send the blank User fields to form  	
	model.addAttribute("user",new Users());
	return "register";
	 
}
	
@RequestMapping(value="/register-process",method = RequestMethod.POST)
public String RegisterUser(@Valid @ModelAttribute("user") Users user,BindingResult result,@RequestParam(value="agree",defaultValue = "false") boolean agr,Model model ,HttpSession session) throws Exception
{

	try {
	if(!agr)  
	{
		System.out.println("you have not tick conditions");
		//throw new Exception("you have not tick conditions");
		session.setAttribute("msg",new com.blogmaker.helper.Messages("you have not tick conditions","show alert-danger"));
		return "register";
	}
	
	if(result.hasErrors()) 
	{ 
		System.out.println("error "+result.toString());
		model.addAttribute("user",user);
		return "register";
		
	}
		
		user.setRole("ROLE_USER");
	
		user.setEnabled(true);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Users saveUser = this.userService.saveUser(user);
		
		Users userByEmail = this.userService.getUserByEmail(user.getEmail());
		
		
		System.out.println(saveUser);
		System.out.println(userByEmail.getId());
		 
		//if all good then send the blank User fields to form remember new User() means blank values
		model.addAttribute("user",new Users());
		
		session.setAttribute("msg",new com.blogmaker.helper.Messages("Your successfully registered go to Login","show alert-success"));
		return "register";
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
		model.addAttribute("user",user);
		session.setAttribute("msg",new com.blogmaker.helper.Messages("Email alrady registered Please Login somthing went wrong "+e.getMessage(),"show alert-danger"));
		return "register";
		
		
	}
	
}




	
	
}
