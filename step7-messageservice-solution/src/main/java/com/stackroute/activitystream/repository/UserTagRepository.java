package com.stackroute.activitystream.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stackroute.activitystream.model.UserTag;

public interface UserTagRepository extends JpaRepository<UserTag, String>{

	@Query("from UserTag where username=:username and tag=:tag")
	public UserTag getUserTag(String username, String tag);
	
}
