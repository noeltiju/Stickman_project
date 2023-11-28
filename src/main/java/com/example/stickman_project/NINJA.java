package com.example.stickman_project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class NINJA {
    private Scene scene;
    private Stage stage;
    private boolean upsidedown = false;
    int cherry_counter = 0;
    int score = 0;
    @FXML
    ImageView character;
    private Blocks blocks;
    private Stick stick;

    double endx = 0;
    private Image running1 = new Image("donkeykong_running1.png");
    private Image running2 = new Image("donkeykong_running2.png");
    private Image running3 = new Image("donkeykong_running3.png");
    private Image running4 = new Image("donkeykong_running4.png");
    private Image running5 = new Image("donkeykong_running5.png");

    private Image kicking1 = new Image("donkeykong_kicking1.png");
    private Image kicking2 = new Image("donkeykong_kicking2.png");
    private Image kicking3 = new Image("donkeykong_kicking3.png");
    private Image kicking4 = new Image("donkeykong_kicking4.png");
    private Image kicking5 = new Image("donkeykong_kicking5.png");


    int running_number = 1;
    private Timeline running_timeline = new Timeline(new KeyFrame(Duration.seconds(0.005), event ->{
        boolean dead = false;
        if (character.getLayoutX() >= this.blocks.getSecondary_block().getLayoutX() + this.blocks.getSecondary_block().getWidth() ){
            dead = true;

        }

        if (character.getLayoutX() < this.endx - 0){
//            System.out.println(this.character.getLayoutX());
//            System.out.println(this.endx);
            this.character.setLayoutX(this.character.getLayoutX() + 1);
            switch (running_number) {
                case 1:
                    this.character.setImage(this.running1);
                    running_number = 2;
                    break;
                case 2:
                    this.character.setImage(this.running2);
                    running_number = 3;
                    break;
                case 3:
                    this.character.setImage(this.running3);
                    running_number = 4;
                    break;
                case 4:
                    this.character.setImage(this.running4);
                    running_number = 5;
                    break;
                case 5:
                    this.character.setImage(this.running5);
                    running_number = 1;
                    break;
            }
        }
        else{

            if (dead){
                exit_routine();

            }else{
                this.running_animation_stopper();
                this.initial_image();
                System.out.println(this.character.getLayoutX());
                System.out.println(this.endx);
            }


        }


    }));
    int kicking_number = 1;
    private Timeline kicking_timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event ->{
        switch (kicking_number) {
            case 1:
                this.character.setImage(this.kicking1);
                this.kicking_number = 2;
                break;
            case 2:
                this.character.setImage(this.kicking2);
                this.kicking_number = 3;
                break;
            case 3:
                this.character.setImage(this.kicking3);
                this.kicking_number = 4;
                break;
            case 4:
                this.character.setImage(this.kicking4);
                this.kicking_number = 5;
                break;
            case 5:
                this.character.setImage(this.kicking5);
                this.kicking_number = 1;
                break;
        }

    }));

    public NINJA(ImageView character) {
        this.character = character;
        this.character.setImage(running1);
    }

    public void initial_image(){
        this.character.setImage(running1);
    }

    public void set_position(double x){
        this.character.setLayoutX(x);
    }
    public void running_animation(double endx){
        this.endx = endx;
        this.running_timeline.setCycleCount(Animation.INDEFINITE);
        this.running_timeline.play();
    }
    public void running_animation_stopper(){
        this.running_timeline.stop();

        this.blocks.switch_block_motion();

    }
    public void kicking_animation(){
        this.kicking_timeline.setCycleCount(5);
        this.kicking_timeline.play();

    }
    public void kicking_animation_stopper(){
        this.kicking_timeline.stop();

    }
    public double get_position(){
        return this.character.getLayoutX();
    }

    public void setBlocks(Blocks blocks) {
        this.blocks = blocks;
    }

    public void setStick(Stick stick) {
        this.stick = stick;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void exit_routine(){
        System.out.println("Exited");
    }
}
