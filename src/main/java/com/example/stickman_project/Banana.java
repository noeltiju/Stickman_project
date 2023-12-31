package com.example.stickman_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Banana {

    ImageView banana_view = new ImageView();
    private AnchorPane main_pane;
    private Blocks blocks;
    private NINJA ninja;
    private int moving = 0;
    private Score_Tracking scoreTracking;
    private boolean status = false;

    private Random rand = new Random();
    private final Timeline moving_banana = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
        if (!this.checkcollision() && this.banana_view.getLayoutX() < this.blocks.getSecondary_block().getLayoutX()) {
            if (moving == 0) {
                this.banana_view.setLayoutY(this.banana_view.getLayoutY() - 2);
                moving = 1;
            } else {
                this.banana_view.setLayoutY(this.banana_view.getLayoutY() + 2);
                moving = 0;
            }
        } else {
            deactivate();
        }

    }));

    private boolean checkcollision() {
        if (!status) {
            return false;
        }
        ;
        if (this.banana_view.getLayoutX() >= this.ninja.get_character().getLayoutX() && this.banana_view.getLayoutX() + 50 <= this.ninja.get_character().getLayoutX() + this.ninja.get_character().getFitWidth()) {
            if (Objects.equals(this.ninja.getCharacter_status(), "DOWN")) {

                scoreTracking.banana_incrementer();
                System.out.println("Hit");
                try {
                    play_flip();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    throw new RuntimeException(e);
                }
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
        status = false;


    }

    public void setPosition(double x, double y) {
        int pos = rand.nextInt((int) x, (int) y - 40);
        this.banana_view.setLayoutX(pos);
        this.banana_view.setLayoutY(561);
        activate();
    }

    public void deactivate() {

        this.banana_view.setVisible(false);
        status = false;
        this.moving_banana.stop();
    }


    public void activate() {
        this.banana_view.setVisible(true);
        status = true;
        this.moving_banana.setCycleCount(Timeline.INDEFINITE);
        this.moving_banana.play();

    }

    public void setScoreTracking(Score_Tracking scoreTracking) {
        this.scoreTracking = scoreTracking;
    }


    private void play_flip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            File file = new File("src/main/banana.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
            clip.drain();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
