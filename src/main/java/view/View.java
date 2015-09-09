package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import logic.database.CategoryDao;
import logic.database.DaoFactory;
import logic.database.PurchaseDao;
import logic.database.StoreDao;
import logic.database.mysql.MySQLCategoryDao;
import logic.database.mysql.MySQLDaoFactory;
import logic.database.mysql.MySQLPurchaseDao;
import logic.database.mysql.MySQLStoreDao;
import model.Category;
import model.Store;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class View extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //----------MENU----------

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem menuItemPurchase = new MenuItem("New Purchase");
        MenuItem menuItemStore = new MenuItem("New Store");
        MenuItem menuItemCategory = new MenuItem("New Category");
        MenuItem menuItemExit = new MenuItem("Exit");
        menuFile.getItems().addAll(menuItemPurchase, menuItemStore, menuItemCategory, menuItemExit);

        Menu menuHelp = new Menu("Help");
        menuBar.getMenus().addAll(menuFile, menuHelp);

        //----------TOOLBAR----------

        ToolBar toolBar = new ToolBar();
        Button buttonPurchase = new Button();
        Button buttonStore = new Button();
        Button buttonCategory = new Button();
        buttonPurchase.setGraphic(new ImageView("/pictures/purchase.png"));
        buttonStore.setGraphic(new ImageView("/pictures/store.png"));
        buttonCategory.setGraphic(new ImageView("/pictures/category.png"));
        toolBar.getItems().addAll(buttonPurchase, buttonStore, buttonCategory);

        buttonStore.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent actionEvent) {
                Stage stage = new Stage();
                try {
                    new StoreWindows().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //----------WORKSPACE----------

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0, 25, 25, 0));

        Label storeLabel = new Label("Store: ");
        storeLabel.setId("simpleLabel");
        gridPane.add(storeLabel, 0, 1);

        final ComboBox<String> stores = new ComboBox<String>();
        gridPane.add(stores, 1, 1);

        Label categoryLabel = new Label("Category: ");
        categoryLabel.setId("simpleLabel");
        gridPane.add(categoryLabel, 2, 1);

        final ComboBox<String> categories = new ComboBox<String>();
        gridPane.add(categories, 3, 1);

        Button bCount = new Button("Count");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(bCount);
        gridPane.add(hBox, 4, 1);

        FlowPane flowPane1 = new FlowPane();
        flowPane1.setAlignment(Pos.CENTER);
        flowPane1.setPadding(new Insets(10, 25, 25, 10));

        final Text spentTitle = new Text("SPENT: ");
        spentTitle.setId("headline");
        flowPane1.getChildren().add(spentTitle);

        //----------DATABASE----------

        Label statusLabel = new Label();
        DaoFactory daoFactory = new MySQLDaoFactory();
        try{
            Connection connection = daoFactory.getConnection();
            statusLabel.setText("Database connection: success");
            StoreDao storeDao = new MySQLStoreDao(connection);
            ArrayList<Store> storeList = (ArrayList)storeDao.getStores();
            for (Store store: storeList){
                stores.getItems().add(store.getName());
            }
            CategoryDao categoryDao = new MySQLCategoryDao(connection);
            ArrayList<Category> categoryList = (ArrayList)categoryDao.getCategories();
            for (Category category: categoryList){
                categories.getItems().add(category.getTitle());
            }
        }catch(SQLException e){
            statusLabel.setText("Database connection: failed");
        }
        FlowPane footer = new FlowPane();

        footer.setPadding(new Insets(10, 10, 10, 10));
        footer.getChildren().add(statusLabel);

        bCount.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent actionEvent) {
                try {
                    PurchaseDao purchaseDao = new MySQLPurchaseDao(new MySQLDaoFactory().getConnection());
                    spentTitle.setText("SPENT: " + purchaseDao.showSpent(stores.getValue(), categories.getValue()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //----------VIEW----------

        Scene scene = new Scene(new VBox(), 800, 600);
        scene.getStylesheets().add("css/style.css");
        ((VBox)scene.getRoot()).getChildren().addAll(menuBar, toolBar, gridPane, flowPane1, footer);

        stage.setScene(scene);
        stage.setTitle("Cash Organizer");
        stage.getIcons().add(new Image("pictures/icon.png"));
        stage.show();
    }


}
