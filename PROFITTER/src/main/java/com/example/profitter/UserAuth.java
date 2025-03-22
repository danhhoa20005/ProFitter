package com.example.profitter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserAuth {  // Thêm lớp chứa phương thức

    // Kiểm tra tài khoản đăng nhập
    public static String name = "";
    public static boolean checkAccount(String username, String password) {
        List<Users> users = UserFile.loadUsers(); // Tải danh sách người dùng
        String hashedPassword = hashPassword(password); // Băm mật khẩu nhập vào

        // Duyệt danh sách để kiểm tra tài khoản
        for (Users user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                name = user.getName();
                return true; // Đăng nhập thành công
            }
        }
        return false; // Sai tài khoản hoặc mật khẩu
    }

    // Hàm băm mật khẩu bằng SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi: Không tìm thấy thuật toán mã hóa!", e);
        }
    }
    public static String getName() { // Getter để lấy giá trị
        return name;
    }
}
