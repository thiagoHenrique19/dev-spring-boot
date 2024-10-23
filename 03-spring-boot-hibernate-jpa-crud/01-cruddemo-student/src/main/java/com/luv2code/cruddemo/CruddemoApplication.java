package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

		return runner -> {
			//createStudent(studentDAO);

			createMultipleStudents(studentDAO);
		};
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		//create multiple students
		System.out.println("Creating 3 student object...");
		Student tempStudent1 = new Student("john@luv2code.com" ,"Doe", "John");
		Student tempStudent2 = new Student("mary@luv2code.com" ,"Public", "Mary");
		Student tempStudent3 = new Student("bonita@luv2code.com" ,"Applebum", "Bonita");

		//save the student objects
		System.out.println("saving the objects...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {

		//create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("paul@luv2code.com" ,"Doe", "Paul");

	    //save the student object
		System.out.println("saving the student...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("save student. Generated id: " + tempStudent.getId());
	}
}
