package com.example.puzzlebites.data.model;

import android.content.Context;
import android.graphics.Color;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.puzzlebites.data.repository.SettingRepository;

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

    public void setLevel1Score(int score) {
        level1Score = score;
    }
    public void setLevel2Score(int score) {
        level2Score = score;
    }
    public void setLevel3Score(int score) {
        level3Score = score;
    }
    public void setLevel4Score(int score) {
        level4Score = score;
    }
    public void setLevel5Score(int score) {
        level5Score = score;
    }

    public static void applySettingToView(ConstraintLayout c){
        Setting s;
        s = new SettingRepository(c.getContext()).getSettings();
        c.setBackgroundColor(s.backgroundColor);
    }
}
