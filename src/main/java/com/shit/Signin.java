package com.shit;


import com.shit.databasesource.DataConnection;
import com.shit.databasesource.dao.UserDao;
import com.shit.databasesource.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/signin")
public class Signin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        req.setAttribute("title", "Панель администратора");
        UserDao userDao = new UserDao(DataConnection.getConnection());
        User user;



        try {
            if (session.getAttribute("user") == null) {
                user = userDao.passwordVerify(req.getParameter("txtusername"), req.getParameter("txtpassword"));
                if (user != null) {
                    session.setAttribute("user", user);
                    if (1 == user.getRoleId())
                        req.getRequestDispatcher("admin.jsp").forward(req, resp);
                    else
                        req.getRequestDispatcher("homepage.jsp").forward(req, resp);
                } else {
                    pw.print("Wrong username or password!");
                }
            } else {
                req.getRequestDispatcher("userpage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            pw.print("User not found!");
        }

    }
}
