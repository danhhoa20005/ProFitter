package com.example.profitter.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.profitter.Main.primaryStage;

public class khoController {

    @FXML
    private Button back;

    @FXML
    private GridPane khoGrid;

    private List<Image> danhSachAnh = new ArrayList<>();

    @FXML
    void backAction(ActionEvent event) {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

            // Lấy Stage hiện tại từ button (login)
            Stage stage = (Stage) primaryStage.getScene().getWindow();

            // Cập nhật root của Scene, dùng chung Stage
            stage.getScene().setRoot(root);
            Stage loginStage = (Stage) back.getScene().getWindow();
            loginStage.close(); // Đóng cửa sổ login

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Image> danhSachKho = new ArrayList<>();
    private List<Image> danhSachTrend = new ArrayList<>();

    private void loadImagesForKhoGrid(String directoryPath) {
        danhSachKho.clear();
        danhSachKho.addAll(loadImagesFromDirectory(directoryPath));
        capNhatKhoGrid();
    }

    private void loadImagesForTrendGrid(String directoryPath) {
        danhSachTrend.clear();
        danhSachTrend.addAll(loadImagesFromDirectory(directoryPath));
        capNhatTrendGrid();
    }

    private List<Image> loadImagesFromDirectory(String directoryPath) {
        List<Image> images = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (isImageFile(file)) {
                    images.add(new Image(file.toURI().toString()));
                }
            }
        } else {
            System.out.println("Thư mục rỗng hoặc không tồn tại: " + directoryPath);
        }
        return images;
    }

    private boolean isImageFile(File file) {
        String tenTep = file.getName().toLowerCase();
        return tenTep.endsWith(".jpg") || tenTep.endsWith(".png") || tenTep.endsWith(".jpeg");
    }

    private void capNhatKhoGrid() {
        capNhatGrid(khoGrid, danhSachKho, 3, 2); // 3 cột × 2 hàng
    }

    private void capNhatTrendGrid() {
        capNhatGrid(trendgrid, danhSachTrend, 3, 1); // 3 cột × 1 hàng
    }

    private void capNhatGrid(GridPane grid, List<Image> danhSachAnh, int soCot, int soHang) {
        if (grid != null) {
            grid.getChildren().clear();

            int col = 0, row = 0;
            for (Image image : danhSachAnh) {
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(250);
                imageView.setFitHeight(250);
                imageView.setPreserveRatio(true);

                GridPane.setRowIndex(imageView, row);
                GridPane.setColumnIndex(imageView, col);
                grid.getChildren().add(imageView);

                col++;
                if (col >= soCot) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    @FXML
    public void initialize() {
        loadImagesForKhoGrid("khoPhoto");
        loadImagesForTrendGrid("trendPhoto");
    }

    @FXML
    private GridPane trendgrid;
}
