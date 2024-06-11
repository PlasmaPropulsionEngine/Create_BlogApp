package com.blogmaker.model;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Blog {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)	
private int Bid;


private String title;

@Column(length=5000)
private String Description;

private String image;

private String blogtype;



@Transient
private MultipartFile img;

@ManyToOne
@JsonIgnore
private Users users;

public int getBid() {
	return Bid;
}

public void setBid(int bid) {
	Bid = bid;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return Description;
}

public void setDescription(String description) {
	Description = description;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public String getBlogtype() {
	return blogtype;
}

public void setBlogtype(String blogtype) {
	this.blogtype = blogtype;
}

public Users getUsers() {
	return users;
}

public void setUsers(Users users) {
	this.users = users;
}

public Blog(int bid, String title, String description, String image, String blogtype, Users users) {
	super();
	Bid = bid;
	this.title = title;
	Description = description;
	this.image = image;
	this.blogtype = blogtype;
	this.users = users;
}

public Blog() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Blog [Bid=" + Bid + ", title=" + title + ", Description=" + Description + ", image=" + image + ", blogtype="
			+ blogtype + ", users=" + users + "]";
}





	
	
	
	
	
	
	
	
}
