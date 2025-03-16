package com.example.profitter.clothingManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.File;

public class ShirtManager {
    // Danh sách lưu trữ các hình ảnh áo thun
    private ObservableList<Image> shirtPhoto = FXCollections.observableArrayList();

    // GridPane để hiển thị danh sách áo thun
    private GridPane shirtGridPane;

    // ImageView hiển thị áo thun được chọn
    private ImageView shirtModel;

    // Gán GridPane từ bên ngoài
    public void setShirtGridPane(GridPane shirtGridPane) {
        this.shirtGridPane = shirtGridPane;
    }

    // Gán ImageView hiển thị áo thun được chọn từ bên ngoài
    public void setShirtModel(ImageView shirtModel) {
        this.shirtModel = shirtModel;
    }

    // Thêm một hình ảnh áo thun vào danh sách
    public void addShirt(File file) {
        // Tạo đối tượng Image từ tập tin
        Image image = new Image(file.toURI().toString());

        // Thêm ảnh vào danh sách
        shirtPhoto.add(image);

        // Cập nhật giao diện hiển thị
        updateShirtGrid();
    }

    // Cập nhật giao diện GridPane để hiển thị danh sách áo thun
    public void updateShirtGrid() {
        if (shirtGridPane != null) { // Kiểm tra GridPane có tồn tại không
            shirtGridPane.getChildren().clear(); // Xóa tất cả phần tử cũ để làm mới

            int col = 0, row = 0; // Biến đếm số cột và số hàng
            for (Image image : shirtPhoto) {
                // Tạo ImageView để hiển thị ảnh
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100); // Đặt chiều rộng ảnh là 200px
                imageView.setFitHeight(100); // Đặt chiều cao ảnh là 200px

                // Gán sự kiện khi nhấp chuột vào ảnh
                imageView.setOnMouseClicked(event -> {
                    if (shirtModel != null) {
                        // Khi nhấp vào ảnh, ảnh đó sẽ hiển thị lên ImageView shirtModel
                        shirtModel.setImage(image);
                    }
                });

                // Đặt vị trí của ảnh trong GridPane
                GridPane.setRowIndex(imageView, row);  // Đặt hàng (row)
                GridPane.setColumnIndex(imageView, col); // Đặt cột (col)
                shirtGridPane.getChildren().add(imageView); // Thêm vào GridPane

                // Di chuyển sang cột tiếp theo
                col++;

                // Nếu đã đạt đến 3 cột, xuống hàng mới
                if (col >= 3) {
                    col = 0;
                    row++;

                    // Giới hạn tối đa 3 hàng (3x3 = 9 ảnh)
                    if (row >= 3) break;
                }
            }
        }
    }
}
