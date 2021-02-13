package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @GetMapping
  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void addNewStudent(Student student) {
    Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
    if (studentByEmail.isPresent()) {
      throw new IllegalStateException("email taken");
    }
    studentRepository.save(student);
  }

  public void deleteStudent(Long id) {
    boolean exists = studentRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("student doesnt exists");
    }
    studentRepository.deleteById(id);
  }

  @Transactional
  public void updateStudent(Student student) {
    boolean exists = studentRepository.existsById(student.getId());
    if (!exists) {
      throw new IllegalStateException("student doesnt exists");
    }
    studentRepository.save(student);
  }

}
