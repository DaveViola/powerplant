package com.powerplant.batterymanagement.service;

import com.powerplant.batterymanagement.model.Student;
import com.powerplant.batterymanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  StudentRepository studentRepository;

  public void saveOrUpdate(Student student) {
    studentRepository.save(student);
  }
}
