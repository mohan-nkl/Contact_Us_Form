package controllers;

import daos.ContactRequestDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/contactus/archive")
public class ArchiveServlet extends HttpServlet {

    private final ContactRequestDAO requestDao = new ContactRequestDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        String newStatus = "archive".equals(action) ? "ARCHIVED" : "ACTIVE";

        try {
            requestDao.updateRequestStatus(id, newStatus);
        } 
        catch (Exception e) {
            throw new IOException("Could not update status", e);
        }

        response.sendRedirect(request.getContextPath() + "/admin/contactus/requests");
    }
}