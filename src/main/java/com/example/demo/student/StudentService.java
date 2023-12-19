package com.example.demo.student;

import java.util.List;
import java.util.Optional;

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
		Optional<Student> studentOptional = studentRepository
				.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("emai taken");
		}
		
		studentRepository.save(student);
		System.out.println(student);
	}
}
