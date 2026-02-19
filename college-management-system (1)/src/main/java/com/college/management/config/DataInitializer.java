package com.college.management.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.college.management.model.Student;
import com.college.management.repository.StudentRepository;

@Configuration
public class DataInitializer {

    /**
     * This method runs automatically when the Spring Boot application starts.
     * It checks if the database is empty and seeds it with initial data.
     */
    @Bean
    CommandLineRunner initDatabase(StudentRepository repository) {
        return args -> {
            // 1. Check if the database already has records
            // This prevents duplicate data being added every time you restart.
            if (repository.count() == 0) {
                System.out.println(">>> System: Database is empty. Seeding initial student records...");

                // 2. Create a list of 'starter' students
                // This uses the custom constructor we added to Student.java
                repository.saveAll(Arrays.asList(
                    new Student("Arjun Mehta", "Computer Science", "arjun.m@college.edu"),
                    new Student("Sanya Iyer", "Information Technology", "sanya.i@college.edu"),
                    new Student("Kabir Singh", "Mechanical Engineering", "kabir.s@college.edu"),
                    new Student("Anjali Rao", "Computer Science", "anjali.r@college.edu"),
                    new Student("Vikram Das", "Civil Engineering", "vikram.d@college.edu")
                ));

                System.out.println(">>> System: 5 Academic profiles successfully initialized.");
            } else {
                // If data already exists (from a previous session), we skip seeding.
                System.out.println(">>> System: Academic records detected. Skipping auto-initialization.");
            }
        };
    }
}