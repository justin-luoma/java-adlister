package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.controllers.util.Paging;
import com.justinluoma.adlister.dao.DaoFactory;
import com.justinluoma.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdsServlet", urlPatterns = "/ads")
public class AdsServlet extends HttpServlet {
    private static int resultsPerPage = 10;
    private static final int pagesToShow = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null)
            session.setAttribute("logged_in", true);
        else
            request.getSession().setAttribute("logged_in", false);

        session.setAttribute("redirect", "/ads");

        List<Ad> ads = DaoFactory.getAdsDao().all();

        session.setAttribute("ads", ads);

        Integer page = (Integer)session.getAttribute("page");
        int pages = (int)Math.ceil((double)(ads.size() / resultsPerPage));
        page = page == null || page > pages ? 1 : page;
        int start = (page - 1) * resultsPerPage;

        session.setAttribute("pagedResults", ads.subList(start,
                (start + resultsPerPage) > ads.size() ? ads.size() : start + resultsPerPage));
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

        request.getRequestDispatcher("/WEB-INF/ads/main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int page;
        try {
            page = Integer.valueOf(req.getParameter("page"));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing page", e);
        }

        session.setAttribute("page", page);
    }
}
