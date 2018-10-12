package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.dao.DaoFactory;
import com.justinluoma.adlister.models.Ad;
import com.justinluoma.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "AdServlet", urlPatterns = "/ad/*")
public class AdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.substring(1).length() < 1) {
            response.sendRedirect("/ads");
            return;
        }

        long adID;
        Ad ad;
        User user;
        try {
            adID = Long.parseLong(path.substring(1));
            ad = DaoFactory.getAdsDao().getFromID(adID);
            user = DaoFactory.getUsersDao().getByID(ad.createdBy());
        } catch (NumberFormatException e) {
            response.sendRedirect("/ads");
            return;
        } catch (SQLException e) {
            response.sendRedirect("/404");
            return;
        }

        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null)
            session.setAttribute("logged_in", true);
        else {
            request.getSession().setAttribute("logged_in", false);
            session.setAttribute("redirect", "/ad/" + adID);
        }

        session.setAttribute("ad", ad);
        session.setAttribute("user_info", user.username());

        request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
    }
}
