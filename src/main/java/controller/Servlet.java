package controller;

import jbdc.lophoc.JDBCLophoc;
import jbdc.lophoc.JDBCLophocIntreface;
import jbdc.student.JDBCStudent;
import jbdc.student.JDBCStudentInterface;
import model.Lophoc;
import model.Student;
import jbdc.*;
import model.ViewAll;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "servlet", urlPatterns = "/students")
public class Servlet extends HttpServlet {
        JDBCStudentInterface jdbcStudentInterface = new JDBCStudent();
        JDBCLophocIntreface jdbcLophocIntreface = new JDBCLophoc();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "create":
                showFormCreate(request,response);
                break;
            case "view":
                showFormView(request,response);



            default:
                listStudent(request, response);
                break;
        }
    }

    private void showFormView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
       ViewAll viewAll = this.jdbcStudentInterface.viewByID(id);
        request.setAttribute("viewAll", viewAll);
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/view.jsp");
        dispatcher.forward(request,response);
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        List<Lophoc> lophocList = jdbcLophocIntreface.selectAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/create.jsp");
        request.setAttribute("student", student);
        request.setAttribute("lophocList",lophocList);
        dispatcher.forward(request,response);

    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ViewAll> viewAllList =  this.jdbcStudentInterface.viewAll();
        request.setAttribute("viewAllList", viewAllList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/list.jsp");
        dispatcher.forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "create":
                createStudent(request,response);
                break;
        }
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String[] lophocString = request.getParameterValues("lophocList");
        int[] lophoc = new int[lophocString.length];
        for (int i = 0; i < lophocString.length; i++) {
            lophoc[i] = Integer.parseInt(lophocString[i]);
        }
        Student student = new Student(name,dob,address);
        this.jdbcStudentInterface.create(student,lophoc);
        request.setAttribute("message", "Create success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/create.jsp");
        dispatcher.forward(request,response);
    }
}
