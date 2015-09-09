package logic.database.mysql;

import logic.database.CategoryDao;
import model.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoryDao implements CategoryDao {

    private final Connection connection;

    public MySQLCategoryDao(Connection connection) {
        this.connection = connection;
    }

    public List<Category> getCategories() throws SQLException {
        ArrayList<Category> categoris = new ArrayList<Category>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from category");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            categoris.add(new Category(id, title));
        }
        return categoris;
    }

    public void insert(Category category) {

    }
}
