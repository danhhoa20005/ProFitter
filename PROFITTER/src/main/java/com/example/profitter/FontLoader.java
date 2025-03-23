package com.example.profitter;

import javafx.scene.text.Font;
import java.io.File;
import java.net.MalformedURLException;

public class FontLoader {
    public static void loadAllFonts() {
        File fontFolder = new File("C:\\CODE\\PROFITTER\\src\\main\\resources\\font");

        if (fontFolder.exists() && fontFolder.isDirectory()) {
            File[] fontFiles = fontFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".ttf") || name.toLowerCase().endsWith(".otf"));

            if (fontFiles != null) {
                for (File fontFile : fontFiles) {
                    try {
                        Font.loadFont(fontFile.toURI().toURL().toExternalForm(), 12);
                        System.out.println("Đã nạp font: " + fontFile.getName());
                    } catch (MalformedURLException e) {
                        System.err.println("Lỗi khi nạp font: " + fontFile.getName());
                    }
                }
            }
        } else {
            System.err.println("Thư mục font không tồn tại!");
        }
    }
}
