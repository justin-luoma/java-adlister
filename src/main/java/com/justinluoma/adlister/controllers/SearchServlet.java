package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.controllers.util.Json;
import com.justinluoma.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    private String search(String query) {
        if (query == null || query.isEmpty()) {
             return DaoFactory.gson.toJson(Json.gen(new String[]{"error"}, (Object)"invalid request"));
        } else {
            return DaoFactory.gson.toJson(DaoFactory.getAdsDao().search(query, 0));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("search");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        out.print(search(query));
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("search");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        out.print(search(query));
        out.flush();
    }
}
