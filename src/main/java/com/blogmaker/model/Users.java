package com.blogmaker.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Users {

	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@NotBlank(message = " First Name Required ")
@Size(min = 3,message = "Minimum 3 Charater Required")
private String firstname;


private String lastname;

@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid Email !!" )
@Column(unique =true)
private String email;


@NotBlank(message = "Password can not be empty !!")
//@Size(min=8,message = "max charters 12 and min charters 8")
private String password;


//@Transient
//@AssertTrue(message = "Please accept terms and conditions")
//private boolean agree;

private String role;	
	
private boolean enabled;

@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "users")
private List<Blog>blog=new ArrayList<Blog>();


//public boolean isAgree() {
//	return agree;
//}
//
//public void setAgree(boolean agree) {
//	this.agree = agree;
//}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public Users(int id,
		@NotBlank(message = " First Name Required ") @Size(min = 3, message = "Minimum 3 Charater Required") String firstname,
		String lastname,
		@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email !!") String email,
		@NotBlank(message = "Password can not be empty !!") String password, String role, boolean enabled) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.password = password;
	this.role = role;
	this.enabled = enabled;
}


public List<Blog> getBlog() {
	return blog;
}

public void setBlog(List<Blog> blog) {
	this.blog = blog;
}

public Users() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
			+ password + ", role=" + role + ", enabled=" + enabled + "]";
}	
	
	
	
	
	
	
	
	
}
