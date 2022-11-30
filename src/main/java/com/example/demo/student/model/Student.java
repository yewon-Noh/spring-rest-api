package com.example.demo.student.model;

import lombok.Data;

@Data
public class Student {
    private Long id;
    private String name;
    private String dept;

    public Student(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }
}