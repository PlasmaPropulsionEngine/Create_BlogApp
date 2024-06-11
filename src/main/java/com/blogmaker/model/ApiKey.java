package com.blogmaker.model;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ApiKey {

	
@Id	
private int apiId;

@Value("${api.key}")	
private String apikey;

public int getApiId() {
	return apiId;
}

public void setApiId(int apiId) {
	this.apiId = apiId;
}

public String getApikey() {
	return apikey;
}

public void setApikey(String apikey) {
	this.apikey = apikey;
}

public ApiKey(int apiId, String apikey) {
	super();
	this.apiId = apiId;
	this.apikey = apikey;
}

public ApiKey() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "ApiKey [apiId=" + apiId + ", apikey=" + apikey + "]";
}












	
	
	
	
	
}
