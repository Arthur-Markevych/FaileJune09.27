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
import java.util.Calendar;


@WebServlet("/update")
public class Update extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String username = req.getParameter("username");

        if (username == null) {
            PrintWriter pw = resp.getWriter();
            pw.print("Error data loading!");
        } else {

            UserDao userDao = new UserDao(DataConnection.getConnection());

            try {
                Calendar calendar = Calendar.getInstance();
                java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

                User user = userDao.getUserByUsername(username);

                user.setFirstName(req.getParameter("txtfirstname"));
                user.setLastName(req.getParameter("txtlastname"));
                user.setCreationDate(ourJavaTimestampObject);
                user.setRoleId(Integer.parseInt(req.getParameter("role")));
                userDao.update(user);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            session.getAttribute("title");
            req.getRequestDispatcher("/edit").forward(req, resp);
        }
    }
}

