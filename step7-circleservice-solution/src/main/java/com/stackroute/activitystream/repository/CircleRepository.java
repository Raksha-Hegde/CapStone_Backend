package com.stackroute.activitystream.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.stackroute.activitystream.model.Circle;
/*
* This class is implementing the JpaRepository interface for Circle.
* */
public interface CircleRepository extends JpaRepository<Circle, String>{
	
	@Query("FROM Circle c where c.circleName like '%:searchString%'")
	List<Circle> findAll(String searchString);
	
}