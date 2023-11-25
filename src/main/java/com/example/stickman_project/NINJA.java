package com.example.stickman_project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class NINJA {
    private boolean upsidedown = false;
    int cherry_counter = 0;
    int score = 0;
    @FXML
    ImageView character;
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
    private Timeline running_timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), event ->{
        this.character.setLayoutX(this.character.getLayoutX() + 7);
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
    public void running_animation(){
        this.running_timeline.setCycleCount(Animation.INDEFINITE);
        this.running_timeline.play();
    }
    public void running_animation_stopper(){
        this.running_timeline.stop();
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



}
