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
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

            // Đóng Stage hiện tại (cửa sổ đăng nhập)
            Stage currentStage = (Stage) exit.getScene().getWindow();
            currentStage.close();

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
