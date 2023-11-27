package com.example.stickman_project;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Stick {
    private final Rectangle stick;
    private final Timeline growing;
    private Timeline Downwardtimeline;
    public Stick(Rectangle stick) {
        stick.setHeight(0);
        stick.setWidth(10);
        stick.setTranslateX(89);
        stick.setTranslateY(401);
        stick.setFill(Color.BLACK);
        growing = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> increase_height()));
        growing.setCycleCount(Timeline.INDEFINITE);
        this.stick=stick;

        stick.setOnMousePressed(event -> increase_height());
        stick.setOnMouseReleased(event -> stick_fall());
    }
    public void increase_height() {
        growing.play();
        stick.setTranslateY(stick.getTranslateY() - 5);
        stick.setHeight(stick.getHeight() + 5);
    }

    public void disable(Scene scene){
        scene.setOnMousePressed(null);
        scene.setOnMouseReleased(null);
    }

    private void stick_fall() {
        growing.pause();
        double change_x = stick.getWidth(),change_y=stick.getHeight();
        Rotate rotation = new Rotate(0, change_x, change_y);
        stick.getTransforms().add(rotation);
        this.Downwardtimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.005), e -> {
                    if (rotation.getAngle() < 90 && rotation.getAngle()>=0) {
                        rotation.setAngle(rotation.getAngle() + 1);
                    }
                    else{
                        this.Downwardtimeline.stop();
                        growing.stop();
                        disable(stick.getScene());
                    }
                })
        );
        Downwardtimeline.setCycleCount(Timeline.INDEFINITE);
        Downwardtimeline.play();
    }
}