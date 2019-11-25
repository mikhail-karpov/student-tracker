package com.mikhailkarpov.student.dao;

import com.mikhailkarpov.student.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDao implements StudentDao {

    private static  JdbcStudentDao instance;

    public static JdbcStudentDao getInstance() {
        try {
            if (instance == null) {
                instance = new JdbcStudentDao();
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private JdbcStudentDao() {}

    // StudentDao implementation ---------------------------------------------------------------------------------------

    @Override
    public void add(Student student) {

    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public void edit(Student student) {

    }

    @Override
    public Student get(Long id) {
        return null;
    }

    @Override
    public List<Student> getAll() throws DaoException {
        List<Student> students = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM student")) {

            while (resultSet.next()) {
                Student student = map(resultSet);
                students.add(student);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return students;
    }

    // Helpers ----------------------------------------------------------------------------------------------------
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "root";
    private static final String PASSWORD = "rootpassword";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private Student map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");

        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        return student;
    }
}
