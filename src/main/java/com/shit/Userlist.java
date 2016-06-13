package com.shit;



import com.mysql.cj.api.Session;
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
import java.util.Iterator;
import java.util.List;

@WebServlet("/userlist")
public class Userlist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserDao userDao = new UserDao(DataConnection.getConnection());
        try {
            req.setAttribute("list", userDao.getAll());
            req.getRequestDispatcher("list.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao(DataConnection.getConnection());


        try {
            List<User> list = userDao.getAll();
            Iterator<User> iterator = list.iterator();

            while (iterator.hasNext()) {
                User user;
                user = iterator.next();
                System.out.println(user.getUsername() + " " + user.getLastName() + " " + user.getFirstName() + " " + user.getCreationDate() );}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
