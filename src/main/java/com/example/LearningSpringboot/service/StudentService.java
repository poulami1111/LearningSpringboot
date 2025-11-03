package com.example.LearningSpringboot.service;

import java.util.List;

import com.example.LearningSpringboot.dto.AddStudentRequestDto;
import com.example.LearningSpringboot.dto.StudentDto;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    StudentDto updateStudent(Long id, StudentDto studentDto);

    void deleteStudent(Long id);
}
