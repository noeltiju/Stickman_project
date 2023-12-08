package com.example.stickman_project;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.EventObject;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloController {
    @FXML
    private AnchorPane starting_screen;
    @FXML
    public ImageView character;
    @FXML
    private Rectangle starting_block;
    @FXML
    private Rectangle secondary_block;
    Image standing = new Image("standing.png");
    Image running = new Image("running.png");

    private Stage stage;private Scene scene;
    BackgroundImage bg = new BackgroundImage(
            (new Image("bg.png")),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    public void initialize() throws InterruptedException {

        starting_screen.setBackground(new Background(bg));
        if (this.secondary_block != null){
            this.secondary_block.setVisible(false);
        }


    }


    public void setup_first_frame(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-game.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();


        scene = new Scene(root);
        controller.setScene(scene);


        stage.setScene(this.scene);

        controller.setStage(stage);

        stage.show();

    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}