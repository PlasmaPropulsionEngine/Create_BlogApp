package com.blogmaker.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.blogmaker.model.WeatherPara;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class Getweather {

	
public static List<JsonNode> getweather(String q,String decrypt)
{
	System.out.println(decrypt);
	System.out.println(q);
	 try {
         // URL of the JSON API
         String apiUrl = "http://api.weatherapi.com/v1/current.json?q="+q+"&key="+decrypt;

         // Create a URL object
         URL url = new URL(apiUrl);

         // Open a connection to the URL
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();

         // Set the request method
         connection.setRequestMethod("GET");

         // Read the response
         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         StringBuilder response = new StringBuilder();
         String line;
         while ((line = reader.readLine()) != null) {
             response.append(line);
         }
         reader.close();
         
        
         // Print the JSON response
         //System.out.println(response.toString());

         ObjectMapper objectMapper = new ObjectMapper();
         JsonNode jsonNode = objectMapper.readTree(response.toString());
         JsonNode jsonNode2 = jsonNode.get("location");
//         String name=jsonNode2.get("name").asText();
//         double temp_c=jsonNode2.get("temp_c").asDouble();
//         JsonNode jsonNode3 = jsonNode2.get("current");
//         JsonNode jsonNode4 = jsonNode3.get("condition");
//         	
//         String icon = jsonNode4.get("icon").asText();
         
         
         //System.out.println(name);
         
         //insert into class
        // WeatherPara weatherpara = objectMapper.readValue(response.toString(), WeatherPara.class);
         
         
         JsonNode jsonNode3 = jsonNode.get("current");     
         List<JsonNode>listobject= new ArrayList<JsonNode>(); 
         listobject.add(jsonNode2);
         listobject.add(jsonNode3);
         
         // Close the connection
         connection.disconnect();
          String string = response.toString();
           return listobject;
     } 
	 catch (Exception e) 
	 {
         e.printStackTrace();
     }
	return null;	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
