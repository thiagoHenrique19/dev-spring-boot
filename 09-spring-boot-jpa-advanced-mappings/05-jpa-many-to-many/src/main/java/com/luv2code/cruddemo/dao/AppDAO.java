package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailbyId(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCourseByInstructorId(int theId);

   Instructor findInstructorByJoinFetch(int theId);

   void update(Instructor tempInstructor);

   void update(Course tempCourse);

   Course findCourseById(int theId);

   void deleteCourseById(int theId);

   void save(Course theCourse);

   Course findCourseAndReviewsByCourseId(int theId);
}
