package com.example.stickman_project;

public class NINJA {
    private boolean upsidedown;int cherry_counter = 0;int score = 0;

    public NINJA(boolean upsidedown) {
        this.upsidedown = upsidedown;
    }

    public boolean isUpsidedown() {
        return upsidedown;
    }

    public void setUpsidedown(boolean upsidedown) {
        this.upsidedown = upsidedown;
    }

    public void running_animation(){

    }

    public void kicking_animation(){

    }

    public void upside_animation(){

    }

    public void setCherry_counter(int cherry_counter) {
        this.cherry_counter = cherry_counter;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
