package logic.database.mysql;

import logic.database.PurchaseDao;
import model.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLPurchaseDao implements PurchaseDao {

    private final Connection connection;

    public MySQLPurchaseDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Purchase purchase) {

    }

    public double showSpent(String store, String category) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("select id from store where (name = ?)");
        ps.setString(1, store);
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        int storeId = resultSet.getInt("id");
        ps = connection.prepareStatement("select id from category where (title = ?)");
        ps.setString(1, category);
        resultSet = ps.executeQuery();
        resultSet.next();
        int categoryId = resultSet.getInt("id");
        ps = connection.prepareStatement("select price from purchase where (category = ?) and (store = ?);");
        ps.setInt(1, categoryId);
        ps.setInt(2, storeId);
        resultSet = ps.executeQuery();
        double costs = 0.0;
        while (resultSet.next()){
            costs = costs + resultSet.getDouble("price");
        }
        return costs;
    }
}
