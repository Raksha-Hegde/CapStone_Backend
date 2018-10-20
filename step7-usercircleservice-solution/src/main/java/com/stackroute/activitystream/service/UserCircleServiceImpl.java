package com.stackroute.activitystream.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.activitystream.model.UserCircle;
import com.stackroute.activitystream.repository.UserCircleRepository;

/*
* Service classes are used here to implement additional business logic/validation.
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn’t currently 
* provide any additional behavior over the @Component annotation, but it’s a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class UserCircleServiceImpl implements UserCircleService {
	/*
	 * Autowiring should be implemented for the UserCircleRepository. Please note
	 * that we should not create any object using the new keyword
	 */
	@Autowired
	private UserCircleRepository userCircleRepo;

	/*
	 * * This method should be used to add a user to a specific circle. You need to
	 * validate * whether the user and also the circle exist. Also, please check if
	 * the user is already * added to the circle. Call the corresponding method of
	 * Respository interface. *
	 */ @SuppressWarnings("null")
	public boolean addUser(String username, String circleName) {
		try {
			UserCircle usrCir = get(username, circleName);
			if (usrCir == null) {
				usrCir.setCircleName(circleName);
				usrCir.setUserName(username);
				userCircleRepo.save(usrCir);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * * This method should be used to remove a user from a specific circle. Please
	 * check * if the user is added to the circle before trying to remove. Call the
	 * corresponding method of Respository interface. *
	 */ public boolean removeUser(String username, String circleName) {
		UserCircle usr = get(username, circleName);
		if (usr != null) {
			userCircleRepo.delete(usr);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * * This method should be used to show circles subscribed by a specific user.
	 * Call the corresponding method of Respository interface. *
	 */ public List<String> getMyCircles(String username) {
		return userCircleRepo.findCircleNameByUserName(username);
	}

	/*
	 * * This method should be used to check whether a specific user has subscribed
	 * to a * specific circle. Call the corresponding method of Respository
	 * interface. *
	 */ public UserCircle get(String username, String circleName) {
		return userCircleRepo.getUsernameAndCircleName(username, circleName);
	}
}