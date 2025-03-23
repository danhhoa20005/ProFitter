### PHẦN MỀM PHỐI ĐỒ - PROFITTER
---
#### Mô tả

ProFitter là ứng dụng hỗ trợ phối đồ thông minh, giúp người dùng lựa chọn trang phục phù hợp theo sở thích, phong cách và xu hướng thời trang. Ứng dụng cung cấp các gợi ý phối đồ dựa trên màu sắc, kiểu dáng và tình huống sử dụng.

#### Tác giả
- Nguyễn Danh Hài

---
#### 📚 Nội dung
- [PHẦN MỀM PHỐI ĐỒ - PROFITTER](#phần-mềm-phối-đồ---profitter)
  - [Mô tả](#mô-tả)
  - [Tác giả](#tác-giả)
  - [📚 Nội dung](#-nội-dung)
  - [1. Cấu trúc thư mục](#1-cấu-trúc-thư-mục)
  - [2. Tính năng](#2-tính-năng)
  - [3. Ảnh chụp màn hình](#3-ảnh-chụp-màn-hình)
  - [4. Video demo sản phẩm](#4-video-demo-sản-phẩm)
  - [5. Môi trường](#5-môi-trường)
  - [6. Cài đặt dự án](#6-cài-đặt-dự-án)
---
#### 1. Cấu trúc thư mục
    ProFitter/
    ├── src
    |    └── main
    |        ├── java
    |        |    └── com.example.profitter
    |        |        ├── clothingManager
    |        |        |    ├── PantsManager.java
    |        |        |    ├── ShirtManager.java
    |        |        |    ├── ShoesManager.java
    |        |        ├── Controller
    |        |        |    ├── HomeController.java
    |        |        |    ├── khoController.java
    |        |        |    ├── LoginController.java
    |        |        |    ├── MainController.java
    |        |        |    ├── RegisterController.java
    |        |        |    ├── ThudoController.java
    |        |        ├── AlertUtil.java
    |        |        ├── FontLoader.java
    |        |        ├── Main.java
    |        |        ├── UserAuth.java
    |        |        ├── UserFile.java
    |        |        ├── Users.java
    |        └── resources
    |            ├── com.example.profitter
    |            |    ├── Controller
    |            |    |    ├── Image
    |            |    ├── home.fxml
    |            |    ├── kho.fxml
    |            |    ├── Login.fxml
    |            |    ├── Register.fxml
    |            |    ├── test.fxml
    |            |    ├── Thudo.fxml
    |            ├── java.css
    |            ├── Main.fxml

#### 2. Tính năng
    + Tạo tài khoản, đăng nhập.
    + Lựa chọn trang phục theo danh mục (áo, quần, giày, phụ kiện).
    + Xem và quản lý danh sách trang phục có sẵn.

#### 3. Ảnh chụp màn hình
a) Giao diện trang đầu

![alt text](https://github.com/danhhoa20005/ProFitter/blob/main/photo/Screenshot%202025-03-23%20205040.png)

b/ giao diện đăng nhập đăng ký

![alt text](https://github.com/danhhoa20005/ProFitter/blob/main/photo/Screenshot%202025-03-23%20205052.png)

c) Giao diện bên trong( có thể phát triển thêm)

![alt text](https://github.com/danhhoa20005/ProFitter/blob/main/photo/Screenshot%202025-03-23%20205100.png)

d) giao diện phối đồ

![alt text](https://github.com/danhhoa20005/ProFitter/blob/main/photo/Screenshot%202025-03-23%20205105.png)

e, kho lưu trữ hoặc tham khảo
![alt text](https://github.com/danhhoa20005/ProFitter/blob/main/photo/Screenshot%202025-03-23%20205114.png)
#### 4. Video demo sản phẩm
[https://youtu.be/M0plYF6yOdg](https://youtu.be/rZqHrTrx_jQ)

#### 5. Môi trường
- **Phát triển trên**: IntelliJ IDEA
- **Ngôn ngữ lập trình**: Java (JavaFX)
- **Công cụ hỗ trợ**: Scene Builder
- **Thư viện sử dụng**: JavaFX, JFoenix

#### 6. Cài đặt dự án
- **Clone dự án**:
  ```sh
  git clone https://github.com/your-repo/profitter.git
