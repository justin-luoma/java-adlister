package com.justinluoma.adlister.controllers;

import com.justinluoma.adlister.controllers.util.Json;
import com.justinluoma.adlister.controllers.util.Validators;
import com.justinluoma.adlister.dao.DaoFactory;
import com.justinluoma.adlister.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            response.sendError(401);
        } else if (request.getParameter("data") == null) {
            response.sendError(400);
        } else {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            String type = request.getParameter("data");
            if (type.equals("username") && request.getParameter("username") != null) {
                String username = request.getParameter("username");
                if (username.isEmpty()) {
                    out.println(DaoFactory.gson.toJson(Json.gen(new String[]{
                            "errors", "username"},
                            true, "username is invalid")));
                    out.flush();
                } else {
                    try {
                        DaoFactory.getUsersDao().updateUsername(user.id(), username);
                    } catch (SQLException e) {
                        if (e.getMessage().matches(".*(?=Duplicate).*(?=username).*")) {
                            out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors", "username"},
                                    true, "username already taken")));
                            out.flush();
                            return;
                        } else {
                            response.sendError(500);
                            throw new RuntimeException(e);
                        }
                    }
                    session.setAttribute("user", DaoFactory.getUsersDao().getByID(user.id()));
                    out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors"},
                            false)));
                    out.flush();
                }
            } else if (type.equals("email") && request.getParameter("email") != null) {
                String email = request.getParameter("email");
                if (email.isEmpty() || !email.matches(Validators.emailPattern.toString())) {
                    out.println(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "email"},
                            true, "email is invalid")));
                    out.flush();
                } else {
                    try {
                        DaoFactory.getUsersDao().updateEmail(user.id(), email);
                    } catch (SQLException e) {
                        if (e.getMessage().matches(".*(?=Duplicate).*(?=email).*")) {
                            out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors", "email"},
                                    true, "email already is use")));
                            out.flush();
                            return;
                        } else {
                            response.sendError(500);
                            throw new RuntimeException(e);
                        }
                    }
                    session.setAttribute("user", DaoFactory.getUsersDao().getByID(user.id()));
                    out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors"},
                            false)));
                    out.flush();
                }
            } else if (type.equals("password") && request.getParameter("password") != null && request.getParameter("new") != null) {
                char[] password = request.getParameter("password").toCharArray();
                char[] newPassword = request.getParameter("new").toCharArray();
                Matcher matcher = Validators.passwordPattern.matcher(new String(newPassword));
                if (newPassword.length < 1) {
                    out.println(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "password"},
                            true, "password invalid")));
                    out.flush();
                } else if (!matcher.matches()) {
                    out.println(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "password"},
                            true, "password doesn't meet complexity requirements")));
                    out.flush();
                    matcher = null;
                } else {
                    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
                    try {
                        if (!argon2.verify(user.password(), password)) {
                            out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors", "passwordCurrent"},
                                    true, "password incorrect")));
                            out.flush();
                        } else {
                            String hash = argon2.hash(63, 65536, 4, newPassword);
                            DaoFactory.getUsersDao().updatePassword(user.id(), hash);
                            session.setAttribute("user", DaoFactory.getUsersDao().getByID(user.id()));
                            out.println(DaoFactory.gson.toJson(Json.gen(new String[]{"errors"},
                                    false)));
                            out.flush();
                        }
                    } catch (SQLException e) {
                        response.sendError(500);
                        throw new RuntimeException(e);
                    } finally {
                        matcher = null;
                        argon2.wipeArray(password);
                        argon2.wipeArray(newPassword);
                    }
                }
            } else
                response.sendError(400);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null) {
            session.setAttribute("logged_in", false);
            session.setAttribute("redirect", "/profile");
            response.sendRedirect("/login");
        } else {
            session.setAttribute("logged_in", true);
            User user = (User)session.getAttribute("user");
            session.setAttribute("ads", DaoFactory.getAdsDao().users(user.id()));
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
        }
    }
}
