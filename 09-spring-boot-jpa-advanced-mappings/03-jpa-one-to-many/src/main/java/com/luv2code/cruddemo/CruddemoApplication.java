package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);}
	
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
		    //createInstructor(appDAO);

			//findInstructor(appDAO);
			
			 //deleteInstructor(appDAO);

			//findInstructorDetail(appDAO);

			//deleteInstructorDetail(appDAO);

			//createInstructorWithCourses(appDAO);

		     //findInstructorWithCourses(appDAO);

			//findCoursesForInstructor(appDAO);

			//findInstructorWithCoursesJoinFetch(appDAO);
			
			updateInstructor(appDAO);
		};
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		//update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		//find instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		//find courses for instructor
		System.out.println("Finding coursses for instructor id: " + theId);
		List<Course> courses = appDAO.findCourseByInstructorId(theId);

		//associate the obejcts
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//create the instructor
		Instructor tempInstructor=
				new Instructor("Susan","Public","susan.public@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.youtube.com",
						"Video Games");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		//add course to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

	    //save the instrucotr
		//
 		//NOTE:this will ALSO save the courses
		//because of CascadeType.PERSIST
		//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 4;
		System.out.println("Deleting instructr detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		//get the instructor detail object
		int theId = 2;
		InstructorDetail temInstructorDetail = appDAO.findInstructorDetailbyId(theId);

		//print the instructor detail
		System.out.println("tempInstructorDetail: " + temInstructorDetail);

		//print the associated instructor
		System.out.println("the associated instructor: " + temInstructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);

		appDAO.deleteInstructorById(theId);
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate instructorDetail only: " + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {
/*
		//create the instructor
		Instructor tempInstructor=
				new Instructor("Chad","Darby","darby@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!");


 */
		//create the instructor
		Instructor tempInstructor=
				new Instructor("Madhu","Patel","madhu@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Guitar");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		//
 		//NOTE: this will ALSO save the details object
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}
