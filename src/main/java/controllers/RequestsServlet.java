package controllers;

import daos.ContactRequestDAO;
import models.ContactRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/contactus/requests")
public class RequestsServlet extends HttpServlet {

    private final ContactRequestDAO requestDao = new ContactRequestDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ContactRequest> activeRequests = new ArrayList<>();
        List<ContactRequest> archivedRequests = new ArrayList<>();

        try {
            for (ContactRequest contactRequest : requestDao.getAllRequests()) {
                if ("ARCHIVED".equals(contactRequest.getStatus())) {
                    archivedRequests.add(contactRequest);
                } else {
                    activeRequests.add(contactRequest);
                }
            }
        } 
        catch (Exception e) {
            throw new ServletException("Could not load requests", e);
        }

        request.setAttribute("activeRequests", activeRequests);
        request.setAttribute("archivedRequests", archivedRequests);
        request.getRequestDispatcher("/WEB-INF/views/admin/requests.jsp").forward(request, response);
    }
}