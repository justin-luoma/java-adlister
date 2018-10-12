package com.justinluoma.adlister.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "ThemeServlet", urlPatterns = "/theme")
public class ThemeServlet extends HttpServlet {
    private static final String[] themes = {
            "cerulean",
            "cosmo",
            "cyborg",
            "darkly",
            "flatly",
            "journal",
            "litera",
            "lumen",
            "lux",
            "materia",
            "minty",
            "pulse",
            "sandstone",
            "simplex",
            "solar",
            "slate",
            "spacelab",
            "superhero",
            "united",
            "yeti"
    };
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String theme = request.getParameter("theme");
        String redirect = session.getAttribute("redirect") == null ? "/" : (String)session.getAttribute("redirect");
        if (theme == null || theme.isEmpty()) {
            session.setAttribute("theme", "default");
        } else if (Arrays.asList(themes).contains(theme)) {
            session.setAttribute("theme", theme);
            response.sendRedirect(redirect);
        } else {
            session.setAttribute("theme", "default");
            response.sendRedirect(redirect);
        }
    }
}
