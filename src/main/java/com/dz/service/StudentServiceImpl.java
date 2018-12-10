package com.dz.service;

import com.dz.Dao.StudentDao;
import com.dz.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentDao studentDao ;



    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * Purpose: addStudent method  call from studentDao
     *
     * @param student object of student
     */
    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    /**
     * Purpose:retrieveStudent call from studentDao
     *
     * @return List of student details
     */
    @Override
    public List<Student> retrieveStudent() {
        return studentDao.retrieveStudent();
    }

    /**
     * Purpose:deleteStudent call from studentDao
     *
     * @param studentId student Id of Student
     */
    @Override
    public void deleteStudent(Integer studentId) {
        studentDao.deleteStudent(studentId);
    }

    /**
     * Purpose:updateStudent call from studentDao
     *
     * @param student object of Student
     */
    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    /**
     * Purpose:getStudent call from studentDao
     *
     * @param studentId student Id of Student
     * @return object of student
     */
    @Override
    public Student getStudent(Integer studentId) {
        return studentDao.getStudent(studentId);
    }
}
