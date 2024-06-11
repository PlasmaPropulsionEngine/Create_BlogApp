package com.blogmaker.model;

public class Articles 
{
	
public String author;

public String title;


private String urlToImage;


private String description;

private String url;

private String publishedAt;

public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author = author;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getUrlToImage() {
	return urlToImage;
}

public void setUrlToImage(String urlToImage) {
	this.urlToImage = urlToImage;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getPublishedAt() {
	return publishedAt;
}

public void setPublishedAt(String publishedAt) {
	this.publishedAt = publishedAt;
}

public Articles(String author, String title, String urlToImage, String description, String url, String publishedAt) {
	super();
	this.author = author;
	this.title = title;
	this.urlToImage = urlToImage;
	this.description = description;
	this.url = url;
	this.publishedAt = publishedAt;
}

public Articles() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Articles [author=" + author + ", title=" + title + ", urlToImage=" + urlToImage + ", description="
			+ description + ", url=" + url + ", publishedAt=" + publishedAt + "]";
}




}
	
	

