package com.stackroute.activitystream.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.model.UserTag;
import com.stackroute.activitystream.repository.MessageRepository;
import com.stackroute.activitystream.repository.UserTagRepository;

@Service("MessageService")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private UserTagRepository userTagRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserTag userTag;
	
	public List<Message> getMessagesFromCircle(String circleName,int pageNumber) {
		
		return messageRepository.getMessagesFromCircle(circleName);
	}
	
	public List<Message> getMessagesFromUser(String username,String otherUsername,int pageNumber) {
		
		return messageRepository.getMessagesFromUser(username, otherUsername); 
	}
	

	
	public boolean sendMessageToCircle(String circleName,Message message) {
		
		message.setPostedDate();
		message.setCircleName(circleName);
		
		
			messageRepository.save(message);
			return true;
		
	}
	 
	public boolean sendMessageToUser(String username,Message message) {
		
		message.setPostedDate();
		message.setReceiverId(username);
		
		
			messageRepository.save(message);
			return true;
		
		
	} 	
	
	public List<String> listTags() {
		
		return messageRepository.listAllTags();
		
	}
	
	public List<String> listMyTags(String username) {
		
		return messageRepository.listMyTags(username);
	}
	
	public List<Message> showMessagesWithTag(String tag,int pageNumber) {
		
		return messageRepository.showMessagesWithTag(tag);
	}
	
	public boolean subscribeUserToTag(String username, String tag) {
		
		userTag.setTag(tag);
		userTag.setUsername(username);
		
		if(!(listMyTags(username).contains(tag))) {
			userTagRepository.save(userTag);
			return true;
		}
		else
			return false;
	}
	
	public boolean unsubscribeUserToTag(String username, String tag) {
		userTag = userTagRepository.getUserTag(username, tag);
		if(listMyTags(username).contains(tag)) {
			userTagRepository.delete(userTag);
			return true;
			}
			else 
				return false;
	}

}
