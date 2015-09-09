package logic.database;

import model.Store;

import java.sql.SQLException;
import java.util.List;

public interface StoreDao {

    public List<Store> getStores() throws SQLException;
    public void insert(Store store) throws SQLException;

}
