package com.example.puzzlebites.data.model;

import android.content.Context;
import android.graphics.Color;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.puzzlebites.R;
import com.example.puzzlebites.data.repository.SettingRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Setting {
    public int style = R.style.Theme_PuzzleBites_theme1;
    public int level1Score = 0;
    public int level1Star =0;
    public int level2Score = 0;
    public int level2Star =0;
    public int level3Score = 0;
    public int level3Star =0;
    public int level4Score = 0;
    public int level4Star =0;
    public int level5Score = 0;
    public int level5Star =0;

    public int getTotalScore(){
        return level1Score + level2Score + level3Score + level4Score + level5Score;
    }
    public int getTotalStars(){
        return level1Star + level2Star + level3Star + level4Star + level5Star;
    }

    public void setLevelScore(String level, int score, int stars){
        switch (level){
            case "one":
                level1Score = score;
                level1Star = stars;
                break;
            case "two":
                level2Score = score;
                level2Star = stars;
                break;
            case "three":
                level3Score = score;
                level3Star = stars;
                break;
            case "four":
                level4Score = score;
                level4Star = stars;
                break;
            case "five":
                level5Score = score;
                level5Star = stars;
                break;
        }
    }

    public int getScore(String level){
        switch (level){
            case "one":
                return level1Score;
            case "two":
                return level2Score;
            case "three":
                return level3Score;
            case "four":
                return level4Score;
            case "five":
                return level5Score;
            default:
                return -1;
        }
    }
    public int getStars(String level){
        switch (level){
            case "one":
                return level1Star;
            case "two":
                return level2Star;
            case "three":
                return level3Star;
            case "four":
                return level4Star;
            case "five":
                return level5Star;
            default:
                return -1;
        }
    }


    public static void applySettingToView(Context c){
        Setting s;
        s = new SettingRepository(c).getSettings();
        c.setTheme(s.style);
    }
}
