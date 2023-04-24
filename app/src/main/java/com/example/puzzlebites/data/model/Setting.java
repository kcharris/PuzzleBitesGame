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
    public int level1Star =0;
    public int level2Score = 0;
    public int level2Star =0;
    public int level3Score = 0;
    public int level3Star =0;
    public int level4Score = 0;
    public int level4Star =0;
    public int level5Score = 0;
    public int level5Star =0;
    public String star1;
    public String star2;
    public String star3;
    public String star4;
    public String star5;

    public int getTotalScore(){
        return level1Score + level2Score + level3Score + level4Score + level5Score;
    }
    public int getTotalStars(){
        return level1Star + level2Star + level3Star + level4Star + level5Star;
    }

    public void setLevel1Score(int score, String star) {
        level1Score = score;
        star = star1;
    }
    public void setLevel2Score(int score, String star) {
        level2Score = score;
        star = star2;
    }
    public void setLevel3Score(int score, String star) {
        level3Score = score;
        star = star3;
    }
    public void setLevel4Score(int score, String star) {
        level4Score = score;
        star = star4;
    }
    public void setLevel5Score(int score, String star) {
        level5Score = score;
        star = star5;
    }
    /*public int getLevel1Score() {
        return level1Score;
    }
    public Score getLevel2Score() {
        return score.;
    }
    public int getLevel3Score() {
        return level3Score;
    }
    public int getLevel4Score() {
        return level4Score;
    }
    public int getLevel5Score() {
        return level5Score;
    }*/
    public void setLevel1Star(int star) {
        level1Star = star;
    }
    public void setLevel2Star(int star) {
        level2Star = star;
    }
    public void setLevel3Star(int star) {
        level3Star = star;
    }
    public void setLevel4Star(int star) {
        level4Star = star;
    }
    public void setLevel5Star(int star) {
        level5Star = star;
    }

    public static void applySettingToView(ConstraintLayout c){
        Setting s;
        s = new SettingRepository(c.getContext()).getSettings();
        c.setBackgroundColor(s.backgroundColor);
    }
}
