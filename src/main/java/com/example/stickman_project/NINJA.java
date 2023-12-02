package com.example.stickman_project;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

public class NINJA {
    private Scene scene;
    private Stage stage;
    private boolean upsidedown = false;
    int cherry_counter = 0;
    int score = 0;
    @FXML
    ImageView character;
    private Blocks blocks;
    private Stick stick;

    String character_status = "";
    double endx = 0;
    private Image running1 = new Image("donkeykong_running1.png");
    private Image running2 = new Image("donkeykong_running2.png");
    private Image running3 = new Image("donkeykong_running3.png");
    private Image running4 = new Image("donkeykong_running4.png");
    private Image running5 = new Image("donkeykong_running5.png");
    private Image running6 = new Image("donkeykong_running6.png");
    private Image running7 = new Image("donkeykong_running7.png");

    private Image kicking1 = new Image("donkeykong_kicking1.png");
    private Image kicking2 = new Image("donkeykong_kicking2.png");






    int running_number = 1;
    private Timeline running_timeline = new Timeline(new KeyFrame(Duration.seconds(0.02), event ->{
        boolean dead = false;
        if (endx >= this.blocks.getSecondary_block().getLayoutX() + this.blocks.getSecondary_block().getWidth() || endx <= this.blocks.getSecondary_block().getLayoutX()){
            dead = true;
        }

        if ((!dead && (character.getLayoutX() < (this.blocks.getSecondary_block().getLayoutX() +this.blocks.getSecondary_block().getWidth()) - this.character.getFitWidth())) || (dead && this.character.getLayoutX() < endx)){

            this.character.setLayoutX(this.character.getLayoutX() + 5);
            switch (running_number) {
                case 1:
                    this.character.setImage(this.running1);
                    running_number = 2;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);

                    break;
                case 2:
                    this.character.setImage(this.running2);
                    running_number = 3;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
                    break;
                case 3:
                    this.character.setImage(this.running3);
                    running_number = 4;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
                    break;
                case 4:
                    this.character.setImage(this.running4);
                    running_number = 5;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
                    break;
                case 5:
                    this.character.setImage(this.running5);
                    running_number = 6;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
                    break;

                case 6:
                    this.character.setImage(this.running6);
                    running_number = 7;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
                    break;

                case 7:
                    this.character.setImage(this.running7);
                    running_number = 1;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
                    break;
            }

        }
        else{

            if (dead){
                try {
                    exit_routine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }else{
                this.running_animation_stopper();
                this.initial_image();

            }


        }


    }));
    int kicking_number = 1;
    private Timeline kicking_timeline = new Timeline(new KeyFrame(Duration.seconds(0.10), event ->{
        switch (kicking_number) {
            case 1:
                this.character.setImage(this.kicking1);
                this.kicking_number = 2;
                break;
            case 2:
                this.character.setImage(this.kicking2);
                this.kicking_number = 1;
                break;

        }

    }));


    private Timeline falling_timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event ->{
        double stick_end = stick.getStick().getLayoutX()+stick.getStick().getHeight();
        double block_end = blocks.getSecondary_block().getLayoutX();
        if(stick_end <block_end){
            this.character.setRotate(10);
            this.character.setRotate(20);
            this.character.setRotate(30);
            this.character.setRotate(40);
            this.character.setRotate(50);
            this.character.setRotate(60);
            this.character.setRotate(70);
            this.character.setRotate(80);
            this.character.setRotate(90);
            this.character.setRotate(100);
        }
        else{
            return;
        }

    }));

    public NINJA(ImageView character) {
        this.character = character;
        this.character.setImage(running1);
        this.character_status = "MOVE";
    }

    public void initial_image(){
        this.character.setImage(running1);
        this.scene.addEventFilter(KeyEvent.KEY_PRESSED,this::donkey_normal);
    }

    public void set_position(double x){
        this.character.setLayoutX(x);
    }
    public void running_animation(double endx){
        this.endx = endx;

        this.running_timeline.setCycleCount(Animation.INDEFINITE);
        this.running_timeline.play();


    }
    public void running_animation_stopper(){
        this.running_timeline.stop();
        this.blocks.switch_block_motion();

    }
    public void running_animation_stopper_2(){
        this.running_timeline.stop();

    }
    public void kicking_animation(){
        this.kicking_timeline.setCycleCount(5);
        this.kicking_timeline.play();

    }
    public void falling_animation(){
        this.falling_timeline.setCycleCount(Animation.INDEFINITE);
        this.falling_timeline.play();
    }
    public void kicking_animation_stopper(){
        this.kicking_timeline.stop();

    }

    public double get_position(){
        return this.character.getLayoutX();
    }
    public ImageView get_character(){
        return this.character;
    }

    public void setBlocks(Blocks blocks) {
        this.blocks = blocks;
    }

    public void setStick(Stick stick) {
        this.stick = stick;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void exit_routine() throws IOException {
        this.blocks.stop();
        this.running_animation_stopper_2();
        System.out.println("Apple");
        RotateTransition r = new RotateTransition(Duration.seconds(3),this.character);
        r.setFromAngle(0);
        r.setToAngle(720);
        r.setCycleCount(1);
        TranslateTransition fallTransition = new TranslateTransition(Duration.seconds(3), this.character);
        fallTransition.setByY(600);
        r.play();
        fallTransition.play();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ending-view.fxml"));
        Parent root = loader.load();
        EndingController controller = loader.getController();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        controller.setStage(stage);

        stage.show();
        controller.setScore(this.stick.getScoreTracker().getScore());

    }

    private void donkey_flip(KeyEvent event) {
        if (event.getCode() == KeyCode.DOWN) {
            this.character_status = "DOWN";
            this.character.setTranslateY(90);
            this.character.setScaleY(-1);
        }
    }
    private void donkey_normal(KeyEvent event){
        if(event.getCode()==KeyCode.DOWN){
            this.character_status = "MOVE";
            this.character.setScaleY(1);
            this.character.setTranslateY(0);
        }
    }


    private boolean isJumping = false;

    private void donkey_jump(KeyEvent event) {
        if (event.getCode() == KeyCode.UP && !isJumping) {
            isJumping = true;
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.6), this.character);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            TranslateTransition jumpTransition = new TranslateTransition(Duration.seconds(0.6), this.character);
            jumpTransition.setByY(-100);
            ParallelTransition combo = new ParallelTransition(rotateTransition, jumpTransition);
            combo.play();

        }
    }

    private void donkey_no_jump(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {

            isJumping = false;


            TranslateTransition donkeyNoJump = new TranslateTransition(Duration.seconds(0.6), this.character);
            donkeyNoJump.setToY(0);
            donkeyNoJump.play();

        }
    }

    public String getCharacter_status() {
        return character_status;
    }
}
