package com.blogmaker.model;

import java.util.List;

public class News {

	
public String status;

public String  totalResults;


public List<Articles> articles;


public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


public String getTotalResults() {
	return totalResults;
}


public void setTotalResults(String totalResults) {
	this.totalResults = totalResults;
}


public List<Articles> getArticles() {
	return articles;
}


public void setArticles(List<Articles> articles) {
	this.articles = articles;
}


public News(String status, String totalResults, List<Articles> articles) {
	super();
	this.status = status;
	this.totalResults = totalResults;
	this.articles = articles;
}


public News() {
	super();
	// TODO Auto-generated constructor stub
}


@Override
public String toString() {
	return "News [status=" + status + ", totalResults=" + totalResults + ", articles=" + articles + "]";
}


	
	
	
	
	
}
