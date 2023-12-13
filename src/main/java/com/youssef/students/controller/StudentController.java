package com.youssef.students.controller;

import com.youssef.students.models.Student;
import com.youssef.students.models.repo.StudentRepository;
import com.youssef.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(studentService.addStudent(student))
                .toUri();
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.status(OK).body(students);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Student> getStudentById(@PathVariable("id") Long id){

        Student students = studentService.getStudentById(id);
        return ResponseEntity.status(OK).body(students);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
