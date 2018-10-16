package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.controllers.util.CompareCategories;
import com.justinluoma.adlister.controllers.util.Json;
import com.justinluoma.adlister.controllers.util.ValidateCategories;
import com.justinluoma.adlister.dao.DaoFactory;
import com.justinluoma.adlister.models.Ad;
import com.justinluoma.adlister.models.AdCategory;
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
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "UserAdServlet", urlPatterns = "/profile/ad/*")
public class UserAdServlet extends HttpServlet {
    private static Ad extractAd(String path, HttpServletResponse response) throws IOException {
        long adID;
        try {
            adID = Long.parseLong(path.substring(1));
            return DaoFactory.getAdsDao().getFromID(adID);
        } catch (NumberFormatException e) {
            response.sendRedirect("/ads");
        } catch (SQLException e) {
            response.sendRedirect("/404");
        }
        return null;
    }

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
            String path = request.getPathInfo();
            if (path == null || path.substring(1).length() < 1) {
                response.sendRedirect("/profile/ads");
                return;
            }

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");

            Ad ad = extractAd(path, response);
            if (ad == null) return;

            if (ad.createdBy() != user.id()) {
                response.sendRedirect("/403");
                return;
            }

            if (title == null & description == null & categories.length < 1) {
                out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors"}, "nothing to update")));
                out.flush();
                return;
            }

            List<Long> newCatIDs;
            try {
                newCatIDs = Stream.of(categories).map(Long::valueOf).collect(Collectors.toList());
            } catch (NumberFormatException e) {
                out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors"}, "invalid categories")));
                out.flush();
                return;
            }

            if (!ValidateCategories.validate(newCatIDs)) {
                out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors"}, "invalid categories")));
                out.flush();
                return;
            }

            List<AdCategory> ac = DaoFactory.getAdCategoriesDao().byAdID(ad.id());
            List<String> catStringIDs = ac.stream().map(c -> c.categoryID().toString()).collect(Collectors.toList());


            if (ad.title().equals(title) & ad.description().equals(description) & CompareCategories.compare(catStringIDs, categories)) {
                out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors"}, "nothing to update")));
                out.flush();
            } else {
                if ((!ad.title().equals(title) && title != null) || (!ad.description().equals(description) && description != null)) {
                    Ad updateAd = new Ad();
                    if (!ad.title().equals(title)) {
                        updateAd.title(title);
                    }
                    if (!ad.description().equals(description)) {
                        updateAd.description(description);
                    }
                    DaoFactory.getAdsDao().update(ad.id(), updateAd);
                }
                List<Long> acIDs = ac.stream().map(AdCategory::categoryID).collect(Collectors.toList());
                if (!CompareCategories.compare(catStringIDs, categories)) {
                    DaoFactory.getAdsDao().updateCategories(ad.id(), acIDs, newCatIDs);
                }
                session.setAttribute("editing", false);
                out.println(DaoFactory.gson.toJson(Json.gen(new String[] {"errors"}, false)));
                out.flush();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String path = request.getPathInfo();
        if (path == null || path.substring(1).length() < 1) {
            response.sendRedirect("/profile/ads");
            return;
        }

        Ad ad = extractAd(path, response);
        if (ad == null) return;

        if (user == null) {
            session.setAttribute("redirect", "/profile/ad/" + ad.id());
            response.sendRedirect("/ads");
            return;
        } else
            session.setAttribute("logged_in", true);

        if (ad.createdBy() != user.id()) {
            response.sendRedirect("/403");
            return;
        }

        session.setAttribute("editing", request.getHeader("referer").endsWith("/profile/ads"));

        session.setAttribute("ad", ad);

        List<Category> categories = DaoFactory.getCategoriesDao().all();
        session.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);
    }
}
