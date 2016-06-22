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

@WebServlet("/edituser")
public class EditUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        PrintWriter pw = resp.getWriter();

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (req.getParameter("oldpassword").equals(user.getPassword())) {
                UserDao userDao = new UserDao(DataConnection.getConnection());
                user.setFirstName(req.getParameter("firstname"));
                user.setLastName(req.getParameter("lastname"));
                user.setPassword(req.getParameter("password"));
                try {
                    userDao.update(user);
                } catch (SQLException e) {
                    System.out.println("При изменении данных произошла ошибка!");
                } finally {
                    session.setAttribute("user", user);
                    req.getRequestDispatcher("/userpage.jsp").forward(req, resp);
                }
            } else {
                pw.print("Старый пароль введён не верно!");
            }

        } else {

            pw.print("Невозможная операция!");
        }
    }
}
