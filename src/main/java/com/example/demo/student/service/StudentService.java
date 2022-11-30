package com.example.demo.student.service;

import com.example.demo.student.model.Student;

import java.util.Optional;

public interface StudentService {

    void save(Student student);

    Optional<Student> find(Long id);
}