package com.youssef.students.service.impl;

import com.youssef.students.exceptions.RecordNotFound;
import com.youssef.students.models.Student;
import com.youssef.students.models.repo.StudentRepository;
import com.youssef.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Long addStudent(Student student) {

        Student saved = studentRepository.save(student);
        return saved.getId();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
            return student.get();
        }else {
            throw new RecordNotFound("Record not found with Id: "+id);
        }
    }

    @Override
    public void deleteStudentById(Long id) {
        Optional<Student> existStudent = studentRepository.findById(id);
        if (existStudent.isPresent()){
            studentRepository.delete(existStudent.get());
        }else {
            throw new RecordNotFound("Record not found with Id: "+id);
        }
    }
}
