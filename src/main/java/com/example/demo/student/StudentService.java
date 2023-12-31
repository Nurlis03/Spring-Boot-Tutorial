package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// is a Spring annotation used to mark a class as a service component. It tells the Spring
// container to create an instance of this class and manage its lifecycle
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
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
			throw new IllegalStateException("email taken");
		}
		
		studentRepository.save(student);
		System.out.println(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException(
				"student with id " + studentId + " does not exists");
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
    public void updateStudent(Long studentId, 
							  String name,
							  String email) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException(
					"student with id " + studentId + " doew not exists"
				));
		if (name != null && 
			name.length() > 0 &&
			!Objects.equals(student.getName(), name)) {
				student.setName(name);
			}
		
			if (email != null &&
				email.length() > 0 &&
				!Objects.equals(student.getEmail(), email)) {
					Optional<Student> studentOptional = studentRepository
						.findStudentByEmail(email);
					if (studentOptional.isPresent()) {
						throw new IllegalStateException("email taken");
					}
					student.setEmail(email);
				}
    }
}
