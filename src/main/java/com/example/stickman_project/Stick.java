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
    private final Timeline growing = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> increase_height()));;
    private Timeline Downwardtimeline;
    private Rotate rotation;
    @FXML
    private AnchorPane main_pane;

    private boolean flag=false;
    private Barrel barrel;
    public Stick(Rectangle stick, NINJA character, Blocks blocks, Scene scene, AnchorPane main_pane) {
        this.stick=stick;
        this.character = character;
        this.blocks = blocks;
        this.scene = scene;
        this.main_pane = main_pane;
        this.characterImageView = this.character.get_character();

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
        rectangle.setWidth(5);
        rectangle.setHeight(0);
        rectangle.setLayoutX(239);
        rectangle.setLayoutY(550);
        //rectangle.setFill(Color.DODGERBLUE);
        rectangle.setFill(Color.web("#8B500B"));
        rectangle.setStroke(Color.BLACK);
        main_pane.getChildren().add(rectangle);
        this.barrel = new Barrel(this.blocks,this.character,this.main_pane);
        this.stick = rectangle;
    }
    public void increase_height() {
        if(flag){
            return;
        }
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
                    }
                    else{
                        this.Downwardtimeline.stop();
                        growing.stop();
                        disable(scene);
                        double endX = stick.getLayoutX()  + this.stick.getHeight();
                        this.stop_down_timeline(endX);
                        if(Math.abs(character.get_position() - endX) <= 1){
                            this.character.running_animation_stopper_2();

                        }
                        flag=true;
                        return;
                    }
                })
        );
        Downwardtimeline.setCycleCount(Timeline.INDEFINITE);
        Downwardtimeline.play();

    }
    public void stop_down_timeline(double endX){
        if (this.barrel != null){
            this.barrel.barrel_roll();
        }
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
    public void stick_flag(){
        flag=false;
    }
}