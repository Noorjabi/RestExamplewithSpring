package com.dz.service;

import com.dz.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    List<Student> retrieveStudent();

    void deleteStudent(Integer studentId);

    void updateStudent(Student student);

    Student getStudent(Integer studentId);
}
