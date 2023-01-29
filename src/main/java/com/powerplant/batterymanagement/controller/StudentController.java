package com.powerplant.batterymanagement.controller;

import com.powerplant.batterymanagement.model.Student;
import com.powerplant.batterymanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  @Autowired
  StudentService studentService;

  @PostMapping("/student")
  private int saveStudent(@RequestBody Student student) {
    studentService.saveOrUpdate(student);
    return student.getId();
  }
}
