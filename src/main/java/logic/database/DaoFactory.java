package logic.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

    public Connection getConnection() throws SQLException;
    public StoreDao getStoreDao(Connection connection);


}
