package com.example.stickman_project;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Filetester {
    @Test
    public void file_reader() throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("src/main/game_state.txt"))); //Use of decorator pattern
        List<String> values = new ArrayList<String>();

        while (in.hasNext()){
            values.add(in.next());
        }

        assertEquals(4, values.size());
        in.close();
    }
}
