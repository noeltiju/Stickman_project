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

public class Barrel{
    ImageView barrel_view = new ImageView();
    private Rotate rotation;
    private Blocks blocks;

    private NINJA ninja;
    private AnchorPane main_pane;

    private Timeline barrel_rolling = new Timeline(new KeyFrame(Duration.seconds(0.004), event ->{
        if (barrel_view.getLayoutX() > blocks.getPrimary_block().getLayoutX() ){
            rotation.setAngle(rotation.getAngle() - 1);
            barrel_view.setLayoutX(barrel_view.getLayoutX() - 1);
        }else{
            this.main_pane.getChildren().remove(barrel_view);
            stop_rolling();
        }

    }));

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

    public void barrel_roll(){
        barrel_rolling.setCycleCount(Timeline.INDEFINITE);
        barrel_rolling.play();
    }
}
