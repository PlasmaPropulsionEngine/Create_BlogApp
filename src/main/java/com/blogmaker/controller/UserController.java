package com.blogmaker.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.blogmaker.dao.ApiRepository;
import com.blogmaker.dao.BlogRepository;
import com.blogmaker.dao.UserRepository;
import com.blogmaker.helper.Dcrypt;
import com.blogmaker.helper.Getweather;
import com.blogmaker.model.ApiKey;
import com.blogmaker.model.Blog;
import com.blogmaker.model.Users;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
      
	
@Autowired	
UserRepository userRepository;	
	 
@Autowired	
private ApiRepository apiRepository;

@Autowired
BlogRepository blogRepository; 

@ModelAttribute
public void profileData(Model model,Principal principal)
{	
	System.out.println("user loged "+ principal.getName());
	String name = principal.getName();
	Users getuserbyemail = this.userRepository.getuserbyemail(name);
	List<Blog> blog = getuserbyemail.getBlog();
	
	model.addAttribute("blog", blog);
	
	model.addAttribute("user",getuserbyemail); 
	
}

@GetMapping	
public String HomePage(Model model)
{
     List<Blog> publicBlogs = this.blogRepository.getPublicBlogs("public");	
	System.out.println(publicBlogs);
	model.addAttribute("blog", publicBlogs);
	return "normal/home"; 	 
} 


//index means dashboard
@RequestMapping("/index")	
public String dashBoard(Model model,Principal principal,Users user)
{	
		
	String username = principal.getName();
	
	Users getuserbyemail = this.userRepository.getuserbyemail(username);
	
	System.out.println(username +"email"+getuserbyemail );

	List<Blog> blog = getuserbyemail.getBlog();
//	ModelAndView m=new ModelAndView();
//	m.addObject(getuserbyemail);
//	m.setViewName("home");
	model.addAttribute("blog", blog);
	model.addAttribute("user",getuserbyemail);
	model.addAttribute("title","Dashboard-Profile");
	return "normal/dashboard";
	
}
@RequestMapping(value="/home",method = RequestMethod.GET)	
public String loggedUserInfoOnHome(Model model,Principal principal)
{
	String name = principal.getName();
	Users getuserbyemail = this.userRepository.getuserbyemail(name);
	model.addAttribute("user",getuserbyemail); 
	return "normal/home";
}

@PostMapping("/search")	
public String searchAll(@RequestParam("query") String q,ApiKey apiKey,Model model,HttpSession httpSession) throws Exception
{		
	try {
		if(q.isBlank()||q.contains("1234567890"))
		{
			httpSession.setAttribute("msg",new com.blogmaker.helper.Messages("Try Entering City/Country/Region ","show alert-danger"));
			return "normal/dashboard";
		}
			System.out.println(q);
			apiKey.setApiId(1);
			apiKey.setApikey("taTSmWiu9bjezYrrIA72iE2WhpR+9fkYE+XYRLaJ0kA=");
			apiRepository.save(apiKey);		
			System.out.println(apiKey.getApiId());			
		Optional<ApiKey> findById = apiRepository.findById(apiKey.getApiId());		
		System.out.println(findById.toString());			
		String decrypt = Dcrypt.decrypt(apiKey.getApikey());		
		System.out.println("realkey "+decrypt);		
		 List<JsonNode> weatherpara = Getweather.getweather(q, decrypt);
		System.out.println(weatherpara);	
		JsonNode location = weatherpara.get(0);
		JsonNode current = weatherpara.get(1);	
		String name=location.get("name").asText();	
      double temp_c=current.get("temp_c").asDouble();    
      JsonNode condition = current.get("condition");   
      String icon = condition.get("icon").asText();	 
      
 		model.addAttribute("name",name);
		model.addAttribute("temp",temp_c);
		model.addAttribute("icon",icon);		
		return "normal/dashboard";
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
		httpSession.setAttribute("msg",new com.blogmaker.helper.Messages("Smothing Went wrong!! try after sometime"+e.getMessage(),"show alert-danger"));
		return "normal/dashboard";
		
	}		
}	 



