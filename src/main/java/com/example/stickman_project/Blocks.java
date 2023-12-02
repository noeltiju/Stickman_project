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

    private Timeline moving_timeline = new Timeline(new KeyFrame(Duration.seconds(0.0005), event ->{
        if (this.ninja.get_position() >= 157){
            this.stick.setPosition(this.stick.getPosition() - 1);
            this.secondary_block.setLayoutX(this.secondary_block.getLayoutX() - 1);
            this.starting_block.setLayoutX(this.starting_block.getLayoutX() - 1);
            this.ninja.set_position(this.ninja.get_position() - 1);
        }

        else{
            this.ninja.initial_image();
            this.starting_block.setLayoutX(275 - this.secondary_block.getWidth());
            this.starting_block.setWidth(this.secondary_block.getWidth());
            this.generate_second_block();
            this.moving_timeline.stop();

        }

    }));
    private Stick stick;

    private Timeline rising_timeline = new Timeline(new KeyFrame(Duration.seconds(0.001), event ->{
        if (this.secondary_block.getLayoutY() <= 550){
            this.secondary_block.setLayoutY(this.secondary_block.getLayoutY() + 1);
        }

        else{
            stop_timeline();
        }
    }));

    private void stop_timeline() {
        this.rising_timeline.stop();
        this.stick.initial_condition();
    }

    public Blocks(Rectangle first, Rectangle second, NINJA character){
        this.starting_block = first;
        this.secondary_block = second;
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
        int x =  400  + rand.nextInt(500);
        this.secondary_block.setLayoutX(x);
        this.secondary_block.setLayoutY(0);
        this.secondary_block.setHeight(327);
        this.secondary_block.setWidth(rand.nextInt((int) this.ninja.get_character().getFitWidth(),300));
        this.rising_timeline.setCycleCount(Timeline.INDEFINITE);
        this.rising_timeline.play();


    }
    public void switch_block_motion(){

        this.moving_timeline.setCycleCount(Animation.INDEFINITE);
        this.moving_timeline.play();
        this.stick.remove_previous_stick();
        this.stick.stick_flag();

    }
    public void setStick(Stick stick) {
        this.stick = stick;
    }

    public Rectangle getSecondary_block() {
        return secondary_block;
    }

    public Rectangle getPrimary_block() {
    return this.starting_block;
    }

    public void stop() {
        this.moving_timeline.stop();
    }


}
