package com.example.stickman_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class EndingController {
    private Scene scene;
    private Stage stage;
    @FXML
    private Label score;
    public void initialize(){

    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScore(int score) {
        this.score.setText(String.valueOf(score));
    }
}
