package com.example.profitter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class UserFile {
    private static final String FILE_PATH ="users.dat" ;
    public static boolean saveUsers(List<Users> users)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(users); // Ghi danh sách người dùng vào tệp dưới dạng đối tượng nhị phân
            return true;
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý lỗi khi ghi tệp
            return false;
        }
    }
    public static List<Users> loadUsers() {
        List<Users> users = new ArrayList<>(); // Khởi tạo danh sách rỗng để tránh NullPointerException
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = ois.readObject(); // Đọc đối tượng từ tệp
            if (obj instanceof List) {
                users = (List<Users>) obj; // Ép kiểu nếu dữ liệu hợp lệ
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Xử lý lỗi khi đọc tệp hoặc khi không tìm thấy lớp User
        }
        return users; // Trả về danh sách người dùng (có thể rỗng nếu có lỗi)
    }
}
