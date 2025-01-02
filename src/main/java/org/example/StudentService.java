package org.example;

import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    // Constructor to inject DAO
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    // Fetch all students
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    // Find a student by ID
    public Student getStudentById(Long id) {
        return studentDAO.findById(id);
    }

    // Create or update a student
    public void createOrUpdateStudent(Student student) {
        studentDAO.saveOrUpdate(student);
    }

    // Delete a student
    public void deleteStudent(Long id) {
        Student student = studentDAO.findById(id);
        if (student != null) {
            studentDAO.delete(student);
        }
    }
}
