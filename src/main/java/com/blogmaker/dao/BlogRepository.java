package com.blogmaker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogmaker.model.Blog;
import com.blogmaker.model.Users;

public interface BlogRepository extends JpaRepository<Blog,Integer> {

	
	//get blog by user id
	@Query("select b from Blog b where b.Bid=:uid")
	public Blog getblogbyuserID(@Param("uid") int uid);
	
	@Query("select b from Blog b where b.blogtype=:public")
	public List<Blog> getPublicBlogs(@Param("public") String p);
	
	
	
	
	
	
	
	
	
	
}
