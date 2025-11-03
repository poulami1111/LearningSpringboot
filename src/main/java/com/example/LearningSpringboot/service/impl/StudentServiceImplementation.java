package com.example.LearningSpringboot.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.LearningSpringboot.dto.AddStudentRequestDto;
import com.example.LearningSpringboot.dto.StudentDto;
import com.example.LearningSpringboot.entity.Student;
import com.example.LearningSpringboot.repository.StudentRepository;
import com.example.LearningSpringboot.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        log.info("Fetching all students...");
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        log.info("Fetching student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        log.info("Creating new student: {}", addStudentRequestDto.getName());
        Student student = modelMapper.map(addStudentRequestDto, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        log.info("Updating student with ID: {}", id);
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + id));

        existing.setName(studentDto.getName());
        existing.setEmail(studentDto.getEmail());

        Student updated = studentRepository.save(existing);
        return modelMapper.map(updated, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Deleting student with ID: {}", id);
        if (!studentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
