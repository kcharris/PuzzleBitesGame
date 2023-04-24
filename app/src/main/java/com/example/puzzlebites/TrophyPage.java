package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

public class TrophyPage extends AppCompatActivity {
    private ConstraintLayout myLayout;
    private Setting setting;
    private SettingRepository settingRepository;
    private int numOfStars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_page2);
        myLayout = (ConstraintLayout) findViewById(R.id.trophyPage);
        settingRepository = new SettingRepository(this);
        setting = settingRepository.getSettings();

        applySettings();
        ActivityResultLauncher<Intent> trophyPageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult trophyPageExit) {
                        // Not used
                    }
                }
        );
        setTextViews();
    }
    public void setStarImage(ImageView starIV, int starsEarned){
        if(starsEarned == 1) {
            starIV.setImageResource(R.drawable.bronzestar);
        }
        else if (starsEarned == 2){
            starIV.setImageResource(R.drawable.silverstar);
        }
        else if(starsEarned == 3){
            starIV.setImageResource(R.drawable.goldstar);
        }
        else{
            starIV.setImageResource(R.drawable.catend);
        }
    }
    public void setScoreButtonText(TextView btn,String level, int score){
        if(score == 0){
            btn.setText("Level " + level + ": not completed");
        }
        else{
            btn.setText("Level " + level + ": completed in " + score + " moves.");
        }
    }

    public void setTextViews(){
        int stars = 0;
        String level;
        int score;
        TextView numOfStarsTV = findViewById(R.id.totalStarsTV);
        numOfStars = setting.getTotalStars();
        numOfStarsTV.setText("You Have " + numOfStars + " Stars out of 15!");

        ImageView level1StarIV = findViewById(R.id.level1StarIV);
        TextView level1BTN = findViewById(R.id.lv1BTN);
        level = "one";
        stars = setting.getStars(level);
        score = setting.getScore(level);
        setStarImage(level1StarIV, stars);
        setScoreButtonText(level1BTN, level, score);

        ImageView level2StarIV = findViewById(R.id.level2StarIV);
        TextView level2BTN = findViewById(R.id.lv2BTN);
        level = "two";
        stars = setting.getStars(level);
        score = setting.getScore(level);
        setStarImage(level2StarIV, stars);
        setScoreButtonText(level2BTN, level, score);

        ImageView level3StarIV = findViewById(R.id.level3StarIV);
        TextView level3BTN = findViewById(R.id.lv3BTN);
        level = "three";
        stars = setting.getStars(level);
        score = setting.getScore(level);
        setStarImage(level3StarIV, stars);
        setScoreButtonText(level3BTN,level, score);

        ImageView level4StarIV = findViewById(R.id.level4StarIV);
        TextView level4BTN = findViewById(R.id.lv4BTN);
        level = "four";
        stars = setting.getStars(level);
        score = setting.getScore(level);
        setStarImage(level4StarIV, stars);
        setScoreButtonText(level4BTN,level, score);

        ImageView level5StarIV = findViewById(R.id.level5StarIV);
        TextView level5BTN = findViewById(R.id.lv5BTN);
        level = "five";
        stars = setting.getStars(level);
        score = setting.getScore(level);
        setStarImage(level5StarIV, stars);
        setScoreButtonText(level5BTN,level, score);
    }
    public void applySettings(){
        Setting.applySettingToView(myLayout);
    }

    public void returnButton(View v){
        Intent intent = new Intent(this, MainPageActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void exitButton(View v){
        Intent intent = new Intent(this, MainPageActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }
}