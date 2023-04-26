package com.example.puzzlebites;

import static com.example.puzzlebites.data.model.PieceType.LEVEL1;
import static com.example.puzzlebites.data.model.PieceType.LEVEL2;
import static com.example.puzzlebites.data.model.PieceType.LEVEL3;
import static com.example.puzzlebites.data.model.PieceType.LEVEL4;
import static com.example.puzzlebites.data.model.PieceType.LEVEL5;

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

import com.example.puzzlebites.data.model.PieceType;
import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

public class TrophyPage extends AppCompatActivity {
    private ConstraintLayout myLayout;
    private Setting setting;
    private SettingRepository settingRepository;
    private int numOfStars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySettings();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_page2);
        myLayout = (ConstraintLayout) findViewById(R.id.trophyPage);
        settingRepository = new SettingRepository(this);
        setting = settingRepository.getSettings();
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
    public void setScoreButtonText(TextView btn,PieceType level, int score){
        if(score == 0){
            btn.setText(level.toString() + ": not completed");
        }
        else{
            btn.setText(level.toString() + ": completed in " + score + " moves.");
        }
    }

    public void setTextViews(){
        int stars;
        PieceType level;
        int score1;
        int score2;
        int score3;
        int score4;
        int score5;
        TextView numOfStarsTV = findViewById(R.id.totalStarsTV);
        numOfStars = setting.getTotalStars();
        numOfStarsTV.setText("You Have " + numOfStars + " Stars out of 15!");

        ImageView level1StarIV = findViewById(R.id.level1StarIV);
        TextView level1BTN = findViewById(R.id.lv1BTN);
        level = LEVEL1;
        stars = setting.getStars(level);
        score1 = setting.getScore(level);
        setStarImage(level1StarIV, stars);
        setScoreButtonText(level1BTN, level, score1);

        ImageView level2StarIV = findViewById(R.id.level2StarIV);
        TextView level2BTN = findViewById(R.id.lv2BTN);
        level = LEVEL2;
        stars = setting.getStars(level);
        score2 = setting.getScore(level);
        setStarImage(level2StarIV, stars);
        setScoreButtonText(level2BTN, level, score2);

        ImageView level3StarIV = findViewById(R.id.level3StarIV);
        TextView level3BTN = findViewById(R.id.lv3BTN);
        level = LEVEL3;
        stars = setting.getStars(level);
        score3 = setting.getScore(level);
        setStarImage(level3StarIV, stars);
        setScoreButtonText(level3BTN,level, score3);

        ImageView level4StarIV = findViewById(R.id.level4StarIV);
        TextView level4BTN = findViewById(R.id.lv4BTN);
        level = LEVEL4;
        stars = setting.getStars(level);
        score4 = setting.getScore(level);
        setStarImage(level4StarIV, stars);
        setScoreButtonText(level4BTN,level, score4);

        ImageView level5StarIV = findViewById(R.id.level5StarIV);
        TextView level5BTN = findViewById(R.id.lv5BTN);
        level = LEVEL5;
        stars = setting.getStars(level);
        score5 = setting.getScore(level);
        setStarImage(level5StarIV, stars);
        setScoreButtonText(level5BTN,level, score5);
    }
    public void applySettings(){
        Setting.applySettingToView(this);
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