package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(
			@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}

	@PutMapping(path="{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
				studentService.updateStudent(studentId, name, email);
			}
}
