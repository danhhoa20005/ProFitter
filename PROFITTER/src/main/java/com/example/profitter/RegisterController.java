package com.example.profitter;

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
import java.util.List;

public class RegisterController {

    @FXML
    private Button login;

    @FXML
    private TextField gmail;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private TextField userName;

    @FXML
    void LoginAction(ActionEvent event) {
        try {
            Stage loginStage = new Stage();
            loginStage.setTitle("PROFITTER");

            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            loginStage.setScene(scene);
            loginStage.show();

            Stage currentStage = (Stage) login.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerAction(ActionEvent event) {
        String gmail = this.gmail.getText().trim();
        String name = this.name.getText().trim();
        String username = this.userName.getText().trim();
        String password = this.password.getText().trim();
        // Kiểm tra xem có trường nào bị bỏ trống không
        if (gmail.isEmpty() || name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            AlertUtil.showAlert("Vui lòng điền đầy đủ thông tin!", Alert.AlertType.WARNING);
            return;
        }

        // Kiểm tra xem username đã tồn tại chưa
        List<Users> users = UserFile.loadUsers();
        // Tạo user mới và lưu vào danh sách
        Users newUser = new Users(gmail, name, username, password);
        users.add(newUser);

        // Lưu danh sách người dùng
        boolean success = UserFile.saveUsers(users);
        if (success) {
            AlertUtil.showAlert("Đăng ký thành công!", Alert.AlertType.INFORMATION);
        } else {
            AlertUtil.showAlert("Lỗi khi lưu dữ liệu!", Alert.AlertType.ERROR);
        }
    }
}
