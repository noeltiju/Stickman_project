package com.example.stickman_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class MainController {
    @FXML
    private AnchorPane main_screen;
    @FXML
    private Rectangle starting_block;
    @FXML
    private Rectangle secondary_block;
    @FXML
    public ImageView character;
    Image standing = new Image("donkey_kong.png");
    Image running = new Image("running.png");
    BackgroundImage bg = new BackgroundImage(
            (new Image("bg.png")),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    private NINJA ninja; private Blocks blocks;
    public void initialize() throws InterruptedException {

        main_screen.setBackground(new Background(bg));
        ninja = new NINJA(character);
        blocks = new Blocks(starting_block,secondary_block,main_screen,ninja);
        blocks.switch_block_motion();
        this.secondary_block.setVisible(false);
        blocks.generate_second_block_initial();
    }

    public void exit(ActionEvent event) throws IOException {
        ninja.running_animation_stopper();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ending-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Stick Hero!");
        stage.setScene(scene);
        stage.show();
    }
}
