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

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Barrel{
    ImageView barrel_view = new ImageView();
    private Rotate rotation;
    private Blocks blocks;
    private boolean status;

    private NINJA ninja;
    private AnchorPane main_pane;

    private final Timeline barrel_rolling = new Timeline(new KeyFrame(Duration.seconds(0.004), event ->{

        if (!status || this.ninja.get_death_status()){
            stop_rolling();
        }

        if(this.barrel_view.getLayoutX() < 270){
            this.deactivate();
        }
        if (!this.checkcollision()){
            rotation.setAngle(rotation.getAngle() - 1);
            barrel_view.setLayoutX(barrel_view.getLayoutX() - 1);
        }else{
            this.barrel_view.setVisible(false);status=false;
            stop_rolling();
            try {
                this.ninja.exit_routine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }));

    private boolean checkcollision() {
        if (this.barrel_view.getLayoutX() >= this.ninja.get_character().getLayoutX() && this.barrel_view.getLayoutX()+50 <= this.ninja.get_character().getLayoutX() + this.ninja.get_character().getFitWidth()){
          if (Objects.equals(this.ninja.getCharacter_status(), "MOVE")){
              try {
                  barrel_touch();
              } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                  throw new RuntimeException(e);
              }
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
        this.barrel_view.setVisible(false);status = false;
        main_pane = mainPane;

        this.main_pane.getChildren().add(barrel_view);
        this.rotation = new Rotate(0,25,25);
        this.barrel_view.getTransforms().add(this.rotation);


    }

    public void barrel_roll(){
        this.barrel_rolling.setCycleCount(Timeline.INDEFINITE);
        this.barrel_rolling.play();
    }

    public void setPosition() {
        this.barrel_view.setVisible(true);status = true;
        barrel_view.setLayoutX(blocks.getSecondary_block().getLayoutX() + (blocks.getSecondary_block().getWidth() / 2) - 25);
        barrel_view.setLayoutY(500);

    }
    public void deactivate(){
        this.barrel_view.setVisible(false);status = false;
    }

    private void barrel_touch() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try{
            File file = new File("src/main/barrel_touch.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
            clip.drain();


        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}
