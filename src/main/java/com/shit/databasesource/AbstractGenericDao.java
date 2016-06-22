package com.shit.databasesource;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractGenericDao<T> {

    protected Connection connection;

    public abstract String getCreateQuery();
    public abstract String getSelectQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();
    public abstract String getIdQuery();



    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void preparedStatementForInsert(PreparedStatement statement, T object) throws SQLException;
    protected abstract void preparedStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    public abstract void persist(T object) throws SQLException;

    public abstract void  delete(T object) throws SQLException;

    public abstract void update(T object) throws SQLException;

    public abstract T getById(int id) throws SQLException;

    public abstract T dataVerify(String username, String password) throws SQLException;

    public abstract T passwordVerify(String username, String password) throws SQLException;

    public abstract T getUserByUsername(String username) throws SQLException;

    public abstract List<T> getAll() throws SQLException;









    protected java.sql.Timestamp convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Timestamp(date.getTime());
    }

    public AbstractGenericDao(Connection connection) {
        this.connection = connection;
    }


}

