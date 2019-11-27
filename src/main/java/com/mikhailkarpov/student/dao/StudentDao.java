package com.mikhailkarpov.student.dao;

import com.mikhailkarpov.student.model.Student;

import java.util.List;

public interface StudentDao {

    void add(Student student) throws DaoException;
    void delete(Long id) throws DaoException;
    void edit(Student student) throws DaoException;
    Student get(Long id) throws DaoException;
    List<Student> getAll() throws DaoException;
}
