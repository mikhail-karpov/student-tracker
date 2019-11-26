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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null)
            command = "LIST";

        switch (command) {
            case "ADD":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");

                Student student = new Student();
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setEmail(email);

                studentDao.add(student);
                doGet(request, response);
                break;
            case "LIST":
                doGet(request, response);
                break;
        }
    }
}
