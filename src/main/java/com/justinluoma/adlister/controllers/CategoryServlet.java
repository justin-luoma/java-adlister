package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.controllers.util.Json;
import com.justinluoma.adlister.dao.DaoFactory;
import com.justinluoma.adlister.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/categories")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<Category> categories = DaoFactory.getCategoriesDao().all();
        if (categories == null || categories.size() == 0) {
            response.sendError(500);
        } else {
            out.println(DaoFactory.gson.toJson(categories));
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<Category> categories = DaoFactory.getCategoriesDao().all();
        if (categories == null || categories.size() == 0) {
            response.sendError(500);
        } else {
            out.println(DaoFactory.gson.toJson(categories));
            out.flush();
        }
    }
}
