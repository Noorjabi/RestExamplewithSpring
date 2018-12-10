package com.dz.Dao;

import com.dz.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {
    private static Logger logger = Logger.getLogger(StudentDaoImpl.class);

    private  SessionFactory sessionFactory;


    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * Purpose:insert student detail in databae
     *
     * @param student object of Student
     */
    @Override
    public void addStudent(Student student) {

        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            session.save(student);

            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            session.close();
        }

        logger.info("Student details saved");
    }

    /**
     * Purpose:retrieve students detail from database
     *
     * @return list of student
     */
    @Override
    public List<Student> retrieveStudent() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction=session.getTransaction();
        try {
            transaction.begin();
            List<Student> studentList = session.createQuery("from Student").list();
            transaction.commit();
            logger.info("retrieve list");
            return studentList;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            transaction.rollback();
            return  null;
        }finally{
            session.close();
        }

    }

    /*
     * Purpose:delete student detail from database using Student Id
     * @param id student Id of Student
     */
    @Override
    public void deleteStudent(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction=session.getTransaction();
        try{
            transaction.begin();
        Student student = session.load(Student.class, new Integer(id));
        if(null != student){
            session.delete(student);
        }
        transaction.commit();
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            transaction.rollback();
        }finally{
            session.close();
        }
        logger.info("student deleted successfully");

    }

    /**
     * Purpose:update student details using student Id
     *
     * @param student object of stduent
     */
    @Override
    public void updateStudent(Student student) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction=session.getTransaction();
        try{
            transaction.begin();
            session.update(student);
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            transaction.rollback();
        }finally {
            session.close();
        }
        logger.info("student updated successfully");
    }

    /**
     * Purpose:generate Student Id
     *
     * @return return generated Id
     *//*
    public int getStudentId() {
        int id = 0;
        Session session = this.sessionFactory.getCurrentSession();
        List<Student> studentList = session.createQuery("from Student").list();

        for (Student student : studentList) {

            id = student.getId();
        }
        id = id + 1;
        logger.info("generate Id");
        return id;
        finall
    }*/

    /**
     * Purpose:get Student detail using Student id
     *
     * @param studentId student Id of student
     * @return student Detail
     */
    @Override
    public Student getStudent(int studentId) {
        Student student=null;
        Session session = this.sessionFactory.openSession();
        Transaction transaction=session.getTransaction();
        try {
            transaction.begin();
            student = (Student) session.load(Student.class, new Integer(studentId));
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            transaction.rollback();
        }finally {
            session.close();
        }
        logger.info("student loaded successfully");
        return student;
    }
}
