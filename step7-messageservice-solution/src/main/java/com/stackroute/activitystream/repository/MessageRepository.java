package com.stackroute.activitystream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.model.UserTag;


public interface MessageRepository extends CrudRepository<Message, Integer>{

	@Query("from Message where circleName=(?1) order by postedDate desc")
	public List<Message> getMessagesFromCircle(String circleName);
	
	@Query("from Message where (receiverID=(?1) and senderID=(?2)) or (receiverID=(?2) and senderID=(?1)) order by postedDate desc")
	public List<Message> getMessagesFromUser(String username, String otherUsername);
	
	@Query("select distinct m.tag from Message m")
	public List<String> listAllTags();
	
	@Query("select tag from UserTag where username=(?1)")
	public List<String> listMyTags(String username);
	
	@Query("from Message where tag like %:tag% order by postedDate desc")
	public List<Message> showMessagesWithTag(String tag);
	
	@Query("from UserTag where username=:username and tag=:tag")
	public UserTag getUserTag(String username, String tag);
	
}
