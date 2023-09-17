package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// is a Spring annotation used to mark a class as a service component. It tells the Spring
// container to create an instance of this class and manage its lifecycle
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public List<Student> getStudents() {
		return List.of(
			new Student(
				1L,
				"Mariam",
				"mariam.jamal@gmail.com",
				LocalDate.of(2000, Month.JANUARY, 5),
				21
			)
		);
	}
}
