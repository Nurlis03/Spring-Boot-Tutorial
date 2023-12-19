package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class-level annotation @RestController indicates that this class is a RESTful controller
// that handles web requests and produces JSON responses.
@RestController

// The @RequestMapping annotation defines the base URL path for all requests handled by this
// controller
@RequestMapping(path = "api/v1/student/")
public class StudentController {

	private final StudentService studentService;
	
	@Autowired // we saying studentService should be auto wired for us
	// so studenetService magically instantiated for us and injected to this constructor
    public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
}
