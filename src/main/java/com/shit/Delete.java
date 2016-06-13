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

@WebServlet("/delete")
public class Delete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user;
        String username = (String) session.getAttribute("username");
        UserDao userDao = new UserDao(DataConnection.getConnection());
        try  {
            user = userDao.getUserByUsername(username);
            userDao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.getAttribute("title");
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
