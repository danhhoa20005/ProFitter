package com.example.profitter.clothingManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.File;

public class ShoesManager {
    private ObservableList<Image> shoesPhoto = FXCollections.observableArrayList();
    private GridPane shoesGridPane;
    private ImageView shoesModel;

    public void setShoesGridPane(GridPane shoesGridPane) {
        this.shoesGridPane = shoesGridPane;
    }

    public void setShoesModel(ImageView shoesModel) {
        this.shoesModel = shoesModel;
    }

    public void addShoes(File file) {
        Image image = new Image(file.toURI().toString());
        shoesPhoto.add(image);
        updateShoesGrid();
    }

    public void updateShoesGrid() {
        if (shoesGridPane != null) {
            shoesGridPane.getChildren().clear();
            int col = 0, row = 0;
            for (Image image : shoesPhoto) {
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setOnMouseClicked(event -> {
                    if (shoesModel != null) {
                        shoesModel.setImage(image);
                    }
                });
                GridPane.setRowIndex(imageView, row);
                GridPane.setColumnIndex(imageView, col);
                shoesGridPane.getChildren().add(imageView);
                col++;
                if (col >= 3) {
                    col = 0;
                    row++;
                }
            }
        }
    }
}