package com.example.stickman_project;

        import javafx.animation.*;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.KeyCode;
        import javafx.scene.input.KeyEvent;
        import javafx.scene.layout.Pane;
        import javafx.stage.Stage;
        import javafx.util.Duration;

        import javafx.scene.media.Media;
        import javafx.scene.media.MediaPlayer;

        import java.io.File;
        import java.io.IOException;
        import java.util.Objects;
        import java.net.URL;
        import javax.sound.sampled.*;


public class NINJA {
    private Scene scene;
    private Stage stage;
    private boolean upsidedown = false;

    private boolean no_jump = false;
    int cherry_counter = 0;
    int score = 0;
    @FXML
    ImageView character;
    private Blocks blocks;
    private Stick stick;
    private int deaths = 0;
    String character_status = "";
    double endx = 0;
    private Pane revive;
    private Image running1 = new Image("donkeykong_running1.png");
    private Image running2 = new Image("donkeykong_running2.png");
    private Image running3 = new Image("donkeykong_running3.png");
    private Image running4 = new Image("donkeykong_running4.png");
    private Image running5 = new Image("donkeykong_running5.png");
    private Image running6 = new Image("donkeykong_running6.png");
    private Image running7 = new Image("donkeykong_running7.png");

    private Image kicking1 = new Image("donkeykong_kicking1.png");
    private Image kicking2 = new Image("donkeykong_kicking2.png");

    private boolean dead = false;

    int running_number = 1;int kicking_number = 1;

    private Score_Tracking score_tracker;
    private Timeline running_timeline = new Timeline(new KeyFrame(Duration.seconds(0.02), event ->{
        if (endx >= this.blocks.getSecondary_block().getLayoutX() + this.blocks.getSecondary_block().getWidth() || endx <= this.blocks.getSecondary_block().getLayoutX()){
            dead = true;
        }

        if (this.character.getLayoutX() + this.character.getFitWidth() >= this.blocks.getSecondary_block().getLayoutX()){
            if (Objects.equals(this.character_status, "DOWN")){
                dead = true;
                try {
                    exit_routine();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if ((!dead && (character.getLayoutX() < (this.blocks.getSecondary_block().getLayoutX() +this.blocks.getSecondary_block().getWidth()) - this.character.getFitWidth())) || (dead && this.character.getLayoutX() < endx)){

            this.character.setLayoutX(this.character.getLayoutX() + 5);
            this.no_jump=true;
            this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
            this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_normal);
            this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
            this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::disableJump);

            switch (running_number) {

                case 1:
                    this.character.setImage(this.running1);
                    running_number = 2;


                    break;
                case 2:
                    this.character.setImage(this.running2);
                    running_number = 3;

                    break;
                case 3:
                    this.character.setImage(this.running3);
                    running_number = 4;

                    break;
                case 4:
                    this.character.setImage(this.running4);
                    running_number = 5;

                    break;
                case 5:
                    this.character.setImage(this.running5);
                    running_number = 6;

                    break;

                case 6:
                    this.character.setImage(this.running6);
                    running_number = 7;

                    break;

                case 7:
                    this.character.setImage(this.running7);
                    running_number = 1;

                    break;
            }

        }
        else{

            if (dead){
                try {
                    exit_routine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }else{
                this.running_animation_stopper();
                this.initial_image();

            }


        }


    }));
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
    private Timeline dying_timeline = new Timeline(new KeyFrame(Duration.seconds(0.005), event ->{

        if (this.character.getLayoutY() >= 800){
            try {
                change_to_end_screen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{

            this.character.setRotate(this.character.getRotate() + 2);
            this.character.setLayoutY(this.character.getLayoutY() + 2);

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
        this.no_jump=false;
        this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::disableJump);
        this.no_jump=false;

    }

    public void set_position(double x){
        this.character.setLayoutX(x);
    }
    public void running_animation(double endx){
        this.endx = endx;
        if (this.endx < this.blocks.getBonus().getLayoutX() + 13 && this.endx > this.blocks.getBonus().getLayoutX()){
            this.score_tracker.score_incrementer();
            try {
                touch_red();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }



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
    public void exit_routine() throws IOException, InterruptedException {
        this.blocks.stop();
        this.running_animation_stopper_2();
        this.revive.setVisible(true);
        this.blocks.getBonus().setVisible(false);

    }

    public void no_revive(){
        this.dead = true;
        this.revive.setVisible(false);
        try {
            fall_down();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        dying_timeline.setCycleCount(Timeline.INDEFINITE);
        dying_timeline.play();
    }

    public void choose_revive(){

        if (this.score_tracker.getBananas_score() >= 10){
            this.dead = false;
            this.character_status = "MOVE";
            this.score_tracker.bananas_used_revive();
            this.revive.setVisible(false);
            this.character.setLayoutX(this.blocks.getSecondary_block().getLayoutX());
            this.initial_image();
            this.stick.getStick().setHeight(0);
            this.blocks.switch_block_motion();

    }else{
        no_revive();
        }
    }


    public void change_to_end_screen() throws IOException {
        dying_timeline.stop();
        this.score_tracker.File_writer();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ending-view.fxml"));
        Parent root = loader.load();
        EndingController controller = loader.getController();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        controller.setStage(stage);

        stage.show();
        controller.setScore(this.score_tracker.getScore(),this.score_tracker.getHigh_score());

    }


    private void donkey_flip(KeyEvent event) {
        if (event.getCode() == KeyCode.DOWN && !this.dead) {
//            try {
//                play_flip();
//            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//                throw new RuntimeException(e);
//            }
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
        if (event.getCode() == KeyCode.UP && !isJumping && !this.dead && no_jump ) {
            isJumping = true;
            this.character_status = "JUMP";
            try {
                play_jump();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.6), this.character);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);

            TranslateTransition jumpTransition = new TranslateTransition(Duration.seconds(0.6), this.character);
            jumpTransition.setByY(-150);

            Timeline jumpTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(this.character.translateXProperty(), 0)),
                    new KeyFrame(Duration.seconds(0.3), new KeyValue(this.character.translateYProperty(), -150)),
                    new KeyFrame(Duration.seconds(0.6), new KeyValue(this.character.translateYProperty(), 0))
            );
            jumpTimeline.setOnFinished(e -> {
                isJumping = false;
                this.character_status = "MOVE";
                this.no_jump=false;

            });

            rotateTransition.play();
            jumpTransition.play();
            jumpTimeline.play();
        }
    }

    private void disableJump(KeyEvent event) {
        if (event.getCode() == KeyCode.UP && !this.no_jump) {}
    }


    public String getCharacter_status() {
        return character_status;
    }

    public void setRevive(Pane revive) {
        this.revive = revive;
    }

    public void setScore_tracker(Score_Tracking score_tracker) {
        this.score_tracker = score_tracker;
    }
    private void play_jump() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try{
            File file = new File("src/main/jump.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    private void fall_down() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try{
            File file = new File("src/main/gameover6.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
//            clip.drain();


        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    private void touch_red() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try{
            File file = new File("src/main/touch_red.wav");
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