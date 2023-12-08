package com.example.stickman_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Score_Tracking {
    private int score = 0;
    private int bananas = 0;
    private int high_score = 0;

    @FXML
    private Label score_label;
    @FXML
    private Label bananas_label;
    Scanner in;

    public Score_Tracking(Label label1, Label label2) throws FileNotFoundException {
        this.score_label = label1;
        this.bananas_label = label2;
        this.File_reader();
    }

    public void File_reader() throws FileNotFoundException {
        in = new Scanner(new BufferedReader(new FileReader("src/main/game_state.txt")));
        in.next();
        this.bananas = Integer.parseInt(in.next());
        in.next();
        this.high_score = Integer.parseInt(in.next());
        this.score = 0;
        set_bananas_score(bananas);

    }

    public void File_writer() throws FileNotFoundException{
    }

    public void score_incrementer(){
        this.score_label.setText(String.valueOf(score+1));
        score = score+1;
    }
    public void banana_incrementer(){
        this.bananas_label.setText(String.valueOf(bananas+1));
        bananas = bananas + 1;
    }

    public void set_bananas_score(int n){
        this.bananas_label.setText(String.valueOf(n));
    }


    public int getScore() {
        return score;
    }
}
