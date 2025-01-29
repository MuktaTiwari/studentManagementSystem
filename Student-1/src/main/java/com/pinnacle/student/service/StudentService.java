package com.pinnacle.student.service;

import java.util.List;
import java.util.Map;

import com.pinnacle.student.model.Student;

public interface StudentService {

	public Student saveStudent(Student student);

	public List<Student> getAllStudent();

	public Student getStudentById(Long id);

	public void updateStudent(Student student);

	public void deleteStudentById(Long id);

	public List<Student> getStudentsByCourse(String course);

	public List<Student> getStudentsWithBalanceFees();

	public Map<String, List<Student>> getStudentsGroupedByCourse();

	
}
