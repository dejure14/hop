package logic.database;

import model.Purchase;

import java.sql.SQLException;

public interface PurchaseDao {

    public void insert(Purchase purchase);
    public double showSpent(String store, String category) throws SQLException;

}
