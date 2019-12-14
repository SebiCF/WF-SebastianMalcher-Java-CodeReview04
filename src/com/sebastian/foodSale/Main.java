package com.sebastian.foodSale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("foodSale.fxml"));
        primaryStage.setTitle("Food Action Sale");
        Scene mainScene = new Scene(root, 1100, 500);
        primaryStage.setScene(mainScene);
        mainScene.getStylesheets().add("mainWindow.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        try {
            ProductData.getInstance().loadProductData();
        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }
}
