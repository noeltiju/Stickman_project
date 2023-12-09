package com.example.stickman_project;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Stick {
    @FXML
    private  Rectangle stick;
    @FXML
    private ImageView characterImageView;
    private Rectangle previous_stick;
    private Scene scene;
    @FXML
    private NINJA character;
    @FXML
    private Blocks blocks;
    private final Timeline growing = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> increase_height(this.stick)));
    private Timeline Downwardtimeline;
    private Rotate rotation;
    @FXML
    private AnchorPane main_pane;
    private Score_Tracking scoreTracker;

    private Banana banana;
    private Barrel barrel;
    private boolean flag=false;

    private boolean stick_activate = false;
    private ArrayList<Barrel> barrels = new ArrayList<Barrel>();
    public Stick(Rectangle stick, NINJA character, Blocks blocks, Scene scene, AnchorPane main_pane) {
        this.banana = new Banana(blocks,character,main_pane);
        this.barrel = new Barrel(blocks,character,main_pane);

        this.stick=stick;
        this.character = character;
        this.blocks = blocks;
        this.scene = scene;
        this.main_pane = main_pane;
        this.characterImageView = this.character.get_character();
        this.stick_activate = true;

        this.scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE && this.stick_activate) {
                this.character.kicking_animation();
                this.increase_height(this.stick);
            }
        });
        this.scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.SPACE && this.stick_activate) {
                this.stick_fall(this.stick);
            }
        });

    }

    public void initial_condition(){
        this.previous_stick = this.stick;
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(5);
        rectangle.setHeight(0);
        rectangle.setLayoutX(239);
        rectangle.setLayoutY(550);

        rectangle.setFill(Color.web("#8B500B"));
        rectangle.setStroke(Color.BLACK);
        main_pane.getChildren().add(rectangle);
        this.scoreTracker.score_incrementer();
        this.stick = rectangle;
        this.stick_activate = true;

        boolean state = new Random().nextBoolean();

        if (state){
            this.banana.setPosition(this.blocks.getPrimary_block().getLayoutX() + this.blocks.getPrimary_block().getWidth() , this.blocks.getSecondary_block().getLayoutX());

        }

        boolean state1 = new Random().nextBoolean();
        if (state1){
            this.barrel.setPosition();
        }

    }
    public void increase_height(Rectangle stick) {
        if(flag){
            return;
        }
        growing.setCycleCount(Timeline.INDEFINITE);
        growing.play();
        stick.setTranslateY(stick.getTranslateY() - 5);
        stick.setHeight(stick.getHeight() + 5);

    }




    public void stick_fall(Rectangle stick) {
        if(flag){
            return;
        }
        double change_x = stick.getWidth(),change_y=stick.getHeight();
        this.growing.stop();
        rotation = new Rotate(0, change_x, change_y);
        stick.getTransforms().add(rotation);
        this.Downwardtimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.005), e -> {
                    if (rotation.getAngle() < 90 && rotation.getAngle()>=0) {
                        rotation.setAngle(rotation.getAngle() + 1);
                    }else{
                        this.stop_down_timeline();
                    }
                })
        );
        flag = true;
        Downwardtimeline.setCycleCount(Timeline.INDEFINITE);
        Downwardtimeline.play();
        try {
            play_stick();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }
    public void stop_down_timeline(){
        this.Downwardtimeline.stop();
        this.growing.stop();
        double endX = stick.getLayoutX()  + this.stick.getHeight();
        this.stick_activate = false;

        this.character.running_animation(endX);

        if (!this.stick_activate){
            this.barrel.barrel_roll();
        }
    }
    public Rectangle getStick() {
        return stick;
    }

    public void setPosition(double x){
        this.stick.setLayoutX(x);
    }

    public double getPosition(){
        return this.stick.getLayoutX();
    }

    public void remove_previous_stick(){
        this.main_pane.getChildren().remove(this.previous_stick);
    }
    public void stick_flag(){
        flag=false;
    }

    public void setTracker(Score_Tracking scoreTracker) {
        this.scoreTracker = scoreTracker;
        this.banana.setScoreTracking(scoreTracker);
    }

    public Barrel getBarrel() {
        return barrel;
    }


    private void play_stick() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try{
            File file = new File("src/main/fall_block.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    //Rotates to death
    public void stick_dies(){
        System.out.println(rotation.getAngle());

        this.Downwardtimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.005), e -> {
                    if (rotation.getAngle() < 180 && rotation.getAngle()>=90) {
                        rotation.setAngle(rotation.getAngle() + 1);
                    }
                })
        );
        Downwardtimeline.setCycleCount(Timeline.INDEFINITE);
        Downwardtimeline.play();
    }
}