package com.example.stickman_project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Blocks {

    @FXML
    private Rectangle starting_block;
    @FXML
    private Rectangle secondary_block;
    @FXML
    private AnchorPane main_pane;
    private NINJA ninja;
    private Random rand = new Random();

    private Timeline moving_timeline = new Timeline(new KeyFrame(Duration.seconds(0.001), event ->{
        if (this.secondary_block.getLayoutX() + this.secondary_block.getWidth() >= 275){
            this.secondary_block.setLayoutX(this.secondary_block.getLayoutX() - 1);
            this.starting_block.setLayoutX(this.starting_block.getLayoutX() - 1);
        }else{
            this.ninja.initial_image();
            this.ninja.set_position(89);
            this.starting_block.setLayoutX(275 - this.secondary_block.getWidth());
            this.starting_block.setWidth(this.secondary_block.getWidth());
            this.generate_second_block();
            this.moving_timeline.stop();

        }

    }));
    public Blocks(Rectangle first, Rectangle second, AnchorPane main_pane,NINJA character){
        this.starting_block = first;
        this.secondary_block = second;
        this.main_pane = main_pane;
        this.ninja = character;
    }
    public void generate_second_block_initial(){
        int x = 380 + rand.nextInt(500);
        this.secondary_block.setLayoutX(x);
        this.secondary_block.setLayoutY(550);
        this.secondary_block.setWidth(rand.nextInt(100,500));
        this.secondary_block.setVisible(true);
    }
    public void generate_second_block(){
        int x = 380 + rand.nextInt(500);
        this.secondary_block.setLayoutX(x);
        this.secondary_block.setLayoutY(550);
        this.secondary_block.setWidth(rand.nextInt(100,500));
    }
    public void switch_block_motion(){

        this.moving_timeline.setCycleCount(Animation.INDEFINITE);
        this.moving_timeline.play();
    }
}
