package com.example.profitter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private TextField userName;

    @FXML
    void loginAction(ActionEvent event) {

    }

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
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
