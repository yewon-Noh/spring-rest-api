package com.example.demo.student.repository;

import com.example.demo.student.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    private static Map<Long, Student> store = new HashMap<>();
    private static long sequence = 0l;

    public void save(Student student) {
        student.setId(++sequence);
        store.put(student.getId(), student);
    }

    public Optional<Student> findById(Long studentId) {
        return Optional.ofNullable(store.get(studentId));
    }

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }
}