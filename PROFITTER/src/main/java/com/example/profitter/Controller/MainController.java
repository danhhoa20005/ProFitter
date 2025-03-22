package com.example.profitter.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

import static com.example.profitter.Main.primaryStage;

public class MainController {
    @FXML
    private Button login;
    @FXML
    private Button register;

    @FXML
    void loginAction(ActionEvent event) {
        try {
            // Tạo Stage mới
            Stage mainStage = new Stage();
            mainStage.setTitle("PROFITTER");
            // Load file FXML và tạo Scene
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

            // Đóng Stage hiện tại (cửa sổ đăng nhập)
            Stage currentStage = (Stage) login.getScene().getWindow();
            //currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Thêm dấu đóng ngoặc nhọn cho loginAction

    @FXML
    void registerAction(ActionEvent event) {
        try {
            // Tạo Stage mới
            Stage RegisterStage = new Stage();
            RegisterStage.setTitle("PROFITTER");

            // Load file FXML và tạo Scene (sửa thành Register.fxml)
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene scene = new Scene(root);
            RegisterStage.setScene(scene);
            RegisterStage.show();

            // Đóng Stage hiện tại
            Stage currentStage = (Stage) register.getScene().getWindow();
            //currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// -- đã xong đăng ký và lưu thông tin
