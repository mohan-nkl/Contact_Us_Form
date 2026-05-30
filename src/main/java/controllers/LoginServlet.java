package controllers;

import daos.AdminDAO;
import models.Admin;
import utils.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/login")
public class LoginServlet extends HttpServlet {

    private final AdminDAO adminDao = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        request.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean loginOk = false;
        try {
            Admin admin = adminDao.findAdminByUsername(username);
            if (admin != null && PasswordUtil.verifyPassword(password, admin.getHashedPassword())) {
                loginOk = true;
            }
        } 
        catch (Exception e) {
            loginOk = false;
        }

        if (loginOk) {
        	
            HttpSession session = request.getSession();
            session.setAttribute("adminUser", username);
            response.sendRedirect(request.getContextPath() + "/admin/contactus/requests");
        } 
        else {
        	
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(request, response);
        }
    }
}