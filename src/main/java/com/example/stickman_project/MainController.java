package com.example.stickman_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.PrimitiveIterator;
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
    @FXML
    private Rectangle stick_rectangle;
    @FXML
    private Button exit_button;
    private Scene scene;private Stage stage;
    Image standing = new Image("donkey_kong.png");
    Image running = new Image("running.png");
    BackgroundImage bg = new BackgroundImage(
            (new Image("bg.png")),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    private NINJA ninja; private Blocks blocks;private Stick stick;
    @FXML
    public void initialize() throws InterruptedException {
        main_screen.setBackground(new Background(bg));
        stick_rectangle.setMouseTransparent(false);
    }

    public void main_initialize(){
        this.ninja = new NINJA(character);
        this.blocks = new Blocks(starting_block,secondary_block,ninja);
        this.ninja.setBlocks(this.blocks);
        this.stick = new Stick(stick_rectangle,ninja,blocks,this.scene,main_screen,character);
        this.ninja.setStick(this.stick);this.ninja.setStage(this.stage);this.ninja.setScene(this.scene);
        this.blocks.setStick(this.stick);
    }
    public void setScene(Scene scene) {
        this.scene = scene;
        System.out.println("Set");
        if (this.scene == null){
            System.out.println("Ayo");
        }
        main_initialize();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
