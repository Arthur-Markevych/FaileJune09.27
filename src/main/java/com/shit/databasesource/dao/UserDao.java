package com.shit.databasesource.dao;


import com.shit.databasesource.AbstractGenericDao;
import com.shit.databasesource.DataConnection;
import com.shit.databasesource.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserDao extends AbstractGenericDao<User> {

    private Connection connection;

    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO userdata.users (username, password, first_name, last_name, creation_date, role_id) \n" +
                "VALUES (?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, username, password, first_name, last_name, creation_date, role_id FROM userdata.users";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE userdata.users \n" +
                "SET username = ?, password = ?, first_name = ?, last_name = ?, creation_date = ?, role_id = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM userdata.users WHERE id = ?;";
    }

    @Override
    public String getIdQuery() {
        return "SELECT id FROM userdata.users";
    }



    @Override
    protected void preparedStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        Timestamp sqlDate = convert(object.getCreationDate());

        statement.setString(1,object.getUsername());
        statement.setString(2,object.getPassword());
        statement.setString(3,object.getFirstName());
        statement.setString(4,object.getLastName());
        statement.setTimestamp(5, sqlDate);
        statement.setInt(6, object.getRoleId());

    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        Timestamp ourJavaTimestampObject = new Timestamp(calendar.getTime().getTime());

        statement.setString(1, object.getUsername());
        statement.setString(2, object.getPassword());
        statement.setString(3, object.getFirstName());
        statement.setString(4, object.getLastName());
        statement.setTimestamp(5, ourJavaTimestampObject);
        statement.setInt(6, object.getRoleId());
        statement.setInt(7, object.getId());

    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        while(rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setRoleId(rs.getInt("role_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setCreationDate(rs.getTimestamp("creation_date"));
            result.add(user);
        }
        return result;
    }

    @Override
    public void persist(User object) throws SQLException {
        String SQL = getCreateQuery();

        try (PreparedStatement statement = DataConnection.getConnection().prepareStatement(SQL)){
            preparedStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("Found more than one record " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User object) throws SQLException {
        String SQL = getDeleteQuery();

        try (PreparedStatement statement = DataConnection.getConnection().prepareStatement(SQL)){
            statement.setObject(1, object.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("Found more than one record " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(User object) throws SQLException {
        String SQL = getUpdateQuery();
        try (PreparedStatement statement = DataConnection.getConnection().prepareStatement(SQL)){
            preparedStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("Found more than one record " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User passwordVerify(String username, String password) throws SQLException {
        User user = null;
        String SQL = getSelectQuery();
        SQL += " WHERE username = ?";
        try (PreparedStatement statement = DataConnection.getConnection().prepareStatement(SQL)) {
            statement.setObject(1, username);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
                if (rs.getString("password").equals(password)) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setCreationDate(rs.getTimestamp("creation_date"));
                } else {
                    user = null;
                }
        } catch (Exception e){
            System.out.println("Username not found in the database!");
        }
        return user;
    }

    @Override
    public User dataVerify(String username, String password) throws SQLException {
        User user = new User();
        String SQL = getIdQuery();
        SQL += " WHERE username = ? AND password = ?";
        try (PreparedStatement statement = DataConnection.getConnection().prepareStatement(SQL)) {
            statement.setObject(1, username);
            statement.setObject(2, password);
            ResultSet rs = statement.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setRoleId(rs.getInt("role_id"));
            user.setCreationDate(rs.getTimestamp("creation_date"));
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        User user = new User();
        String SQL = getSelectQuery();
        SQL += " WHERE username = ?";
        try (PreparedStatement statement = DataConnection.getConnection().prepareStatement(SQL)) {
            statement.setObject(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCreationDate(rs.getTimestamp("creation_date"));
            }

        } catch (Exception e) {
            throw new SQLException(e);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> list;
        String SQL = getSelectQuery();
        try (PreparedStatement statement = DataConnection.getConnection().prepareStatement(SQL)){
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public User getById(int id) throws SQLException {
        List<User> list;
        String SQl = getSelectQuery();
        SQl += " WHERE id = ?";
        try (PreparedStatement statement = DataConnection.getConnection().prepareStatement(SQl)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        if (list == null || list.size() == 0) {
            throw new SQLException("Row with id " + id + " not found.");
        }
        if (list.size() > 1) {
            throw new SQLException("Received more than one record.");
        }
        return list.iterator().next();
    }



}
