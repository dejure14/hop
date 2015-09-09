package logic.database.mysql;

import logic.database.DaoFactory;
import logic.database.StoreDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDaoFactory implements DaoFactory{

    private String url = "jdbc:mysql://localhost:3306/db";
    private String user = "root";
    private String password = "root";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public StoreDao getStoreDao(Connection connection) {
        return new MySQLStoreDao(connection);
    }
}
