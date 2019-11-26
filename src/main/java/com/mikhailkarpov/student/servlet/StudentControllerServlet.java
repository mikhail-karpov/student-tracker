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
        listStudents(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDao.getAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null)
            command = "DEFAULT";

        switch (command) {
            case "ADD":
                Student student = getStudentFromRequest(request);
                studentDao.add(student);
                listStudents(request, response);
                break;
            case "DELETE":
                String idString = request.getParameter("studentId");
                Long id = Long.parseLong(idString);
                studentDao.delete(id);
                listStudents(request, response);
                break;
            case "EDIT":
                editStudent(request);
                listStudents(request, response);
                break;
            case "LOAD":
                loadStudent(request, response);
                break;
            default:
                listStudents(request, response);
                break;
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

    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("studentId");
        Long id = Long.parseLong(idString);
        Student student = studentDao.get(id);

        request.setAttribute("student", student);
        request.getRequestDispatcher("edit-student.jsp").forward(request, response);
    }

    private void editStudent(HttpServletRequest request) {
        Student student = getStudentFromRequest(request);
        String idString = request.getParameter("studentId");
        Long id = Long.parseLong(idString);
        student.setId(id);
        studentDao.edit(student);
    }
}