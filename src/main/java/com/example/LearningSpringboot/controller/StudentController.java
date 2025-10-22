package com.example.LearningSpringboot.controller;

import org.springframework.web.bind.annotation.*;


import java.util.List;


import com.example.LearningSpringboot.dto.StudentDto;

import com.example.LearningSpringboot.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    

    // ✅ GET all students
    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ✅ GET student by ID (path variable)
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        // In real app, you'd map entity to DTO, for now returning mock data
        return studentService.getStudentById(id);
    }
}
