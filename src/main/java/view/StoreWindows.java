package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.database.DaoFactory;
import logic.database.StoreDao;
import logic.database.mysql.MySQLDaoFactory;
import logic.database.mysql.MySQLStoreDao;
import model.Store;

import java.sql.Connection;
import java.sql.SQLException;

public class StoreWindows extends Application {



    @Override
    public void start(final Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label storeName = new Label("Store Name:");
        grid.add(storeName, 0, 1);

        final TextField storeTextField = new TextField();
        grid.add(storeTextField, 1, 1);

        Button bInsert = new Button("Insert");
        grid.add(bInsert, 0, 2);

        Button bCancel = new Button("Cancel");
        grid.add(bCancel, 1, 2);

        bInsert.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent actionEvent) {
                Store store = new Store(storeTextField.getText());
                try {
                    StoreDao storeDao = new MySQLStoreDao(new MySQLDaoFactory().getConnection());
                    storeDao.insert(store);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        bInsert.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        stage.setScene(scene);
        stage.setTitle("New Store");
        stage.show();
    }
}
