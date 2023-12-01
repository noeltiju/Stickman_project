package com.example.stickman_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Objects;

public class Barrel implements Runnable{
    ImageView barrel_view = new ImageView();
    private Rotate rotation;
    private Blocks blocks;

    private NINJA ninja;
    private AnchorPane main_pane;

    private final Timeline barrel_rolling = new Timeline(new KeyFrame(Duration.seconds(0.004), event ->{
        if (!this.checkcollision()){
            rotation.setAngle(rotation.getAngle() - 1);
            barrel_view.setLayoutX(barrel_view.getLayoutX() - 1);
        }else{
            this.main_pane.getChildren().remove(barrel_view);
            stop_rolling();
            this.ninja.exit_routine();
        }

    }));

    private boolean checkcollision() {
        if (this.barrel_view.getLayoutX() >= this.ninja.get_character().getLayoutX() && this.barrel_view.getLayoutX()+50 <= this.ninja.get_character().getLayoutX() + this.ninja.get_character().getFitWidth()){
                if (Objects.equals(this.ninja.getCharacter_status(), "MOVE")){
                    return true;
                }
        }

        return false;
    }

    private void stop_rolling() {
        this.barrel_rolling.stop();
    }

    public ImageView getBarrel_view() {
        return barrel_view;
    }

    public Barrel(Blocks blocks, NINJA ninja, AnchorPane mainPane){
        this.blocks = blocks;
        this.ninja = ninja;
        this.main_pane = mainPane;
        this.barrel_view.setFitHeight(50);
        this.barrel_view.setFitWidth(50);
        this.barrel_view.setImage(new Image("Donkey Kong Barrel.png"));
        barrel_view.setLayoutX(blocks.getSecondary_block().getLayoutX() + (blocks.getSecondary_block().getWidth() / 2) - 25);
        barrel_view.setLayoutY(500);
        mainPane.getChildren().add(barrel_view);
        this.rotation = new Rotate(0,25,25);
        this.barrel_view.getTransforms().add(this.rotation);

    }

    @Override
    public void run(){
        System.out.println("Executing");
        this.barrel_rolling.setCycleCount(Timeline.INDEFINITE);
        this.barrel_rolling.play();
    }

    public void barrel_roll(){
        System.out.println("Executing");
        this.barrel_rolling.setCycleCount(Timeline.INDEFINITE);
        this.barrel_rolling.play();
    }
}
