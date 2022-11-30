package com.example.demo;

import com.example.demo.student.model.Student;
import com.example.demo.student.service.StudentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void before() {
        Student student = new Student("yewon", "computer-system");
        Student student2 = new Student("hayeon", "computer-system");
        studentService.save(student);
        studentService.save(student2);
    }

    @Test
    @DisplayName("API 성공 테스트")
    public void Api_200() throws Exception {
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("학생 조회 성공"))
                .andExpect(jsonPath("$.data.name").value("yewon"))
                .andExpect(jsonPath("$.data.dept").value("computer-system"))
                .andDo(print());
    }

    @Test
    @DisplayName("API 실패 테스트")
    public void Api_400() throws Exception {
        mockMvc.perform(get("/students/99"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("학생 조회 실패"))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());

    }
}
