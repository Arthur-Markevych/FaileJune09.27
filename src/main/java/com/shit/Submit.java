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
import java.util.Calendar;


@WebServlet("/submit")
public class Submit extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

        User user = new User();

        user.setUsername(req.getParameter("txtusername"));
        user.setPassword(req.getParameter("txtpassword"));
        user.setFirstName(req.getParameter("txtfirstname"));
        user.setLastName(req.getParameter("txtlastname"));
        user.setCreationDate(ourJavaTimestampObject);

        try {
            UserDao userDao = new UserDao(DataConnection.getConnection());
            userDao.persist(user);


            session.setAttribute("firstname", user.getFirstName());
            session.setAttribute("lastname", user.getLastName());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("password", user.getPassword());

            req.getRequestDispatcher("homepage.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
