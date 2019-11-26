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
    public void add(Student student) throws IllegalArgumentException, DaoException {
        if (student.getId() != null)
            throw new IllegalArgumentException("Student is already created. Student ID is not null");

        String sql = "INSERT INTO student (first_name, last_name, email) values (?, ?, ?)";

        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.execute();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM student WHERE id=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void edit(Student student) throws IllegalArgumentException, DaoException {
        if (student.getId() == null)
            throw new IllegalArgumentException("Student is not yet created. Student ID is null");

        String sql = "UPDATE student SET first_name=?, last_name=?, email=? WHERE id=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setLong(4, student.getId());
            statement.execute();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Student get(Long id) throws DaoException {
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = map(resultSet);
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return student;
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
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "RuC1jNab";

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