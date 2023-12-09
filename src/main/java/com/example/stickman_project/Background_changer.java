package com.example.stickman_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class Background_changer implements Runnable{
    @FXML
    private AnchorPane main_pane;
    private Score_Tracking score_tracker;

    public Background_changer(AnchorPane pane){
        this.main_pane = pane;
        this.main_pane.setBackground(new Background(new BackgroundImage(
                (new Image("bg.png")),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }
    int file_no = 1;
    private Timeline changing_timeline = new Timeline(new KeyFrame(Duration.seconds(5), event ->{
        switch (file_no) {
            case 1:
                this.main_pane.setBackground(new Background(new BackgroundImage(
                        (new Image("bg.png")),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
                this.file_no = 2;
                break;
            case 2:
                this.main_pane.setBackground(new Background(new BackgroundImage(
                        (new Image("bg2.png")),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
                this.file_no = 3;
                break;
            case 3:
                this.main_pane.setBackground(new Background(new BackgroundImage(
                        (new Image("bg3.png")),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
                this.file_no = 4;
                break;
            case 4:
                this.main_pane.setBackground(new Background(new BackgroundImage(
                        (new Image("bg4.png")),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
                this.file_no = 5;
                break;
            case 5:
                this.main_pane.setBackground(new Background(new BackgroundImage(
                        (new Image("bg5.png")),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
                this.file_no = 1;
                break;
        }

    }));

    @Override
    public void run(){
        this.changing_timeline.setCycleCount(Timeline.INDEFINITE);
        changing_timeline.play();

    }

    public void setScore_tracker(Score_Tracking score_tracker) {
        this.score_tracker = score_tracker;
    }
}
