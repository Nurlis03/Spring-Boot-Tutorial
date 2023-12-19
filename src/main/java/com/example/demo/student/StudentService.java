package com.example.demo.student;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
// is a Spring annotation used to mark a class as a service component. It tells the Spring
// container to create an instance of this class and manage its lifecycle
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		System.out.println(student);
	}
}
