package com.example.LearningSpringboot.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.LearningSpringboot.dto.StudentDto;
import com.example.LearningSpringboot.entity.Student;
import com.example.LearningSpringboot.repository.StudentRepository;
import com.example.LearningSpringboot.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {

        // Implementation logic here
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = students.stream()
            .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
            .toList();
           
        return studentDtoList; // temporary empty list
    }

    @Override
    public StudentDto getStudentById(Long id) {
       return null; // temporary null
    }
}

