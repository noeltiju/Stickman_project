package com.example.stickman_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.util.Objects;

public class  HelloApplication extends Application {
    @Override
    public void start(Stage  stage) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();

        Scene scene = new Scene(root);

        controller.setScene(scene);

        stage = new Stage();
        stage.setScene(scene);       

        controller.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}