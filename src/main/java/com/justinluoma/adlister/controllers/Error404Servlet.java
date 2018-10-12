package com.justinluoma.adlister.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Error404Servlet", urlPatterns = "/404")
public class Error404Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null)
            session.setAttribute("logged_in", true);
        else
            request.getSession().setAttribute("logged_in", false);

        request.getRequestDispatcher("/WEB-INF/errors/404.jsp").forward(request, response);
    }
}
