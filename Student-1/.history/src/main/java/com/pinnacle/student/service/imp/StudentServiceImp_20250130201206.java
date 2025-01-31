package com.pinnacle.student.service.imp;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinnacle.student.model.Student;
import com.pinnacle.student.repository.StudentRepository;
import com.pinnacle.student.service.StudentService;

@Service
public class StudentServiceImp implements StudentService {

	
	@Autowired
	private StudentRepository repo;
	
	@Override
	public Student saveStudent(Student student) {
		return repo.save(student);
	}

	@Override
	public List<Student> getAllStudent() {
		return repo.findAll();
	}

	@Override
	public Student getStudentById(Long id) {
		
		Optional<Student>opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
					
		}
		else {
			throw new NoSuchElementException("Student with Id : "+id+" Not Found");
		}
		
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		repo.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
	
		repo.delete(getStudentById(id));
	}

	@Override
	public List<Student> getStudentsByCourse(String course) {
		return repo.findByCourse(course);
	}

	@Override
	public List<Student> getStudentsWithBalanceFees() {
		return repo.findByBalanceFeesGreaterThan(0);
	}
	
	
	public Map<String, List<Student>> getStudentsGroupedByCourse() {
	    List<Student> allStudents = repo.findAll(); // Fetch all students
	    return allStudents.stream()
	                      .collect(Collectors.groupingBy(Student::getCourse)); // Group students by course
	}

	

}
