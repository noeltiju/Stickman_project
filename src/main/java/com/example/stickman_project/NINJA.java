//package com.example.stickman_project;
//
//import javafx.animation.*;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.IOException;
//
//public class NINJA {
//    private Scene scene;
//    private Stage stage;
//    private boolean upsidedown = false;
//    int cherry_counter = 0;
//    int score = 0;
//    @FXML
//    ImageView character;
//    private Blocks blocks;
//    private Stick stick;
//    private int deaths = 0;
//    String character_status = "";
//    double endx = 0;
//    private Pane revive;
//    private Image running1 = new Image("donkeykong_running1.png");
//    private Image running2 = new Image("donkeykong_running2.png");
//    private Image running3 = new Image("donkeykong_running3.png");
//    private Image running4 = new Image("donkeykong_running4.png");
//    private Image running5 = new Image("donkeykong_running5.png");
//    private Image running6 = new Image("donkeykong_running6.png");
//    private Image running7 = new Image("donkeykong_running7.png");
//
//    private Image kicking1 = new Image("donkeykong_kicking1.png");
//    private Image kicking2 = new Image("donkeykong_kicking2.png");
//
//    private boolean dead = false;
//
//    int running_number = 1;int kicking_number = 1;
//
//    private Score_Tracking score_tracker;
//    private Timeline running_timeline = new Timeline(new KeyFrame(Duration.seconds(0.02), event ->{
//        if (endx >= this.blocks.getSecondary_block().getLayoutX() + this.blocks.getSecondary_block().getWidth() || endx <= this.blocks.getSecondary_block().getLayoutX()){
//            dead = true;
//        }
//
//        if (this.character.getLayoutX() + this.character.getFitWidth() >= this.blocks.getSecondary_block().getLayoutX()){
//            if (this.character_status == "DOWN"){
//                dead = true;
//                try {
//                    exit_routine();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//
//        if ((!dead && (character.getLayoutX() < (this.blocks.getSecondary_block().getLayoutX() +this.blocks.getSecondary_block().getWidth()) - this.character.getFitWidth())) || (dead && this.character.getLayoutX() < endx)){
//
//            this.character.setLayoutX(this.character.getLayoutX() + 5);
//            switch (running_number) {
//                case 1:
//                    this.character.setImage(this.running1);
//                    running_number = 2;
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_normal);
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
//
//                    break;
//                case 2:
//                    this.character.setImage(this.running2);
//                    running_number = 3;
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
//                    break;
//                case 3:
//                    this.character.setImage(this.running3);
//                    running_number = 4;
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
//                    break;
//                case 4:
//                    this.character.setImage(this.running4);
//                    running_number = 5;
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
//                    break;
//                case 5:
//                    this.character.setImage(this.running5);
//                    running_number = 6;
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
//                    break;
//
//                case 6:
//                    this.character.setImage(this.running6);
//                    running_number = 7;
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
//                    break;
//
//                case 7:
//                    this.character.setImage(this.running7);
//                    running_number = 1;
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
//                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
//                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_no_jump);
//                    break;
//            }
//
//        }
//        else{
//
//            if (dead){
//                try {
//                    exit_routine();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//            }else{
//                this.running_animation_stopper();
//                this.initial_image();
//
//            }
//
//
//        }
//
//
//    }));
//    private Timeline kicking_timeline = new Timeline(new KeyFrame(Duration.seconds(0.10), event ->{
//        switch (kicking_number) {
//            case 1:
//                this.character.setImage(this.kicking1);
//                this.kicking_number = 2;
//                break;
//            case 2:
//                this.character.setImage(this.kicking2);
//                this.kicking_number = 1;
//                break;
//
//        }
//
//    }));
//    private Timeline dying_timeline = new Timeline(new KeyFrame(Duration.seconds(0.005), event ->{
//        if (this.character.getLayoutY() >= 800){
//            try {
//                change_to_end_screen();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }else{
//            this.character.setRotate(this.character.getRotate() + 2);
//            this.character.setLayoutY(this.character.getLayoutY() + 2);
//        }
//
//    }));
//
//    public NINJA(ImageView character) {
//        this.character = character;
//        this.character.setImage(running1);
//        this.character_status = "MOVE";
//    }
//
//    public void initial_image(){
//        this.character.setImage(running1);
//        this.scene.addEventFilter(KeyEvent.KEY_PRESSED,this::donkey_normal);
//    }
//
//    public void set_position(double x){
//        this.character.setLayoutX(x);
//    }
//    public void running_animation(double endx){
//        this.endx = endx;
//
//        this.running_timeline.setCycleCount(Animation.INDEFINITE);
//        this.running_timeline.play();
//
//
//    }
//    public void running_animation_stopper(){
//        this.running_timeline.stop();
//        this.blocks.switch_block_motion();
//
//    }
//    public void running_animation_stopper_2(){
//        this.running_timeline.stop();
//
//    }
//    public void kicking_animation(){
//        this.kicking_timeline.setCycleCount(5);
//        this.kicking_timeline.play();
//
//    }
//    public void kicking_animation_stopper(){
//        this.kicking_timeline.stop();
//
//    }
//
//    public double get_position(){
//        return this.character.getLayoutX();
//    }
//    public ImageView get_character(){
//        return this.character;
//    }
//
//    public void setBlocks(Blocks blocks) {
//        this.blocks = blocks;
//    }
//
//    public void setStick(Stick stick) {
//        this.stick = stick;
//    }
//
//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }
//
//    public void setScene(Scene scene) {
//        this.scene = scene;
//    }
//    public void exit_routine() throws IOException, InterruptedException {
//        this.blocks.stop();
//        this.running_animation_stopper_2();
//        this.revive.setVisible(true);
//
//    }
//
//    public void no_revive(){
//        this.dead = true;
//        this.revive.setVisible(false);
//        dying_timeline.setCycleCount(Timeline.INDEFINITE);
//        dying_timeline.play();
//    }
//
//    public void choose_revive(){
//        this.dead = false;
//        this.character_status = "MOVE";
//        this.revive.setVisible(false);
//        this.character.setLayoutX(this.blocks.getSecondary_block().getLayoutX());
//        this.initial_image();
//        this.stick.getStick().setHeight(0);
//        this.blocks.switch_block_motion();
//    }
//
//
//    public void change_to_end_screen() throws IOException {
//        dying_timeline.stop();
//        this.score_tracker.File_writer();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("ending-view.fxml"));
//        Parent root = loader.load();
//        EndingController controller = loader.getController();
//
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        controller.setStage(stage);
//
//        stage.show();
//        controller.setScore(this.score_tracker.getScore(),this.score_tracker.getHigh_score());
//
//    }
//
//    private void donkey_flip(KeyEvent event) {
//        if (event.getCode() == KeyCode.DOWN && !this.dead) {
//            this.character_status = "DOWN";
//            this.character.setTranslateY(90);
//            this.character.setScaleY(-1);
//        }
//    }
//    private void donkey_normal(KeyEvent event){
//        if(event.getCode()==KeyCode.DOWN){
//            this.character_status = "MOVE";
//            this.character.setScaleY(1);
//            this.character.setTranslateY(0);
//        }
//    }
//
//
//    private boolean isJumping = false;
//
//    private void donkey_jump(KeyEvent event) {
//        if (event.getCode() == KeyCode.UP && !isJumping && !this.dead) {
//            isJumping = true;
//            this.character_status = "JUMP";
//
//            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.6), this.character);
//            rotateTransition.setFromAngle(0);
//            rotateTransition.setToAngle(360);
//            TranslateTransition jumpTransition = new TranslateTransition(Duration.seconds(0.6), this.character);
//            jumpTransition.setByY(-150);
//            ParallelTransition combo = new ParallelTransition(rotateTransition, jumpTransition);
//            combo.play();
//
//        }
//    }
//
//    private void donkey_no_jump(KeyEvent event) {
//        if (event.getCode() == KeyCode.UP) {
//
//            isJumping = false;
//            this.character_status = "MOVE";
//
//
//            TranslateTransition donkeyNoJump = new TranslateTransition(Duration.seconds(0.6), this.character);
//            donkeyNoJump.setToY(0);
//            donkeyNoJump.play();
//
//        }
//    }
//
//    public String getCharacter_status() {
//        return character_status;
//    }
//
//    public boolean getJumping(){
//
//        return this.isJumping;
//    }
//    public void setRevive(Pane revive) {
//        this.revive = revive;
//    }
//
//    public void setScore_tracker(Score_Tracking score_tracker) {
//        this.score_tracker = score_tracker;
//    }
//}

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

        import java.io.IOException;

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
            if (this.character_status == "DOWN"){
                dead = true;
                try {
                    exit_routine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if ((!dead && (character.getLayoutX() < (this.blocks.getSecondary_block().getLayoutX() +this.blocks.getSecondary_block().getWidth()) - this.character.getFitWidth())) || (dead && this.character.getLayoutX() < endx)){

            this.character.setLayoutX(this.character.getLayoutX() + 5);
            this.no_jump=true;
            switch (running_number) {
                case 1:
                    this.character.setImage(this.running1);
                    running_number = 2;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::disableJump);

                    break;
                case 2:
                    this.character.setImage(this.running2);
                    running_number = 3;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::disableJump);
                    break;
                case 3:
                    this.character.setImage(this.running3);
                    running_number = 4;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::disableJump);
                    break;
                case 4:
                    this.character.setImage(this.running4);
                    running_number = 5;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::disableJump);
                    break;
                case 5:
                    this.character.setImage(this.running5);
                    running_number = 6;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::disableJump);
                    break;

                case 6:
                    this.character.setImage(this.running6);
                    running_number = 7;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::disableJump);
                    break;

                case 7:
                    this.character.setImage(this.running7);
                    running_number = 1;
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_flip);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::donkey_normal);
                    this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::donkey_jump);
                    this.scene.addEventFilter(KeyEvent.KEY_RELEASED,this::disableJump);
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

    }

    public void no_revive(){
        this.dead = true;
        this.revive.setVisible(false);
        dying_timeline.setCycleCount(Timeline.INDEFINITE);
        dying_timeline.play();
    }

    public void choose_revive(){
        this.dead = false;
        this.character_status = "MOVE";
        this.revive.setVisible(false);
        this.character.setLayoutX(this.blocks.getSecondary_block().getLayoutX());
        this.initial_image();
        this.stick.getStick().setHeight(0);
        this.blocks.switch_block_motion();
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
            this.character_status = "DOWN";
            this.character.setTranslateY(90);
            this.character.setScaleY(-1);
            System.out.println("Donkey flip");
        }
    }
    private void donkey_normal(KeyEvent event){
        if(event.getCode()==KeyCode.DOWN){
            this.character_status = "MOVE";
            this.character.setScaleY(1);
            this.character.setTranslateY(0);
            System.out.println("Donkey Normal");
        }
    }


    private boolean isJumping = false;


    private void donkey_jump(KeyEvent event) {
        if (event.getCode() == KeyCode.UP && !isJumping && !this.dead && no_jump ) {
            isJumping = true;
            this.character_status = "JUMP";
            System.out.println("jumpingggg  on blockk");
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
        if (event.getCode() == KeyCode.UP && !this.no_jump) {
//            no_jump=true;
            System.out.println("Jumping on block");
        }
    }


    public String getCharacter_status() {
        return character_status;
    }

    public boolean getJumping(){

        return this.isJumping;
    }
    public void setRevive(Pane revive) {
        this.revive = revive;
    }

    public void setScore_tracker(Score_Tracking score_tracker) {
        this.score_tracker = score_tracker;
    }
}