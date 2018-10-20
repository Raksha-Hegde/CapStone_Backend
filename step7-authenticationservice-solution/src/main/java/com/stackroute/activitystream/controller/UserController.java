package com.stackroute.activitystream.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.model.User;
import com.stackroute.activitystream.service.UserService;



@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	 
	 /*
	  * Retrieve All Users
	  * */ 
	 
	@GetMapping
	public ResponseEntity<List<User>> listAllUsers() {
		
		return new ResponseEntity<List<User>>(userService.list(), HttpStatus.OK);

	}

	  
	/*
	 * Retrieve Single User
	 */ 
	 
	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("username") String username) {
		
		User user = userService.get(username);
		if (user == null) {
			
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	 
	/*
	 * Create a new user
	 */ 
	 
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User u = userService.get(user.getUsername());
		if (u != null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}

		//userDAO.save(user);
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	 
	/*
	 * Update an User 
	 */
	 
	@PutMapping(value = "/{username}")
	public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user) {
		
		User currentUser = userService.get(username);

		if (currentUser == null) {

			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setPassword(user.getPassword());

		userService.update(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

}