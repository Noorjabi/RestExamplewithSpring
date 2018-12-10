package com.dz.controller;

import com.dz.model.Student;
import com.dz.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class ViewController {


    private StudentService studentService;
    private static Logger logger = Logger.getLogger(ViewController.class);

    @Autowired
    public ViewController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Purpose: the list of student retrieve
     *
     *
     * @return model
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<List<Student>> retrieveStudent() {
        List<Student> listStudent = studentService.retrieveStudent();
        /*if(listStudent.isEmpty()){
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }*/
        logger.info("inside controller");
        /*for(Student student:listStudent)
            logger.info(student.getId());*/
        return new ResponseEntity<List<Student>>(listStudent, HttpStatus.OK);
    }

    /**
     * Purpose:on button click send to the Edit student form
     *
     *
     * @return model
     */
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {

        studentService.addStudent(student);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * Purpose:on delete click call delete method for deletion and redirect to retrieve page
     *
     * @param studentId studentId
     * @return view page
     */
    @RequestMapping(value = "/deleteStudent/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<Student> deleteStudent(@PathVariable(value = "studentId") Integer studentId) {
        Student student = studentService.getStudent(studentId);
        if (student == null) {

            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteStudent(studentId);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    /**
     * Purpose:on update click send to the Edit student form
     *
     * @param studentId student Id of Student
     * @return model
     */
    @RequestMapping(value = "/editStudent/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<Student> editContact(@PathVariable(value = "studentId") Integer studentId, @RequestBody Student student) {

        Student exitstudent =studentService.getStudent(studentId);
        if (exitstudent == null) {

            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        exitstudent.setStdname(student.getStdname());
        exitstudent.setAge(student.getAge());


        studentService.updateStudent(exitstudent);


        return new ResponseEntity<Student>(exitstudent,HttpStatus.OK);
    }

}