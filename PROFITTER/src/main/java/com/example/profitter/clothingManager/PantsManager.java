package com.example.profitter.clothingManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.File;

public class PantsManager {
    private ObservableList<Image> pantsPhoto = FXCollections.observableArrayList();
    private GridPane pantsGridPane;
    private ImageView pantModel;

    public void setPantsGridPane(GridPane pantsGridPane) {
        this.pantsGridPane = pantsGridPane;
    }

    public void setPantModel(ImageView pantModel) {
        this.pantModel = pantModel;
    }

    public void addPants(File file) {
        Image image = new Image(file.toURI().toString());
        pantsPhoto.add(image);
        updatePantsGrid();
    }

    public void updatePantsGrid() {
        if (pantsGridPane != null) {
            pantsGridPane.getChildren().clear();
            int col = 0, row = 0;
            for (Image image : pantsPhoto) {
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setOnMouseClicked(event -> {
                    if (pantModel != null) {
                        pantModel.setImage(image);
                    }
                });
                GridPane.setRowIndex(imageView, row);
                GridPane.setColumnIndex(imageView, col);
                pantsGridPane.getChildren().add(imageView);
                col++;
                if (col >= 3) {
                    col = 0;
                    row++;
                }
            }
        }
    }
}
