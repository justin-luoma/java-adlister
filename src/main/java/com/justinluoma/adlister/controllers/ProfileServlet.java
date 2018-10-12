package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.dao.DaoFactory;
import com.justinluoma.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.session = request.getSession();
        if(this.session.getAttribute("user") == null) {
            this.session.setAttribute("logged_in", false);
            session.setAttribute("redirect", "/profile");
            response.sendRedirect("/login");
        } else {
            this.session.setAttribute("logged_in", true);
            User user = (User)session.getAttribute("user");
            this.session.setAttribute("ads", DaoFactory.getAdsDao().users(user.id()));
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
        }
    }
}
