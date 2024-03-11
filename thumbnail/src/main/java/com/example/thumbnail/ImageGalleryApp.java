package com.example.thumbnail;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageGalleryApp extends Application {

    private ImageView fullSizeImageView;
    private StackPane fullSizeImagePane;
    private FlowPane thumbnailPane;

    @Override
    public void start(Stage primaryStage) {

        Image[] thumbnails = {
                new Image(getClass().getResourceAsStream("/car1.jpg"), 100, 0, true, true),
                new Image(getClass().getResourceAsStream("/messi.jpg"), 100, 0, true, true),
                new Image(getClass().getResourceAsStream("/mort.jpg"), 100, 0, true, true)
        };

        Image[] fullSizeImages = {
                new Image(getClass().getResourceAsStream("/car1.jpg")),
                new Image(getClass().getResourceAsStream("/messi.jpg")),
                new Image(getClass().getResourceAsStream("/mort.jpg"))
        };

        thumbnailPane = createThumbnailPane(thumbnails, fullSizeImages);
        fullSizeImageView = new ImageView();
        fullSizeImagePane = new StackPane(fullSizeImageView);
        fullSizeImagePane.getStyleClass().add("full-size-image-pane");
        fullSizeImagePane.setVisible(false);

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showThumbnailView());

        VBox fullSizeLayout = new VBox(backButton, fullSizeImagePane);
        fullSizeLayout.setSpacing(5);
        fullSizeLayout.setPadding(new Insets(5));

        VBox root = new VBox(thumbnailPane, fullSizeLayout);
        root.getStyleClass().add("main-root");

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("Image Gallery");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private FlowPane createThumbnailPane(Image[] thumbnails, Image[] fullSizeImages)
    {
        FlowPane pane = new FlowPane();
        pane.getStyleClass().add("thumbnail-pane");
        pane.setHgap(10);
        pane.setVgap(10);

        for (int i = 0; i < thumbnails.length; i++) {
            ImageView imageView = new ImageView(thumbnails[i]);
            imageView.getStyleClass().add("thumbnail-image");
            int finalI = i;
            imageView.setOnMouseClicked(event -> showFullSizeImage(fullSizeImages[finalI]));
            pane.getChildren().add(imageView);
        }

        return pane;
    }

    private void showFullSizeImage(Image image) {
        fullSizeImageView.setImage(image);
        fullSizeImagePane.setVisible(true);
        thumbnailPane.setVisible(false);
    }

    private void showThumbnailView() {
        thumbnailPane.setVisible(true);
        fullSizeImagePane.setVisible(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
