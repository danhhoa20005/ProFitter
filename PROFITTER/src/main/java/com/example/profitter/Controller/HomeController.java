package com.example.profitter.Controller;

import com.example.profitter.UserAuth;
import com.example.profitter.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.profitter.Main.primaryStage;


public class HomeController {

    @FXML
    private Button exit;

    @FXML
    private Button kho;

    @FXML
    private Button thudo;

    @FXML
    void exitAction(ActionEvent event) {
        try {

            Stage mainStage = new Stage();
            mainStage.setTitle("PROFITTER");

            // Load file FXML và tạo Scene
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/profitter/Main.fxml"));
            Stage stage = (Stage) thudo.getScene().getWindow();

            // Cập nhật root của Scene (dùng chung Stage, không tạo cái mới)
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    private Label nameuser;

    public void initialize() {
        if (nameuser != null) {
            nameuser.setText("HI, " + UserAuth.getName());
        } else {
            System.out.println("Lỗi: nameuser chưa được khởi tạo!");
        }
    }

    @FXML
    void khoAction(ActionEvent event) {
        Stage mainStage = new Stage();
        mainStage.setTitle("PROFITTER");

        // Load file FXML và tạo Scene
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("kho.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) thudo.getScene().getWindow();

        // Cập nhật root của Scene (dùng chung Stage, không tạo cái mới)
        stage.getScene().setRoot(root);



    }
    @FXML
    void thudoAction(ActionEvent event) {
        try {

            Stage mainStage = new Stage();
            mainStage.setTitle("PROFITTER");

            // Load file FXML và tạo Scene
            Parent root = FXMLLoader.load(getClass().getResource("Thudo.fxml"));
            Stage stage = (Stage) thudo.getScene().getWindow();

            // Cập nhật root của Scene (dùng chung Stage, không tạo cái mới)
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
