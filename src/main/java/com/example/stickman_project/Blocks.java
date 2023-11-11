package com.example.stickman_project;

import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Blocks {
    private Random rand = new Random();
    public Rectangle make_block(){
        Rectangle block = new Rectangle();
        int x = 200 + rand.nextInt(300);
        int y = 400 + rand.nextInt(250);
        block.setX(x);block.setY(y);
        block.setWidth(50 + rand.nextInt(100));
        block.setHeight(750 - y);
        return block;
    }
}
