package com.blogmaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.blogmaker.model.News;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NewsServices
{

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	
	public Mono<News> getApiResponse(String url, String apiKey) {
		  return webClientBuilder.build()
                .get()
                .uri(url)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(News.class);
              
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
