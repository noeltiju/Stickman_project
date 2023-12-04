package com.example.stickman_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Banana {

    ImageView banana_view = new ImageView();
    private AnchorPane main_pane;
    private Blocks blocks;
    private NINJA ninja;
    private int moving = 0;

    private Random rand = new Random();
    private final Timeline moving_banana = new Timeline(new KeyFrame(Duration.seconds(0.004), event ->{
        if (!this.checkcollision()){
            if (moving == 0){
                this.banana_view.setLayoutY(this.banana_view.getLayoutY() - 1);
                moving = 1;
            }else{
                this.banana_view.setLayoutY(this.banana_view.getLayoutY() + 1);
                moving = 0;
            }
        }else{
            deactivate();
        }

    }));

    private boolean checkcollision() {
        if (this.banana_view.getLayoutX() >= this.ninja.get_character().getLayoutX() && this.banana_view.getLayoutX()+50 <= this.ninja.get_character().getLayoutX() + this.ninja.get_character().getFitWidth()){
            if (Objects.equals(this.ninja.getCharacter_status(), "DOWN")){
                System.out.println("Hit");
                return true;
            }
        }

        return false;
    }

    public Banana(Blocks blocks, NINJA ninja, AnchorPane mainPane) {
        this.blocks = blocks;
        this.ninja = ninja;
        this.main_pane = mainPane;
        this.banana_view.setFitHeight(50);
        this.banana_view.setFitWidth(50);
        this.banana_view.setImage(new Image("banana.png"));
        mainPane.getChildren().add(this.banana_view);
        this.banana_view.setVisible(false);


    }

    public void setPosition(double x, double y){
        int pos = rand.nextInt((int) x, (int) y - 20);
        this.banana_view.setLayoutX(pos);
        this.banana_view.setLayoutY(561);
        activate();
    }

    public void deactivate(){
        this.banana_view.setVisible(false);
        this.moving_banana.stop();
    }

    public void activate(){
        this.banana_view.setVisible(true);
        this.moving_banana.setCycleCount(Timeline.INDEFINITE);
        this.moving_banana.play();


    }

}
