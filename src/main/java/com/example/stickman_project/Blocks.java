package com.example.stickman_project;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Blocks {
    private Random rand = new Random();
    public Rectangle make_block(AnchorPane p){
        Rectangle block = new Rectangle();
        int x = 380 + rand.nextInt(700);
        block.setLayoutX(x);block.setLayoutY(524);block.setWidth(rand.nextInt(100,500));
        p.getChildren().add(block);
        return block;
    }
    public void red_block_setter(){

    }
}
