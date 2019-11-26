package com.mikhailkarpov.student.dao;

import com.mikhailkarpov.student.model.Student;

import java.util.List;

public interface StudentDao {

    void add(Student student);
    void delete(Long id);
    void edit(Student student);
    Student get(Long id);
    List<Student> getAll() throws DaoException;
}
