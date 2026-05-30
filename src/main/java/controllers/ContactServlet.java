package controllers;

import daos.ContactRequestDAO;
import models.ContactRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/contactus")
public class ContactServlet extends HttpServlet {

    private static final Pattern EMAIL =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[\\w.-]+$");

    private final ContactRequestDAO requestDao = new ContactRequestDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        request.getRequestDispatcher("/WEB-INF/views/contactus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fullName = trim(request.getParameter("fullName"));
        String email = trim(request.getParameter("email"));
        String message = trim(request.getParameter("message"));

        String error = validate(fullName, email, message);

        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/views/contactus.jsp").forward(request, response);
            return;
        }

        try {
            requestDao.saveRequest(new ContactRequest(fullName, email, message));
        } 
        catch (Exception e) {
            request.setAttribute("error", "Something went wrong. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/contactus.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/contactus?success=1");
    }

    private String validate(String fullName, String email, String message) {
        if (isBlank(fullName) || isBlank(email) || isBlank(message)) {
            return "All fields are required.";
        }

        if (!EMAIL.matcher(email).matches()) {
            return "The email format is incorrect.";
        }

        return null;
    }

    private boolean isBlank(String s) {
        return s == null || s.isEmpty();
    }

    private String trim(String s) {
        return s == null ? null : s.trim();
    }
}