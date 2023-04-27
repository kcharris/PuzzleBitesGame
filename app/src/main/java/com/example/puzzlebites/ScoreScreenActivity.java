package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.puzzlebites.data.model.PieceType;
import com.example.puzzlebites.data.model.Score;
import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

public class ScoreScreenActivity extends AppCompatActivity {
    private ConstraintLayout myLayout;
    SettingRepository settingRepository;
    private Setting setting;
    private int goldThres;
    private int silverThres;
    private int bronzeThres;
    private PieceType puzzleEnum;
    private int stars;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySettings();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        myLayout = (ConstraintLayout) findViewById(R.id.scoreScreen);
        settingRepository = new SettingRepository(this);
        setting = settingRepository.getSettings();
        Intent intent = getIntent(); //this might be a problem, revisit later
        goldThres = intent.getExtras().getInt("gold");
        silverThres = intent.getExtras().getInt("silver");
        bronzeThres = intent.getExtras().getInt("bronze");
        puzzleEnum = PieceType.valueOf(intent.getExtras().getString("puzzleEnum"));
        score = intent.getExtras().getInt("score");

        setScoreTextViews();
        //setStars() will set the stars earned for the level and should complete before saving the score;
        setStars();
        saveScore();

    }
    public  void setScoreTextViews(){
        TextView congratsTV = findViewById(R.id.congratsTV);
        congratsTV.setText("Congrats!\n You completed the puzzle in " + score + " moves!");

        TextView goldStat = findViewById(R.id.goldTV);
        TextView bronzeStat = findViewById(R.id.bronzeTV);
        TextView silverStat = findViewById(R.id.silverTV);

        bronzeStat.setText(String.valueOf(bronzeThres) + " MOVES");
        silverStat.setText(String.valueOf(silverThres) + " MOVES");
        goldStat.setText(String.valueOf(goldThres) + " MOVES");

    }
    public void setStars(){
        ImageView bronze = findViewById(R.id.bronze);
        ImageView silver = findViewById(R.id.silver);
        ImageView gold = findViewById(R.id.gold);
        gold.setVisibility(View.INVISIBLE); bronze.setVisibility(View.INVISIBLE); silver.setVisibility(View.INVISIBLE);

        final Animation fadeIn0 = new AlphaAnimation(0.0F, 1.0F); fadeIn0.setStartOffset(500); fadeIn0.setDuration(1000);
        final Animation fadeIn1 = new AlphaAnimation(0.0F, 1.0F); fadeIn1.setStartOffset(1000); fadeIn1.setDuration(1000);
        final Animation fadeIn2 = new AlphaAnimation(0.0F, 1.0F); fadeIn2.setStartOffset(1500); fadeIn2.setDuration(1000);

        stars = 0;
        if (score <= bronzeThres) {
            bronze.startAnimation(fadeIn0);
            bronze.setVisibility(View.VISIBLE);
            stars = 1;
            Log.d("star 1", "One Star");
        }
        if (score <= silverThres) {
            silver.startAnimation(fadeIn1);
            silver.setVisibility(View.VISIBLE);
            stars = 2;
            Log.d("star 2", "Two Stars");
        }
        if (score <= goldThres) {
            gold.startAnimation(fadeIn2);
            gold.setVisibility(View.VISIBLE);
            stars = 3;
            Log.d("star 3", "Three Stars");
        }
    }
    public void saveScore(){

        int oldScore = setting.getScore(puzzleEnum);
        if(score < oldScore || oldScore == 0){
            setting.setLevelScore(puzzleEnum, score, stars);
        }
        settingRepository.saveSettings(setting);
        Log.d("Score!", "saveScore ran");
    }
    public void applySettings(){
        Setting.applySettingToView(this);
    }
    private ActivityResultLauncher<Intent> mpStartLauncher;

    public void returnBTN(View v) {
        Intent retryPuzzle = new Intent(this, PuzzleActivity.class);
        // This only needs to return, we are only checking if "returned" is an extra.
        setResult(Activity.RESULT_OK, retryPuzzle);
        finish();
    }

    // The exit button and the retry button do essentially the same thing
    public void exitBTN(View v) {
        Intent exitPuzzle = new Intent(this, PuzzleActivity.class);
        setResult(Activity.RESULT_OK, exitPuzzle);
        finish();
    }

    public void retryBTN(View v) {
        Intent retryPuzzle = new Intent(this, PuzzleActivity.class);
        retryPuzzle.putExtra("returned", "returned");
        setResult(Activity.RESULT_OK, retryPuzzle);
        finish();
    }
}
