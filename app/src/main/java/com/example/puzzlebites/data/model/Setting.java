package com.example.puzzlebites.data.model;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.TimeUnit;

import com.example.puzzlebites.R;
import com.example.puzzlebites.data.repository.SettingRepository;

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
    public long animationMilliseconds = 800;
    public boolean soundToggle = true;

    public void setAnimationTime(long animationTimes) {
        animationTimes = animationMilliseconds;
    }
    public long getAnimationTime() {
        return animationMilliseconds;
    }

    public void moveTime() {
        try {
            TimeUnit.SECONDS.sleep(getAnimationTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalScore(){
        return level1Score + level2Score + level3Score + level4Score + level5Score;
    }
    public int getTotalStars(){
        return level1Star + level2Star + level3Star + level4Star + level5Star;
    }

    public void setLevelScore(PieceType level, int score, int stars){
        switch (level){
            case LEVEL1:
                level1Score = score;
                level1Star = stars;
                Log.d("TAG", "Level 1 star");
                break;
            case LEVEL2:
                level2Score = score;
                level2Star = stars;
                Log.d("TAG", "Level 2 star");
                break;
            case LEVEL3:
                level3Score = score;
                level3Star = stars;
                Log.d("TAG", "Level 3 star");
                break;
            case LEVEL4:
                level4Score = score;
                level4Star = stars;
                Log.d("TAG", "Level 4 star");
                break;
            case LEVEL5:
                level5Score = score;
                level5Star = stars;
                Log.d("TAG", "Level 5 star");
                break;
        }
    }

    public int getScore(PieceType level){
        switch (level){
            case LEVEL1:
                return level1Score;
            case LEVEL2:
                return level2Score;
            case LEVEL3:
                return level3Score;
            case LEVEL4:
                return level4Score;
            case LEVEL5:
                return level5Score;
            default:
                return -1;
        }
    }
    public int getStars(PieceType level){
        switch (level){
            case LEVEL1:
                return level1Star;
            case LEVEL2:
                return level2Star;
            case LEVEL3:
                return level3Star;
            case LEVEL4:
                return level4Star;
            case LEVEL5:
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
