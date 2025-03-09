package com.example.profitter;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void showAlert(String mes, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        // thiết lập nội dung hộp thông báo
        alert.setTitle(switch(alertType)
        {
            case ERROR -> "Error";          // Nếu là lỗi, đặt tiêu đề "Error"
            case INFORMATION -> "Information"; // Nếu là thông tin, đặt tiêu đề "Information"
            case WARNING -> "Warning";      // Nếu là cảnh báo, đặt tiêu đề "Warning"
            case CONFIRMATION -> "Confirmation"; // Nếu là xác nhận, đặt tiêu đề "Confirmation"
            default -> ""; // Nếu không thuộc loại nào, để tiêu đề trống
        });
        // Loại bỏ tiêu đề phụ để hộp thoại chỉ hiển thị nội dung chính
        alert.setHeaderText(null);

        // Thiết lập nội dung của hộp thoại theo thông điệp truyền vào
        alert.setContentText(mes);

        // Hiển thị hộp thoại và đợi người dùng đóng nó trước khi tiếp tục chương trình
        alert.showAndWait();

    }
}
