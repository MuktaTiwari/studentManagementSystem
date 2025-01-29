package com.pinnacle.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pinnacle.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	List<Student> findByCourse(String course);
	
    List<Student> findByBalanceFeesGreaterThan(double balanceFees);
}
