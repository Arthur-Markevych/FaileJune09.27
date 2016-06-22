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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class Welcome extends HttpServlet {

    private String title = "Страница входа";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");



        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user.getId() > 0) {
                req.getRequestDispatcher("userpage.jsp").forward(req, resp);
            } else {
                session.setAttribute("title", title);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

        } finally {
            req.setAttribute("title", title);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }



    }
}
