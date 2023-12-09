package com.example.stickman_project;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Bananas_tester{

    @Test
    public void bananas_tester() throws FileNotFoundException {
        Score_Tracking score_tracker = Score_Tracking.getInstance();
        int bananas = score_tracker.getBananas_score();
        score_tracker.bananas_used_revive();
        assertEquals(bananas - score_tracker.getBananas_score() , 10);
    }
}
