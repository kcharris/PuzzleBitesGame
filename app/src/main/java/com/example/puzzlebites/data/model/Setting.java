package com.example.puzzlebites.data.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Setting {
    public int backgroundColor = Color.LTGRAY;
    public int level1Score = 0;
    public int level2Score = 0;
    public int level3Score = 0;
    public int level4Score = 0;
    public int level5Score = 0;

    public int getTotalScore(){
        return level1Score + level2Score + level3Score + level4Score + level5Score;
    }

}
