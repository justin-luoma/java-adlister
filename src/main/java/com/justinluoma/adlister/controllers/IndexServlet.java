package com.justinluoma.adlister.controllers;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "IndexServlet", urlPatterns = "/")
public class IndexServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null)
            session.setAttribute("logged_in", true);
        else {
            request.getSession().setAttribute("logged_in", false);
            session.setAttribute("redirect", "/");
        }

        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

}
