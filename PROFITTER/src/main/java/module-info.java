module com.example.profitter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.profitter to javafx.fxml;
    exports com.example.profitter;
    exports com.example.profitter.Controller;
    opens com.example.profitter.Controller to javafx.fxml;
    exports com.example.profitter.clothingManager;
    opens com.example.profitter.clothingManager to javafx.fxml;

}