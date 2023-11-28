package com.example.stickman_project;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Stick {
    @FXML
    private  Rectangle stick;
    private Rectangle previous_stick;
    private Scene scene;
    @FXML
    private NINJA character;
    @FXML
    private Blocks blocks;
    private final Timeline growing = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> increase_height()));;
    private Timeline Downwardtimeline;
    private Rotate rotation;
    @FXML
    private AnchorPane main_pane;
    public Stick(Rectangle stick, NINJA character, Blocks blocks, Scene scene, AnchorPane main_pane) {
        this.stick=stick;
        this.character = character;
        this.blocks = blocks;
        this.scene = scene;
        this.main_pane = main_pane;


        this.scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                this.character.kicking_animation();
                this.increase_height();
            }
        });
        this.scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                this.stick_fall();
            }
        });

    }

    public void initial_condition(){
        this.previous_stick = this.stick;
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(0);
        rectangle.setLayoutX(239);
        rectangle.setLayoutY(550);
        rectangle.setFill(Color.DODGERBLUE);
        main_pane.getChildren().add(rectangle);

        this.stick = rectangle;
    }
    public void increase_height() {
        growing.setCycleCount(Timeline.INDEFINITE);
        growing.play();
        stick.setTranslateY(stick.getTranslateY() - 5);
        stick.setHeight(stick.getHeight() + 5);
    }

    public void disable(Scene scene){
        scene.setOnMousePressed(null);
        scene.setOnMouseReleased(null);
    }

    public void stick_fall() {
        double change_x = stick.getWidth(),change_y=stick.getHeight();
        this.growing.stop();
        rotation = new Rotate(0, change_x, change_y);
        stick.getTransforms().add(rotation);
        this.Downwardtimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.005), e -> {
                    if (rotation.getAngle() < 90 && rotation.getAngle()>=0) {
                        rotation.setAngle(rotation.getAngle() + 1);
                    }
                    else{
                        this.Downwardtimeline.stop();
                        growing.stop();
                        disable(scene);
                        double endX = stick.getLayoutX()  + this.stick.getHeight();
                        this.stop_down_timeline(endX);
                        return;


                    }
                })
        );
        Downwardtimeline.setCycleCount(Timeline.INDEFINITE);
        Downwardtimeline.play();

    }
    public void stop_down_timeline(double endX){
        this.character.running_animation(endX);


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
}