//Create blog handler
@PostMapping("/create-blog")
public String createBlog(@ModelAttribute("Blog") Blog blog ,@RequestParam("blogtype") String  type,@RequestParam("img") MultipartFile file, Model model,Principal principal,HttpSession httpSession)
{	
	System.out.println(blog);
	System.out.println(type);
	
	try { 
		
		//blog image
		if(file.isEmpty()) 
		{
			
		}
		else
		{			
			blog.setImage(file.getOriginalFilename());
			System.out.println(new ClassPathResource("static/image").getFile().getAbsolutePath());
			File savefile=new ClassPathResource("static/image").getFile().getAbsoluteFile();
			Path path = Paths.get(savefile+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("image uploaded");
			System.out.println(blog.getImage()); 
		}
	
	
		if(type==null)
		{
			httpSession.setAttribute("msg",new com.blogmaker.helper.Messages("Please select Blog type ","show alert-warning"));

		}
		
		
		if(type.equalsIgnoreCase("private"))
		{
			
			blog.setBlogtype(type);
			System.out.println(blog.getBlogtype()+"tese");
		
		}
		else
		{
			blog.setBlogtype("public");
		}	
		String name = principal.getName();	
		Users getuserbyemail = this.userRepository.getuserbyemail(name);	
		blog.setUsers(getuserbyemail);
		getuserbyemail.getBlog().add(blog);		
		Users save = this.userRepository.save(getuserbyemail);
		List<Blog> blog2 = getuserbyemail.getBlog();		
		model.addAttribute("blog", blog2);
		System.out.println("data blog" + blog);
		System.out.println("data added");
		httpSession.setAttribute("msg",new com.blogmaker.helper.Messages("Blog created successfully! ","show alert-success"));

	}
	catch(Exception e)
	{
		e.printStackTrace();
		httpSession.setAttribute("msg",new com.blogmaker.helper.Messages("somthing went worng ","show alert-danger"));

	}	
	return "normal/dashboard";
	
}


//view blog handler
@GetMapping("/blog-view/{bid}")
public String viewBlog(@PathVariable("bid") Integer bid,Model model,Principal principal)
{	
	Blog referenceById = this.blogRepository.getReferenceById(bid);	
	System.out.println(referenceById);
	String name = principal.getName();
	Users logedUser = this.userRepository.getuserbyemail(name);
	if(logedUser.getId()==referenceById.getUsers().getId())
	{
		model.addAttribute("blog", referenceById);
	}	 
	
	return "normal/blog-view"; 	
} 

//get blog update form 
@PostMapping("/blog-update/{bid}")
public String getUpdateBlog(@PathVariable("bid") Integer bid,Model model,Principal principal)
{
	System.out.println(bid);
	Blog referenceByIdblog = this.blogRepository.findById(bid).get();
	String image = referenceByIdblog.getImage();
	System.out.println("image address "+  image);
	System.out.println(referenceByIdblog);
	String name = principal.getName();
	Users logedUser = this.userRepository.getuserbyemail(name);
	if(logedUser.getId()==referenceByIdblog.getUsers().getId())
	{
		model.addAttribute("blog", referenceByIdblog);
	} 
   
	  String blogtype = referenceByIdblog.getBlogtype();
	  
	  if(referenceByIdblog.getBlogtype()=="public")
	  {
		  model.addAttribute("public",blogtype);  
	  }
 
	  if(referenceByIdblog.getBlogtype()=="private") 
	  {
		  model.addAttribute("private",blogtype);
	  }  
	return "normal/blog-update";
	  
}
  
@RequestMapping(value = "/process-update",method = RequestMethod.POST)
public String updateBlog(@ModelAttribute("Blog") Blog blog,@RequestParam("img") MultipartFile file,Principal principal,Model model,HttpSession httpSession)
{
	System.out.println("update blog req:-- "+blog);
	try {		
		//old contact details
		Blog oldblog = this.blogRepository.findById(blog.getBid()).get();
		System.out.println("old contact "+oldblog);	
		//new image
		if(!file.isEmpty())
		{		
			//delete old image			
			File deletefile=new ClassPathResource("static/image").getFile();
			File deletewithname=new File(deletefile,oldblog.getImage());
			deletewithname.delete();
			
			//update new image
			File savefile=new ClassPathResource("static/image").getFile().getAbsoluteFile();
			Path path = Paths.get(savefile+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);	
			
			blog.setImage(file.getOriginalFilename());
		}		
		else
		{
			//if is not selected then set the old one
			blog.setImage(oldblog.getImage());
		}	
		Users user=this.userRepository.getuserbyemail(principal.getName());
		
		blog.setUsers(user);
	
		System.out.println("user :"+user);
		this.blogRepository.save(blog);
			
		httpSession.setAttribute("msg",new com.blogmaker.helper.Messages("blog is updated..","success"));
		
	}
	catch (Exception e) {
		e.printStackTrace();
		httpSession.setAttribute("msg",new com.blogmaker.helper.Messages("somthing went wrong try again later..!!","danger"));
	}

	return "normal/dashboard";
		 
}
 
//delete contact handler
@GetMapping("/delete/{bid}")
public String deleteContact(@PathVariable("bid") Integer bid,Model model,Principal principal,HttpSession session)
{	
	Optional<Blog> optionalBlog = this.blogRepository.findById(bid);
	Blog blog = optionalBlog.get();
	
	String username = principal.getName();
	
	Users userByEmail = this.userRepository.getuserbyemail(username);
	
	if(userByEmail.getId()==blog.getUsers().getId())
	{
	//user table and blog table linked cascade to unlink blog by blog id set user null
		blog.setUsers(null);
	this.blogRepository.delete(blog);
	
	session.setAttribute("msg",new com.blogmaker.helper.Messages("blog deleted successfully..","success"));
	}	 
	return "normal/dashboard";
		
}
	
	
	
	
}
