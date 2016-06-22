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

@WebServlet("/delete")
public class Delete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user.getRoleId() == 1 && (!user.getUsername().equals(req.getParameter("username")))){
            UserDao userDao = new UserDao(DataConnection.getConnection());
            try {
                user = userDao.getUserByUsername(req.getParameter("username"));
                userDao.delete(user);
            } catch (SQLException e) {
                System.out.println("Не удалось найти данного пользователя!");
            } finally {
                req.getRequestDispatcher("/edit").forward(req, resp);
            }
        } else {
            PrintWriter pw = resp.getWriter();
            pw.print("Данная операция невозможна!");
        }
    }
}
