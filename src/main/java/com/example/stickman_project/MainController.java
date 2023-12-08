package com.example.stickman_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
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
    @FXML
    private Label score_label;
    @FXML
    private Pane pane;
    @FXML
    private Rectangle bonus;
    @FXML
    private Label bananas_label;
    Image standing = new Image("donkey_kong.png");
    Image running = new Image("running.png");
    BackgroundImage bg = new BackgroundImage(
            (new Image("bg.png")),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    private NINJA ninja; private Blocks blocks;private Stick stick; private Score_Tracking scoreTracker;
    Background_changer bg_changer;
    @FXML
    public void initialize() throws InterruptedException {
//        main_screen.setBackground(new Background(bg));
        stick_rectangle.setMouseTransparent(false);
        pane.setVisible(false);

        bg_changer = new Background_changer(main_screen);
    }

    public void main_initialize() throws FileNotFoundException {

        this.scoreTracker = new Score_Tracking(score_label,bananas_label);
        this.scoreTracker.File_reader();
        bg_changer.setScore_tracker(this.scoreTracker);
        Thread bg_changer_thread = new Thread(bg_changer);
        bg_changer_thread.start();


        this.ninja = new NINJA(character);
        this.blocks = new Blocks(starting_block,secondary_block,ninja);
        this.ninja.setBlocks(this.blocks);
        this.stick = new Stick(stick_rectangle,ninja,blocks,this.scene,main_screen);
        this.ninja.setStick(this.stick);this.ninja.setStage(this.stage);this.ninja.setScene(this.scene);this.ninja.setScore_tracker(scoreTracker);
        this.ninja.setRevive(pane);
        this.blocks.setStick(this.stick);
        this.stick.setTracker(this.scoreTracker);
        this.blocks.setBonus(bonus);
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        main_initialize();

    }

    public void revive(ActionEvent event){
        this.ninja.choose_revive();
    }

    public void die(ActionEvent event) throws IOException {
        this.ninja.no_revive();
    }
}
