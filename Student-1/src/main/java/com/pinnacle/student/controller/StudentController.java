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
    public String saveStudent(@ModelAttribute Student student, RedirectAttributes attributes) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            Long id = savedStudent.getId();
            attributes.addFlashAttribute("message", "Record with id: '" + id + "' is saved successfully!");
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry")) {
                attributes.addFlashAttribute("message", "Error: Duplicate contact number is not allowed.");
                return "redirect:/student/register"; 
            }
            attributes.addFlashAttribute("message", "Error saving student: " + e.getMessage());
        }
        return "redirect:/student/getAllStudents";  
    }
    
    @GetMapping("/getAllStudents")
    public String getAllStudent(@RequestParam(value = "message", required = false) String message, Model model){
        try {
            List<Student> student = studentService.getAllStudent();
            model.addAttribute("list", student);
        } catch (Exception e) {
            model.addAttribute("message", "Error fetching students: " + e.getMessage());
        }
        model.addAttribute("message", message);
        return "allStudentsPage";
    }
    
    @GetMapping("/edit")
    public String getEditPage(Model model, RedirectAttributes attributes, @RequestParam Long id) {
        try {
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            return "editStudentPage";
        } catch (NoSuchElementException e) {
            attributes.addAttribute("message", "Student not found: " + e.getMessage());
            return "redirect:getAllStudents";
        }
    }
    
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, RedirectAttributes attributes) {
        try {
            studentService.updateStudent(student);
            Long id = student.getId();
            attributes.addAttribute("message", "Student with id: '" + id + "' is updated successfully!");
        } catch (Exception e) {
            attributes.addAttribute("message", "Error updating student: " + e.getMessage());
        }
        return "redirect:getAllStudents";
    }
    
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam Long id, RedirectAttributes attributes) {
        try {
            studentService.deleteStudentById(id);
            attributes.addAttribute("message", "Student with Id: '" + id + "' is removed successfully!");
        } catch (NoSuchElementException e) {
            attributes.addAttribute("message", "Student not found: " + e.getMessage());
        } catch (Exception e) {
            attributes.addAttribute("message", "Error deleting student: " + e.getMessage());
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
        try {
            List<Student> students = studentService.getStudentsByCourse(course);
            model.addAttribute("students", students);
            model.addAttribute("course", course);
        } catch (Exception e) {
            model.addAttribute("message", "Error fetching students for course: " + e.getMessage());
        }
        return "studentByCourse"; 
    }
    
    @GetMapping("/showFees")
    public String showFeesDetails(Model model) {
        try {
            List<Student> studentsWithBalanceFees = studentService.getStudentsWithBalanceFees();
            model.addAttribute("studentsWithBalanceFees", studentsWithBalanceFees);
        } catch (Exception e) {
            model.addAttribute("message", "Error fetching fee details: " + e.getMessage());
        }
        return "showFeesDetails";  
    }
    
    @GetMapping("/coursesWithStudents")
    public String getCoursesWithStudents(Model model) {
        try {
            Map<String, List<Student>> coursesWithStudents = studentService.getStudentsGroupedByCourse();
            model.addAttribute("coursesWithStudents", coursesWithStudents);
        } catch (Exception e) {
            model.addAttribute("message", "Error fetching courses with students: " + e.getMessage());
        }
        return "coursesWithStudents"; 
    }
}
