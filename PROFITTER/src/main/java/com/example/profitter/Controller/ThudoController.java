package com.example.profitter.Controller;

import com.example.profitter.Main;
import com.example.profitter.clothingManager.PantsManager;
import com.example.profitter.clothingManager.ShirtManager;
import com.example.profitter.clothingManager.ShoesManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.profitter.Main.isAdmin;
import static com.example.profitter.Main.primaryStage;

public class ThudoController {

    @FXML
    private Button back;

    @FXML
    private GridPane gridPant;

    @FXML
    private GridPane gridShirt;

    @FXML
    private GridPane gridShoe;

    @FXML
    private Button pant;

    @FXML
    private Button shirt;

    @FXML
    private Button shoe;

    @FXML
    private Button save;
    // --------------chuyển các grid quần áo  -------------------------------------------------------------------
    @FXML
    private Button selectedButton; // Nút đang được chọn
    @FXML
    private Pane panePant;

    @FXML
    private Pane paneShoe;


    @FXML
    private Pane paneShirt;

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

    @FXML
    private void showShirt() {
        removeSelectedClass();
        shirt.getStyleClass().add("selected");

        paneShirt.toFront();
    }

    @FXML
    private void showPant() {
        removeSelectedClass();
        pant.getStyleClass().add("selected");

        panePant.toFront();
    }

    @FXML
    private void showShoe() {
        removeSelectedClass();
        shoe.getStyleClass().add("selected");

        paneShoe.toFront();
    }

    @FXML
    void pantAction(ActionEvent event) {
        showPant();
    }

    @FXML
    void shirtAction(ActionEvent event) {
        showShirt();
    }

    @FXML
    void shoeAction(ActionEvent event) {
        showShoe();
    }

    // Hàm xóa lớp "selected" khỏi tất cả các nút trước khi chọn cái mới
    private void removeSelectedClass() {
        pant.getStyleClass().remove("selected");
        shirt.getStyleClass().remove("selected");
        shoe.getStyleClass().remove("selected");
    }

    //----------------------------------- lưu trữ hình ảnh----------------------------------
    @FXML
    private AnchorPane paneImage;

    @FXML
    void saveAction(ActionEvent event) {
        savePhoto();
    }

