package com.laryhills.springbootrestapidemo.student.service;

import com.laryhills.springbootrestapidemo.student.model.Student;
import com.laryhills.springbootrestapidemo.student.repository.StudentRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    Business Layer
    Services has business logic

    a bean @Service so it can be injected, coupled with @Autowired in controller
 */

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

  // public void addNewStudent(Student student) {
  // // check if email is taken
  // // if yes, throw error
  // // else save
  // // return response

  // // Optional<Student> studentOptional =
  // // studentRepository.findStudentByEmail(student.getEmail());

  // studentRepository.findStudentByEmail(student.getEmail())
  // .ifPresentOrElse(
  // s -> {
  // throw new IllegalStateException("email taken");
  // },
  // () -> {
  // studentRepository.save(student);
  // });
  // }

  public Student addNewStudent(Student student) {
    // Check if email is taken
    // If yes, throw error
    // Else save and return the saved student

    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

    if (studentOptional.isPresent()) {
      throw new IllegalStateException("Email already taken");
    }

    return studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);

    if (!exists) {
      throw new IllegalStateException("Student with id " + studentId + " does not exist");
    }

    studentRepository.deleteById(studentId);
  }

  @Transactional
  public Student updateStudent(Student student) {
    // Check if student exists
    // If yes, update and return the updated student
    // Else throw error

    Optional<Student> studentOptional = studentRepository.findById(student.getId());

    if (!studentOptional.isPresent()) {
      throw new IllegalStateException("Student with id " + student.getId() + " does not exist");
    }

    Student studentToUpdate = studentOptional.get();

    if (student.getName() != null && student.getName().length() > 0) {
      studentToUpdate.setName(student.getName());
    }

    if (student.getEmail() != null && student.getEmail().length() > 0) {
      Optional<Student> studentOptionalByEmail = studentRepository.findStudentByEmail(student.getEmail());

      if (studentOptionalByEmail.isPresent()) {
        throw new IllegalStateException("Email already taken");
      }

      studentToUpdate.setEmail(student.getEmail());
    }

    if (student.getDob() != null) {
      studentToUpdate.setDob(student.getDob());
    }

    return studentToUpdate;
  }

}
