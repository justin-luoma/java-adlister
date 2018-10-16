package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.controllers.util.Json;
import com.justinluoma.adlister.controllers.util.Paging;
import com.justinluoma.adlister.controllers.util.Sublist;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdsUserServlet", urlPatterns = "/profile/ads")
public class AdsUserServlet extends HttpServlet {
    private static int resultsPerPage = 10;
    private static final int pagesToShow = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        session.setAttribute("redirect", "/profile/ads");

        if(user != null)
            session.setAttribute("logged_in", true);
        else {
            response.sendRedirect("/login");
            return;
        }

        List<Ad> userAds = DaoFactory.getAdsDao().users(user.id());

        session.setAttribute("userAds", userAds);
        Integer page = (Integer)session.getAttribute("page");
        int pages = (int)Math.ceil((double)userAds.size() / (double)resultsPerPage);
        page = page == null || page > pages ? 1 : page;
        int start = (page - 1) * resultsPerPage;

        List<Ad> pagedResults = userAds.size() != 0 ? userAds.subList(start, (start + resultsPerPage) > userAds.size() ? userAds.size() : start + resultsPerPage) : new ArrayList<>();
//        List<Ad> pagedResults = Sublist.get(userAds, start, resultsPerPage);
        session.setAttribute("pagedResults", pagedResults);
        session.setAttribute("pages", pages);
        session.setAttribute("page", page);
        int last;
        int first;
        if (pages < pagesToShow) {
            last = pages;
            first = 1;
        } else {
            last = Paging.doPaging(page, pagesToShow, pages);
            first = last - pagesToShow + 1;
        }
        session.setAttribute("last", last);
        session.setAttribute("first", first);

        request.getRequestDispatcher("/WEB-INF/ads/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            session.setAttribute("redirect", "/profile/ads");
            response.sendRedirect("/login");
            return;
        }
        if (request.getParameter("adID") == null) {
            int page;
            try {
                page = Integer.valueOf(request.getParameter("page"));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Error parsing page", e);
            }

            session.setAttribute("page", page);
        } else {
            long adID;
            try {
                adID = Long.valueOf(request.getParameter("adID"));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Error parsing adID", e);
            }

            Boolean success = DaoFactory.getAdsDao().delete(user.id(), adID);
            if (success) {
                session.setAttribute("reload", true);
            } else
                session.setAttribute("reload", false);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();

            out.print(DaoFactory.gson.toJson(Json.gen(new String[]{"status"}, success)));
            out.flush();
        }
    }
}
