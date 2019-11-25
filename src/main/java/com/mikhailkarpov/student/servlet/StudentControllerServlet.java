package com.mikhailkarpov.student.servlet;

import com.mikhailkarpov.student.dao.JdbcStudentDao;
import com.mikhailkarpov.student.dao.StudentDao;
import com.mikhailkarpov.student.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentControllerServlet extends HttpServlet {

    private StudentDao studentDao;

    @Override
    public void init() throws ServletException {
        studentDao = JdbcStudentDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDao.getAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
