package com.example.demo;

import com.example.demo.restapi.Message;
import com.example.demo.restapi.RestResponse;
import com.example.demo.student.model.Student;
import com.example.demo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ApiController {

    private final StudentService studentService;

    @Autowired
    public ApiController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<RestResponse> find(@PathVariable Long id) {
        RestResponse<Object> restResponse = new RestResponse<>();
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(Message.READ_STUDENTS.label())
                    .result(student)
                    .build();
        } else {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(Message.NOT_FOUND_STUDENTS.label())
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }
}
