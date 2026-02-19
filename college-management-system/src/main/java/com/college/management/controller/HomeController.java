package com.college.management.controller;

import com.college.management.model.Student;
import com.college.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. Dashboard / Home
    @GetMapping("/")
    public String dashboard() {
        return "index";
    }

    // 2. Student Ledger (With Search and Filter Logic)
    @GetMapping("/students")
    public String listStudents(@RequestParam(name = "keyword", required = false) String keyword,
                               @RequestParam(name = "dept", required = false) String dept,
                               Model model) {
        List<Student> students;

        if (dept != null && !dept.isEmpty()) {
            // This handles the "Filter CS" click
            students = studentRepository.findByDepartment(dept);
        } else if (keyword != null && !keyword.isEmpty()) {
            // This handles the Search Bar input
            students = studentRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            // Default: Show everyone
            students = studentRepository.findAll();
        }

        model.addAttribute("students", students);
        return "students";
    }

    // 3. Show Add Student Form
    @GetMapping("/add-student")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // 4. Save Student (Redirects to Ledger)
    @PostMapping("/save-student")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    // 5. Delete Student
    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
    
    // 6. Edit Student (Optional logic if you have an edit-form.html)
    @GetMapping("/edit-student/{id}")
    public String showEditForm(@PathVariable(value = "id") long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow();
        model.addAttribute("student", student);
        return "add-student"; // Re-uses the add-student form for editing
    }
}