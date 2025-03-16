package com.example.profitter.Controller;

import com.example.profitter.AlertUtil;
import com.example.profitter.Main;
import com.example.profitter.UserAuth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.profitter.Main.primaryStage;

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
    // nút đăng nhập
    void loginAction(ActionEvent event) {
    String username = userName.getText();
    String userPassword = password.getText();
    if(UserAuth.checkAccount(username, userPassword) != true) {
        AlertUtil.showAlert("Sai tên đăng nhập hoặc mật khẩu!", Alert.AlertType.ERROR);
    }
    else{
        try {
            // Kiểm tra tài khoản
            if(username.equals("admin") && userPassword.equals("admin")) {
                Main.isAdmin = true;
            }

            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

            // Lấy Stage hiện tại từ button (login)
            Stage stage = (Stage) primaryStage.getScene().getWindow();

            // Cập nhật root của Scene, dùng chung Stage
            stage.getScene().setRoot(root);
            Stage loginStage = (Stage) login.getScene().getWindow();
            loginStage.close(); // Đóng cửa sổ login

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    }

    @FXML
    void registerAction(ActionEvent event) {
        try {
            // Load file FXML mới (Register.fxml)
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));

            // Lấy Stage hiện tại từ button (register)
            Stage stage = (Stage) register.getScene().getWindow();

            // Cập nhật root của Scene (dùng chung Stage, không tạo cái mới)
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
