package logic.database.mysql;

import logic.database.StoreDao;
import model.Store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLStoreDao implements StoreDao {

    private final Connection connection;

    public MySQLStoreDao(Connection connection) {
        this.connection = connection;
    }

    public List<Store> getStores() throws SQLException{
        ArrayList<Store> stores = new ArrayList<Store>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from store");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            stores.add(new Store(id, name));
        }
        return stores;
    }

    public void insert(Store store) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "insert into store (name) values (\"" + store.getName() + "\"); ";
        statement.execute(query);
    }
}
