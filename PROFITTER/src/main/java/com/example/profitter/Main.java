package com.example.profitter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage; // Lưu stage chính để dùng lại
    public static boolean isAdmin = false;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage; // Gán stage chính cho biến tĩnh

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setTitle("PROFITTER");
        stage.setScene(new Scene(root, 950, 650));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