    private void savePhoto() {
        try {
            WritableImage image = paneImage.snapshot(new SnapshotParameters(), null);
            PixelReader reader = image.getPixelReader();
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    bufferedImage.setRGB(x, y, reader.getArgb(x, y));
                }
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Images", "*.png"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                ImageIO.write(bufferedImage, "png", file);
                showAlert("Ảnh đã được lưu vào: " + file.getAbsolutePath(), Alert.AlertType.INFORMATION);
            } else {
                showAlert("Hủy lưu ảnh.", Alert.AlertType.INFORMATION);
            }
        } catch (IOException e) {
            showAlert("Lỗi khi lưu ảnh.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Thông báo");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private ImageView pantmodel;

    @FXML
    private ImageView shirtmodel;

    @FXML
    private ImageView shoesmodel;
    //------------------------- quản lý grid quần áo----------------------
    private ShirtManager shirtManager;
    private PantsManager pantsManager;
    private ShoesManager shoesManager;

    public void initialize() {
        if (!isAdmin) {
            addPant.setVisible(false);
            addShirt.setVisible(false);
            addShoe.setVisible(false);
        }
        showShirt();
        // Khởi tạo các đối tượng quản lý quần áo
        shirtManager = new ShirtManager();
        shirtManager.setShirtGridPane(gridShirt);
        shirtManager.setShirtModel(shirtmodel);

        pantsManager = new PantsManager();
        pantsManager.setPantsGridPane(gridPant);
        pantsManager.setPantModel(pantmodel);

        shoesManager = new ShoesManager();
        shoesManager.setShoesGridPane(gridShoe);
        shoesManager.setShoesModel(shoesmodel);

        // hàm tải và quản lý

        // Tải ảnh từ thư mục
        loadShirtImagesFromDirectory("ShirtPhoto");
        loadPantImagesFromDirectory("PantPhoto");
        loadShoesImagesFromDirectory("ShoesPhoto");
    }

    private boolean isImageFile(File file) {
        // Lấy tên tập tin từ đối tượng File
        String fileName = file.getName();

        // Lấy phần mở rộng của tập tin (từ dấu "." đến cuối chuỗi)
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        // Kiểm tra xem phần mở rộng có thuộc các định dạng hình ảnh phổ biến không
        return extension.equalsIgnoreCase("png") ||
                extension.equalsIgnoreCase("jpg") ||
                extension.equalsIgnoreCase("jpeg") ||
                extension.equalsIgnoreCase("gif");
    }

    private void setDraggable(ImageView imageView) {
        // Kiểm tra xem imageView có null không để tránh lỗi NullPointerException
        if (imageView != null) {
            // Mảng để lưu tọa độ chuột khi bắt đầu kéo
            final double[] mouseX = new double[1];
            final double[] mouseY = new double[1];

            // Xử lý sự kiện khi người dùng nhấn chuột vào hình ảnh
            imageView.setOnMousePressed(event -> {
                // Lưu vị trí tương đối giữa chuột và hình ảnh khi nhấn
                mouseX[0] = event.getSceneX() - imageView.getTranslateX();
                mouseY[0] = event.getSceneY() - imageView.getTranslateY();

                // Ngăn sự kiện lan ra các thành phần khác
                event.consume();
            });

            // Xử lý sự kiện khi người dùng kéo chuột
            imageView.setOnMouseDragged(event -> {
                // Tính toán vị trí mới của hình ảnh dựa trên vị trí chuột
                double offsetX = event.getSceneX() - mouseX[0];
                double offsetY = event.getSceneY() - mouseY[0];

                // Cập nhật vị trí của hình ảnh
                imageView.setTranslateX(offsetX);
                imageView.setTranslateY(offsetY);
            });
        }
    }


    private void loadShirtImagesFromDirectory(String directoryPath) {
        // Tạo một đối tượng File từ đường dẫn thư mục
        File directory = new File(directoryPath);

        // Lấy danh sách tất cả các tập tin trong thư mục
        File[] files = directory.listFiles();

        // Kiểm tra xem thư mục có chứa tập tin nào không
        if (files != null) {
            // Duyệt qua từng tập tin trong thư mục
            for (File file : files) {
                // Kiểm tra xem tập tin có phải là hình ảnh không
                if (isImageFile(file)) {
                    // Thêm hình ảnh vào quản lý áo thun
                    shirtManager.addShirt(file);

                    // Đặt hình ảnh có thể kéo thả (có thể 'shirtmodel' bị sai ở đây)
                    setDraggable(shirtmodel);
                }
            }
            shirtManager.updateShirtGrid();
        }else {
            System.out.println("Thư mục rỗng hoặc không tồn tại: " + directoryPath);
        }
    }

    private void loadPantImagesFromDirectory(String directoryPath) {
        // Tạo đối tượng File từ đường dẫn thư mục
        File directory = new File(directoryPath);

        // Lấy danh sách tất cả các tập tin trong thư mục
        File[] files = directory.listFiles();

        // Kiểm tra xem thư mục có chứa tập tin nào không
        if (files != null) {
            // Duyệt qua từng tập tin trong thư mục
            for (File file : files) {
                // Kiểm tra xem tập tin có phải là hình ảnh không
                if (isImageFile(file)) {
                    // Thêm hình ảnh vào quản lý quần
                    pantsManager.addPants(file);

                    // Đặt hình ảnh có thể kéo thả (có thể 'pantmodel' bị sai ở đây)
                    setDraggable(pantmodel);
                }
            }
        }
    }

    private void loadShoesImagesFromDirectory(String directoryPath) {
        // Tạo đối tượng File từ đường dẫn thư mục
        File directory = new File(directoryPath);

        // Lấy danh sách tất cả các tập tin trong thư mục
        File[] files = directory.listFiles();

        // Kiểm tra xem thư mục có chứa tập tin nào không
        if (files != null) {
            // Duyệt qua từng tập tin trong thư mục
            for (File file : files) {
                // Kiểm tra xem tập tin có phải là hình ảnh không
                if (isImageFile(file)) {
                    // Thêm hình ảnh vào quản lý giày
                    shoesManager.addShoes(file);

                    // Đặt hình ảnh có thể kéo thả (có thể 'shoesmodel' bị sai ở đây)
                    setDraggable(shoesmodel);
                }
            }
        }

    }
    // thêm quần đồ nếu là admin
    @FXML
    private Button addPant;

    @FXML
    private Button addShirt;

    @FXML
    private Button addShoe;

    public void addShoesAction(ActionEvent event) {
        // Kiểm tra xem người dùng có phải là admin hay không
        if (isAdmin) {
            // Mở hộp thoại chọn file ảnh
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File file = fileChooser.showOpenDialog(null); // Lấy file được chọn

            // Nếu shoesmodel không rỗng, thiết lập khả năng kéo thả cho đối tượng
            if (shoesmodel != null) {
                setDraggable(shoesmodel);
            }

            // Kiểm tra xem người dùng đã chọn file hay chưa
            if (file != null) {
                String sourceImagePath = file.getAbsolutePath(); // Lấy đường dẫn gốc của ảnh
                String destinationDirectory = "ShoesPhoto"; // Thư mục đích để lưu ảnh
                String destinationImagePath = destinationDirectory + File.separator + file.getName();
                File destinationFile = new File(destinationImagePath); // Tạo file đích

                try {
                    // Sao chép ảnh từ thư mục nguồn đến thư mục đích
                    Files.copy(Paths.get(sourceImagePath), destinationFile.toPath());

                    // Hiển thị ảnh vừa chọn trên giao diện
                    Image image = new Image(file.toURI().toString());
                    shoesmodel.setImage(image);

                    // Thêm giày mới vào danh sách quản lý
                    shoesManager.addShoes(file);

                    // Cập nhật lại giao diện hiển thị danh sách giày
                    shoesManager.updateShoesGrid();
                } catch (IOException e) {
                    // Hiển thị thông báo lỗi nếu quá trình sao chép thất bại
                    showAlert("Lỗi khi sao chép ảnh.", Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            // Nếu không phải admin, hiển thị cảnh báo
            showAlert("Bạn cần đăng nhập bằng tài khoản admin để thực hiện thao tác này.", Alert.AlertType.WARNING);
        }
    }// thêm giày
    public void addShirtAction(ActionEvent event) {
        // Kiểm tra xem người dùng có phải là admin hay không
        if (isAdmin) {
            // Mở hộp thoại chọn file ảnh
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Chọn ảnh áo");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File file = fileChooser.showOpenDialog(null); // Lấy file được chọn

            // Nếu shirtModel không rỗng, thiết lập khả năng kéo thả
            if (shirtmodel != null) {
                setDraggable(shirtmodel);
            }

            // Kiểm tra xem người dùng đã chọn file hay chưa
            if (file != null) {
                String sourceImagePath = file.getAbsolutePath(); // Lấy đường dẫn gốc của ảnh
                String destinationDirectory = "ShirtPhoto"; // Thư mục đích để lưu ảnh
                String destinationImagePath = destinationDirectory + File.separator + file.getName();
                File destinationFile = new File(destinationImagePath); // Tạo file đích

                try {
                    // Sao chép ảnh từ thư mục nguồn đến thư mục đích
                    Files.copy(Paths.get(sourceImagePath), destinationFile.toPath());

                    // Hiển thị ảnh vừa chọn trên giao diện
                    Image image = new Image(file.toURI().toString());
                    shirtmodel.setImage(image);

                    // Thêm áo mới vào danh sách quản lý
                    shirtManager.addShirt(file);

                    // Cập nhật lại giao diện hiển thị danh sách áo
                    shirtManager.updateShirtGrid();
                } catch (IOException e) {
                    // Hiển thị thông báo lỗi nếu quá trình sao chép thất bại
                    showAlert("Lỗi khi sao chép ảnh áo.", Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            // Nếu không phải admin, hiển thị cảnh báo
            showAlert("Bạn cần đăng nhập bằng tài khoản admin để thực hiện thao tác này.", Alert.AlertType.WARNING);
        }
    }// thêm áo
    public void addPantAction(ActionEvent event) {
        // Kiểm tra xem người dùng có phải là admin hay không
        if (isAdmin) {
            // Mở hộp thoại chọn file ảnh
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Chọn ảnh quần");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File file = fileChooser.showOpenDialog(null); // Lấy file được chọn

            // Nếu pantModel không rỗng, thiết lập khả năng kéo thả
            if (pantmodel != null) {
                setDraggable(pantmodel);
            }

            // Kiểm tra xem người dùng đã chọn file hay chưa
            if (file != null) {
                String sourceImagePath = file.getAbsolutePath(); // Lấy đường dẫn gốc của ảnh
                String destinationDirectory = "PantPhoto"; // Thư mục đích để lưu ảnh
                String destinationImagePath = destinationDirectory + File.separator + file.getName();
                File destinationFile = new File(destinationImagePath); // Tạo file đích

                try {
                    // Sao chép ảnh từ thư mục nguồn đến thư mục đích
                    Files.copy(Paths.get(sourceImagePath), destinationFile.toPath());

                    // Hiển thị ảnh vừa chọn trên giao diện
                    Image image = new Image(file.toURI().toString());
                    pantmodel.setImage(image);

                    // Thêm quần mới vào danh sách quản lý
                    pantsManager.addPants(file);

                    // Cập nhật lại giao diện hiển thị danh sách quần
                    pantsManager.updatePantsGrid();
                } catch (IOException e) {
                    // Hiển thị thông báo lỗi nếu quá trình sao chép thất bại
                    showAlert("Lỗi khi sao chép ảnh quần.", Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            // Nếu không phải admin, hiển thị cảnh báo
            showAlert("Bạn cần đăng nhập bằng tài khoản admin để thực hiện thao tác này.", Alert.AlertType.WARNING);
        }
    }// thêm quần
    

}
