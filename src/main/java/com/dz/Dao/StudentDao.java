package com.dz.Dao;

import com.dz.model.Student;

import java.util.List;

public interface StudentDao {

    void addStudent(Student student);

    List<Student> retrieveStudent();

    void deleteStudent(int studentId);

    void updateStudent(Student student);

 /*   int getStudentId();
*/
    Student getStudent(int studentId);
}
