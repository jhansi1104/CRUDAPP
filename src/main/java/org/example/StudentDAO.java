package org.example;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

public class StudentDAO extends AbstractDAO<Student> {

    public StudentDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // Create or Update a student
    public void saveOrUpdate(Student student) {
        persist(student); // Save or update the student object
    }

    // Find a student by ID
    public Student findById(Long id) {
        return get(id); // Fetches the student by primary key
    }

    // Retrieve all students
    public List<Student> findAll() throws HibernateException {
        return list((CriteriaQuery<Student>) namedQuery("org.example.Student.findAll"));
    }

    // Delete a student
    public void delete(Student student) {
        if (student!= null) {
            currentSession().delete(student); // Removes the student from the database
        }
    }
}
