package com.stackroute.activitystream.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.repository.CircleRepository;
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
public class CircleServiceImpl implements CircleService{
	
	/*
	 * Autowiring should be implemented for the CircleRepository and UserRepository.
	 *  Please note that we should not create any object using the new keyword
	 * */
	@Autowired
	private CircleRepository circleRepo;
	
	/*
	 * A circle should only be created if the circle does not already exist or the creatorId
	 * is a valid username. 
	 * */
	public boolean save(Circle circle) {
		try {
		circle.setCreatedDate();
		if(get(circle.getCircleName())==null) {
			circleRepo.save(circle);
			return true;
		}else {
			return false;
		}
	
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	}
	
	/*
	 * This method should return the list of existing circles
	 * */
	public List<Circle> getAllCircles() {
		List<Circle> circles = (List<Circle>) circleRepo.findAll();
		return circles;
	}
	
	/*
	 * This method should return the list of existing circles which matches the 
	 * search String
	 * */
	public List<Circle> getAllCircles(String searchString) {
		List<Circle> circles = (List<Circle>) circleRepo.findAll(searchString);
		return circles;
	}
	
	/*
	 * This method should return a specific circle which matches the Circle Name
	 */
	public Circle get(String circleName) {
		
		return circleRepo.findOne(circleName);
	}
	
	/*
	 * This method should delete a specific circle(if exists)
	 */
	public boolean delete(Circle circle) {
		try{
			if(get(circle.getCircleName())!=null) {
			circleRepo.delete(circle);
			return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
		
}