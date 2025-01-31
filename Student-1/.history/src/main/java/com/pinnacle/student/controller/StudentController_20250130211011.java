package com.pinnacle.student.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinnacle.student.model.Student;
import com.pinnacle.student.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String showHomePage() {
		return "homePage";
		
	}
	
	@GetMapping("/register")
	public String showRegistration() {
		
		return "registrationStudentPage";
	}
	
	@PostMapping("/save")
public String saveStudent(@ModelAttribute Student student, Model model) {
    Student savedStudent = studentService.saveStudent(student);
    Long id = savedStudent.getId();
    model.addAttribute("message", "Record with id: '" + id + "' is saved successfully!");
    return "allStudentsPage";  // Forward to the page instead of redirecting
}
	
	@GetMapping("/getAllStudents")
	public String getAllStudent(@RequestParam(value = "message", required = false) String message, Model model){
		
		List<Student> student = studentService.getAllStudent();
		model.addAttribute("list", student);
		model.addAttribute("message", message);
		return "allStudentsPage";
		
	}
	
	@GetMapping("/edit")
	public String getEditPage(Model model, RedirectAttributes attributes, @RequestParam Long id) {
		
		String page = null;
		try {
			Student student = studentService.getStudentById(id);
			model.addAttribute("student", student);
			page = "editStudentPage";
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:getAllStudents";
		}
		
		return page;
	}
	
	@PostMapping("/update")
	public String updateStudent(@ModelAttribute Student student, RedirectAttributes attributes) {
		
		studentService.updateStudent(student);
		Long id = student.getId();
		attributes.addAttribute("message", "Student with id: '"+id+"' is updated successfully !");
		return "redirect:getAllStudents";
		
	}
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam Long id, RedirectAttributes attributes) {
		
		try {
		studentService.deleteStudentById(id);
        attributes.addAttribute("message", "Student with Id : '"+id+"' is removed successfully!");
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());

		}
		return "redirect:getAllStudents";
		
	
	}
	
	
	@GetMapping("/selectCourse")
	public String selectCourse(Model model) {
	    model.addAttribute("courses", List.of("Java", "Python", "AI-ML", "FrontEnd - Technologies", "BackEnd Technologies"));
	    return "selectCourse"; 
	}
	
    @GetMapping("/getStudentsByCourse")
    public String getStudentsByCourse(@RequestParam("course") String course, Model model) {
        List<Student> students = studentService.getStudentsByCourse(course); // Get students by selected course
        model.addAttribute("students", students);
        model.addAttribute("course", course);  
        return "studentByCourse"; 
    }
    
    
    @GetMapping("/showFees")
    public String showFeesDetails(Model model) {
        
        List<Student> studentsWithBalanceFees = studentService.getStudentsWithBalanceFees();
        
        
        model.addAttribute("studentsWithBalanceFees", studentsWithBalanceFees);
        return "showFeesDetails";  
    }
    
    
    
    @GetMapping("/coursesWithStudents")
    public String getCoursesWithStudents(Model model) {
        Map<String, List<Student>> coursesWithStudents = studentService.getStudentsGroupedByCourse();
        model.addAttribute("coursesWithStudents", coursesWithStudents);
        System.out.println("Courses with Students: " + coursesWithStudents); 
        return "coursesWithStudents"; 
    }

}
