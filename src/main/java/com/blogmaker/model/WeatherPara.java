package com.blogmaker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherPara {

@JsonProperty("location")
private String location;	
	
@JsonProperty("name")	
private String name;

@JsonProperty("temp_c")
private double temp_c;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getTemp_c() {
	return temp_c;
}

public void setTemp_c(double temp_c) {
	this.temp_c = temp_c;
}

public WeatherPara(String name, double temp_c) {
	super();
	this.name = name;
	this.temp_c = temp_c;
}

public WeatherPara() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "WeatherPara [name=" + name + ", temp_c=" + temp_c + "]";
}
	










	
}
