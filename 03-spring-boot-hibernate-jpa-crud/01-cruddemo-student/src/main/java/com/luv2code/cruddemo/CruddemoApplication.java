package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

		    //queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

		    //deleteStudent(studentDAO);

		    deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted );
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int studentId = 3;
		System.out.println("deleting student id: " + studentId);
		studentDAO.delete(studentId );
	}

	private void updateStudent(StudentDAO studentDAO) {

		//retrieve student based on the id: primary key
        int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		//Change first name to "Scooby"
		System.out.println("Updating student...");
		myStudent.setFirstName("Scooby");
		studentDAO.update(myStudent);

		//update the student
		studentDAO.update(myStudent);

		//display the update student
		System.out.println("updated student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		//get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Duck");

		//display list of students
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		//get a list of students
		List<Student> theStudents = studentDAO.findAll();

		//display list of students
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		//create a student object
		System.out.println("Creating new student objects...");
		Student tempStudent = new Student("daffy@luv2code.com","Duck","Daffy");

		//save the student
		System.out.println("saving the student...");
		studentDAO.save(tempStudent);

		//display id of the saved student
        int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		//retrieve student based on the id: primary key
		System.out.println("retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("Found the student: " + myStudent);
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
