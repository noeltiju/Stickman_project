package com.example.stickman_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Score_Tracking {
    private int score = 0;private int bananas = 0;

    @FXML
    private Label score_label;
    @FXML
    private Label bananas_label;

    public Score_Tracking(Label label1, Label label2){
        this.score_label = label1;
        this.bananas_label = label2;
    }

    public void score_incrementer(){
        this.score_label.setText(String.valueOf(score+1));
        score = score+1;
    }





}
