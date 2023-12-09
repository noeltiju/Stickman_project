package com.example.stickman_project;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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
    @Override
    public void run(){
        while(true){
            if (this.score_tracker.getScore() >= 2 && this.score_tracker.getScore() < 4){

                this.main_pane.setBackground(new Background(new BackgroundImage(
                        (new Image("bg4.png")),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
            }else if (this.score_tracker.getScore() >= 4 && this.score_tracker.getScore() < 8){
                this.main_pane.setBackground(new Background(new BackgroundImage(
                        (new Image("bg6.jpg")),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
            }
        }

    }

    public void setScore_tracker(Score_Tracking score_tracker) {
        this.score_tracker = score_tracker;
    }
}
