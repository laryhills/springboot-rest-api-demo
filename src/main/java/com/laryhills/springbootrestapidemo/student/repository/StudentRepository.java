package com.laryhills.springbootrestapidemo.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laryhills.springbootrestapidemo.student.model.Student;

/*
Repository for Student
    - extends JpaRepository
    - JpaRepository<type of entity, type of ID>
    - Spring Data JPA will provide implementation for all the methods
    - @Repository is optional

    For Data Access Layer
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
