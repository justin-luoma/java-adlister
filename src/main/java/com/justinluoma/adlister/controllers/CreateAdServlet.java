package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.controllers.util.InvalidAdException;
import com.justinluoma.adlister.controllers.util.Json;
import com.justinluoma.adlister.controllers.util.ValidateAd;
import com.justinluoma.adlister.controllers.util.ValidateCategories;
import com.justinluoma.adlister.dao.DaoFactory;
import com.justinluoma.adlister.models.Ad;
import com.justinluoma.adlister.models.Category;
import com.justinluoma.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CreateAdServlet", urlPatterns = "/create")
public class CreateAdServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            session.setAttribute("redirect", "/create");
            response.sendRedirect("/login");
        } else {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String[] categories = request.getParameterValues("categories");

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");

            boolean valid;
            try {
                valid = ValidateAd.validate(title, description, categories);
            } catch (InvalidAdException e) {
                valid = false;
                out.println(e.getMessage());
                out.flush();
            }

            valid = ValidateCategories.validate(categories) && valid;

            if (valid) {
                long adID = DaoFactory.getAdsDao().insert(
                        new Ad(
                                user.id(),
                                title,
                                description)
                );
                for(String category: categories) {
                    try {
                        long catID = Long.valueOf(category);
                        DaoFactory.getAdCategoriesDao().insert(adID, catID);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Error parsing category id", e);
                    }
                }
                out.println(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "success"}, false, adID)));
                out.flush();
            } else {
                out.println(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "success"}, true)));
                out.flush();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null) {
            session.setAttribute("logged_in", false);
            session.setAttribute("redirect", "/create");
            response.sendRedirect("/login");
        } else {
            session.setAttribute("logged_in", true);
            User user = (User)session.getAttribute("user");
            List<Category> categories = DaoFactory.getCategoriesDao().all();
            session.setAttribute("categories", categories);

            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
        }
    }
}
