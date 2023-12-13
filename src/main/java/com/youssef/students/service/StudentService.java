package com.youssef.students.service;

import com.youssef.students.models.Student;

import java.util.List;

public interface StudentService {

    Long addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    void deleteStudentById(Long id);

}
