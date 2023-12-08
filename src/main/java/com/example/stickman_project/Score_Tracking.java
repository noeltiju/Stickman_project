package com.example.stickman_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.*;
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
    }

    public void File_reader() throws FileNotFoundException {
        in = new Scanner(new BufferedReader(new FileReader("src/main/game_state.txt")));
        in.next();
        this.bananas = Integer.parseInt(in.next());
        in.next();
        this.high_score = Integer.parseInt(in.next());
        this.score = 0;
        set_bananas_score(bananas);

        in.close();


    }

    public void File_writer() throws IOException {
        if (score > high_score){
            high_score = score;
        }
        String[] lines = {
                "Bananas: " + bananas,
                "HighScore: " + high_score
        };
        BufferedWriter out = new BufferedWriter(new FileWriter("src/main/game_state.txt"));
        for (String line: lines){
            out.write(line);
            out.newLine();
        }

        out.close();

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

    public int getHigh_score() {
        return high_score;
    }
}
