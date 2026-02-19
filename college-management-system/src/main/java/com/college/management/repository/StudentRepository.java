package com.college.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.management.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Finds students by exact department name
    List<Student> findByDepartment(String department);

    // Finds students by name containing a string (e.g., "Arj" finds "Arjun")
    List<Student> findByNameContainingIgnoreCase(String name);
}