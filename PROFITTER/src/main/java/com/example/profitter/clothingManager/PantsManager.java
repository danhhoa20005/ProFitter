package com.example.profitter.clothingManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.File;

public class PantsManager {
    // Danh sách chứa các hình ảnh của quần
    private ObservableList<Image> pantsPhoto = FXCollections.observableArrayList();

    // GridPane để hiển thị danh sách quần dưới dạng lưới
    private GridPane pantsGridPane;

    // ImageView để hiển thị quần khi người dùng chọn một ảnh
    private ImageView pantmodel;

    // Phương thức thiết lập GridPane dùng để hiển thị danh sách quần
    public void setPantsGridPane(GridPane pantsGridPane) {
        this.pantsGridPane = pantsGridPane;
    }

    // Phương thức thiết lập ImageView dùng để hiển thị quần được chọn
    public void setPantModel(ImageView pantModel) {
        this.pantmodel = pantModel;
    }

    // Phương thức thêm một ảnh quần mới vào danh sách và cập nhật giao diện
    public void addPants(File file) {
        // Tạo đối tượng hình ảnh từ file
        Image image = new Image(file.toURI().toString());

        // Thêm ảnh vào danh sách quần
        pantsPhoto.add(image);

        // Cập nhật giao diện để hiển thị ảnh mới thêm
        updatePantsGrid();
    }

    // Phương thức cập nhật giao diện của GridPane để hiển thị danh sách quần
    public void updatePantsGrid() {
        if (pantsGridPane != null) {
            // Xóa tất cả các phần tử hiện có trong GridPane
            pantsGridPane.getChildren().clear();

            int col = 0, row = 0; // Biến đếm để xác định vị trí đặt ảnh trong lưới

            // Duyệt qua danh sách ảnh quần để hiển thị lên GridPane
            for (Image image : pantsPhoto) {
                // Tạo một ImageView mới để hiển thị ảnh quần
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100); // Đặt chiều rộng ảnh
                imageView.setFitHeight(100); // Đặt chiều cao ảnh

                // Bắt sự kiện khi người dùng nhấp vào ảnh quần
                imageView.setOnMouseClicked(event -> {
                    // Khi ảnh được chọn, cập nhật ảnh hiển thị chính (pantmodel)
                    if (pantmodel != null) pantmodel.setImage(image);
                });

                // Thêm ảnh vào GridPane tại vị trí cột `col`, hàng `row`
                pantsGridPane.add(imageView, col, row);

                // Di chuyển sang cột tiếp theo
                col++;




                // Nếu đã đủ 3 cột, chuyển xuống hàng tiếp theo
                if (col >= 3) {
                    col = 0; // Reset về cột đầu tiên
                    row++; // Tăng hàng lên

                    // Giới hạn tối đa 3 hàng (3x3 = 9 ảnh)
                    if (row >= 3) break;
                }
            }
        }
    }
}
