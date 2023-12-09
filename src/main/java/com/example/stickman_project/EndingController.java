package com.example.stickman_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class EndingController {
    private Scene scene;
    private Stage stage;
    @FXML
    private Label score;
    @FXML
    private Label high_score;

    public void initialize(){

    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScore(int score, int highScore) {
        System.out.println(highScore);
        this.score.setText(String.valueOf(score));
        this.high_score.setText(String.valueOf(highScore));

        try {
            applause();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void new_game(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller.setStage(stage);


        stage.show();

    }



    private void applause() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try{
            File file = new File("src/main/applause.wav");
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
