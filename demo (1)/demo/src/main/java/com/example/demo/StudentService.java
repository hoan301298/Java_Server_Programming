package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.getReferenceById(id);
    }

    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student added!";
    }

    public String deleteStudentById(int id) {
        studentRepository.deleteById(id);
        return "Student deleted!";
    }
}
