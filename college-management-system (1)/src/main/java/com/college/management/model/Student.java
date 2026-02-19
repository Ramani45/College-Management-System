package com.college.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    private String email;

    // 1. DEFAULT CONSTRUCTOR
    // Required by JPA (it uses this to create empty objects before filling them with data)
    public Student() {
    }

    // 2. CUSTOM CONSTRUCTOR
    // This allows you to do: new Student("Name", "Dept", "Email") 
    // This is the one fixing your error in DataInitializer!
    public Student(String name, String department, String email) {
        this.name = name;
        this.department = department;
        this.email = email;
    }

    // 3. GETTERS AND SETTERS
    // Required so Thymeleaf and Hibernate can read/write the data
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}