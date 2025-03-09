module com.example.profitter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.profitter to javafx.fxml;
    exports com.example.profitter;
}