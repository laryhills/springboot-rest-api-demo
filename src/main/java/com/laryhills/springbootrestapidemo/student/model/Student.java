package com.laryhills.springbootrestapidemo.student.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.time.Period;

/*
Class called Student
    - use CMD + N to generate constructors, getters , setters, toString (in IntelliJ)
    - one constructor without ID since ID comes form db

		For Model Layer
 */

@Entity // for hibernate
@Table // for table in db
public class Student {
	// Generate Sequence for ID
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1 // increment by 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	@Transient // not a column in db
	private Integer age;

	public Student() {
	}

	public Student(Long id, String name, String email, LocalDate dob) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	public Student(String name, String email, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public Integer getAge() {
		// from dob, calculate age
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", dob=" + dob +
				", age=" + age +
				'}';
	}
}
