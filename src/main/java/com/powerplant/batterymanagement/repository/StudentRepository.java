package com.powerplant.batterymanagement.repository;

import com.powerplant.batterymanagement.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
