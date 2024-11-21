package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
