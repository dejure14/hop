package logic.database;

import model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {

    public List<Category> getCategories() throws SQLException;
    public void insert(Category category);

}
