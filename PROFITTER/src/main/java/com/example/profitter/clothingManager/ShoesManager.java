package com.example.profitter.clothingManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.File;

public class ShoesManager {
    // Danh sách chứa các hình ảnh của giày
    private ObservableList<Image> shoesPhoto = FXCollections.observableArrayList();

    // GridPane để hiển thị danh sách giày
    private GridPane shoesGridPane;

    // ImageView để hiển thị giày khi được chọn
    private ImageView shoesModel;

    // Thiết lập GridPane để hiển thị danh sách giày
    public void setShoesGridPane(GridPane shoesGridPane) {
        this.shoesGridPane = shoesGridPane;
    }

    // Thiết lập ImageView để hiển thị giày được chọn
    public void setShoesModel(ImageView shoesModel) {
        this.shoesModel = shoesModel;
    }

    // Thêm ảnh giày vào danh sách và cập nhật giao diện
    public void addShoes(File file) {
        // Tạo đối tượng hình ảnh từ file
        Image image = new Image(file.toURI().toString());

        // Thêm ảnh vào danh sách giày
        shoesPhoto.add(image);

        // Cập nhật giao diện để hiển thị ảnh mới thêm
        updateShoesGrid();
    }

    // Cập nhật giao diện GridPane để hiển thị danh sách giày
    public void updateShoesGrid() {
        if (shoesGridPane != null) {
            // Xóa tất cả các phần tử hiện có trong GridPane
            shoesGridPane.getChildren().clear();

            int col = 0, row = 0; // Biến đếm để xác định vị trí đặt ảnh trong lưới

            // Duyệt qua danh sách ảnh giày để hiển thị lên GridPane
            for (Image image : shoesPhoto) {
                // Tạo một ImageView mới để hiển thị ảnh giày
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200); // Đặt chiều rộng ảnh
                imageView.setFitHeight(200); // Đặt chiều cao ảnh

                // Bắt sự kiện khi người dùng nhấp vào ảnh giày
                imageView.setOnMouseClicked(event -> {
                    // Khi ảnh được chọn, cập nhật ảnh hiển thị chính (shoesModel)
                    if (shoesModel != null) shoesModel.setImage(image);
                });

                // Thêm ảnh vào GridPane tại vị trí cột `col`, hàng `row`
                shoesGridPane.add(imageView, col, row);

                // Di chuyển sang cột tiếp theo
                col++;

                // Nếu đã đủ 3 cột, chuyển xuống hàng tiếp theo
                if (col >= 3) {
                    col = 0; // Reset về cột đầu tiên
                    row++; // Tăng hàng lên

                    // Giới hạn tối đa 2 hàng (3x2 = 6 ảnh)
                    if (row >= 2) break;
                }
            }
        }
    }
}
