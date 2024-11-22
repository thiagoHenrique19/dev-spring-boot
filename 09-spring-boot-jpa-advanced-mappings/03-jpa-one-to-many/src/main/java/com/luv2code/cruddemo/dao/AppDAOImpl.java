package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    //define field for entity manager
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteInstructorById(int theId){

        //retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailbyId(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        //retrieve instructor detail
        InstructorDetail tempInstructordetail = entityManager.find(InstructorDetail.class, theId);

        //remote the associated object reference
        //break bi-directional link
        //
        tempInstructordetail.getInstructor().setInstructorDetail(null);

        //delete the instructor detail
        entityManager.remove(tempInstructordetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int theId) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        //execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByJoinFetch(int theId) {

       //create query

        TypedQuery<Instructor> query = entityManager.createQuery(
                                                          "select i from instructor i"
                                                            +"JOIN FETCH i.courses "
                                                            +"JOIN FETCH i.instructorDetail "
                                                            +"where i.id = :data", Instructor.class);
        query.setParameter("data",theId);

        //execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
          entityManager.merge(tempInstructor);
    }

    //inject entity manager using constructors injection
   @Autowired
    public AppDAOImpl(EntityManager entityManager){
       this.entityManager = entityManager;
   }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
           entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }
}
