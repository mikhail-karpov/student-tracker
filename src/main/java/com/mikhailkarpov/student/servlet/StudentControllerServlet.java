package com.mikhailkarpov.student.servlet;

import com.mikhailkarpov.student.dao.DaoException;
import com.mikhailkarpov.student.dao.JdbcStudentDao;
import com.mikhailkarpov.student.dao.StudentDao;
import com.mikhailkarpov.student.model.Student;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentControllerServlet extends HttpServlet {

    @Resource(name = "jdbc/studentdb")
    private DataSource dataSource;
    private StudentDao studentDao;

    @Override
    public void init() throws ServletException {
        studentDao = new JdbcStudentDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listStudents(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Student> students = studentDao.getAll();
            request.setAttribute("students", students);
            request.getRequestDispatcher("list-students.jsp").forward(request, response);

        } catch (DaoException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null)
            command = "DEFAULT";

        if (command.equals("ADD")) {
            addStudent(request);
            listStudents(request, response);
        }
        else if (command.equals("DELETE")) {
            deleteStudent(request);
            listStudents(request, response);
        }
        else if (command.equals("EDIT")) {
            editStudent(request);
            listStudents(request, response);
        }
        else if (command.equals("LOAD")) {
            Student student = loadStudentFromDB(request);
            request.setAttribute("student", student);
            request.getRequestDispatcher("edit-student.jsp").forward(request, response);
        }
        else {
            listStudents(request, response);
        }
    }

    private void addStudent(HttpServletRequest request) throws ServletException {
        try {
            Student student = getStudentFromRequest(request);
            studentDao.add(student);

        } catch (DaoException e) {
            throw new ServletException(e);
        }
    }

    private Student getStudentFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        return student;
    }

    private void deleteStudent(HttpServletRequest request) throws ServletException {
        try {
            String idString = request.getParameter("studentId");
            Long id = Long.parseLong(idString);
            studentDao.delete(id);

        } catch (DaoException | IllegalArgumentException e) {
            throw new ServletException(e);
        }
    }

    private Student loadStudentFromDB(HttpServletRequest request) throws ServletException {
        try {
            String idString = request.getParameter("studentId");
            Long id = Long.parseLong(idString);
            return studentDao.get(id);

        } catch (DaoException | IllegalArgumentException e) {
            throw new ServletException(e);
        }
    }

    private void editStudent(HttpServletRequest request) throws ServletException {
        try {
            Student student = getStudentFromRequest(request);
            String idString = request.getParameter("studentId");
            Long id = Long.parseLong(idString);
            student.setId(id);
            studentDao.edit(student);

        } catch (DaoException | IllegalArgumentException e) {
            throw new ServletException(e);
        }
    }
}