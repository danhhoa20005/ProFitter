package com.example.profitter;

import javafx.fxml.Initializable;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Users implements Serializable {
    // thông tin người dùng
    private String name;
    private String email;
    private String userName;
    private String password;

    public Users(String name, String email, String userName, String password) {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }



}
