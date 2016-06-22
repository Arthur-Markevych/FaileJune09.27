package com.shit.databasesource;


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
import java.util.List;

@WebServlet("/edit")
public class Edit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        HttpSession session = req.getSession();
        UserDao userDao = new UserDao(DataConnection.getConnection());
        User user = (User) session.getAttribute("user");

        if (1 == user.getRoleId()) {
            try {
                List<User> list = userDao.getAll();
                req.setAttribute("list", list);
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            printWriter.print("Для просмотра данного контента, нужно зайти под учетной записью администратора!");
        }
    }
}
