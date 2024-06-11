package com.blogmaker.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import com.blogmaker.dao.UserRepository;
import com.blogmaker.helper.Dcrypt;
import com.blogmaker.model.Blog;
import com.blogmaker.model.News;
import com.blogmaker.model.Users;
import com.blogmaker.services.NewsServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/news")
public class NewsController {

	
	 
@Autowired	
private UserRepository userRepository;	

 
@Autowired
private NewsServices newsServices;

@Value("${api.key}")
private String apikey;



@GetMapping("/all")	
public DeferredResult<String> getIndianNews(Model model,Principal principal) throws Exception 
{	
	String decryptapikey = Dcrypt.decrypt(apikey);
	if(principal==null) 
	{	
		System.out.println("all request active");
		Mono<News> apiResponse = this.newsServices.getApiResponse("https://newsapi.org/v2/top-headlines?country=in",decryptapikey);
		
		//apiResponse.doOnNext(data->System.out.println(data)).subscribe(data->model.addAttribute("t", new ReactiveDataDriverContextVariable(data)));
			//return "news";
		DeferredResult<String> deferredResult = new DeferredResult<>();
        
        apiResponse.subscribe(data -> {   
            model.addAttribute("data", data);  
            deferredResult.setResult("news");
        }, deferredResult::setErrorResult);

        return deferredResult;		  		  
	}
	else
	{
		System.out.println("user loged "+ principal.getName());
		String name = principal.getName();
		Users getuserbyemail = this.userRepository.getuserbyemail(name);
		List<Blog> blog = getuserbyemail.getBlog();	
		model.addAttribute("blog", blog);
		model.addAttribute("user",getuserbyemail); 
		
		Mono<News> apiResponse = this.newsServices.getApiResponse("https://newsapi.org/v2/top-headlines?country=in", apikey);
		
		//apiResponse.doOnNext(data->System.out.println(data)).subscribe(data->model.addAttribute("t", new ReactiveDataDriverContextVariable(data)));
			//return "news";
		DeferredResult<String> deferredResult = new DeferredResult<>();
        
        apiResponse.subscribe(data -> {   
            model.addAttribute("data", data);  
            deferredResult.setResult("normal/news");
        }, deferredResult::setErrorResult);

        return deferredResult;
		
	}
	
}


@GetMapping("/q")
public DeferredResult<String> search(@RequestParam("query") String query, Model model,Principal principal) throws Exception 
{
	
	System.out.println("search active");
	String decryptapikey = Dcrypt.decrypt(apikey);
	
	if(principal==null) 
	{	
		System.out.println("all request active");
		Mono<News> apiResponse = this.newsServices.getApiResponse("https://newsapi.org/v2/everything?q="+query,decryptapikey);
		
		//apiResponse.doOnNext(data->System.out.println(data)).subscribe(data->model.addAttribute("t", new ReactiveDataDriverContextVariable(data)));
			//return "news";
		DeferredResult<String> deferredResult = new DeferredResult<>();
        
        apiResponse.subscribe(data -> {   
            model.addAttribute("data", data);  
            deferredResult.setResult("news");
        }, deferredResult::setErrorResult);

        return deferredResult;		  		  
	}
	else
	{
		System.out.println("user loged "+ principal.getName());
		String name = principal.getName();
		Users getuserbyemail = this.userRepository.getuserbyemail(name);
		List<Blog> blog = getuserbyemail.getBlog();	
		model.addAttribute("blog", blog);
		model.addAttribute("user",getuserbyemail); 
		
		Mono<News> apiResponse = this.newsServices.getApiResponse("https://newsapi.org/v2/top-headlines?country=in", apikey);
		
		//apiResponse.doOnNext(data->System.out.println(data)).subscribe(data->model.addAttribute("t", new ReactiveDataDriverContextVariable(data)));
			//return "news";
		DeferredResult<String> deferredResult = new DeferredResult<>();
        
        apiResponse.subscribe(data -> {   
            model.addAttribute("data", data);  
            deferredResult.setResult("normal/news");
        }, deferredResult::setErrorResult);

        return deferredResult;
		
	}
	
}










}
