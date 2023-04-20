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
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

public class ScoreScreenActivity extends AppCompatActivity {
    private ConstraintLayout myLayout;
    private int goldThres;
    private int silverThres;
    private int bronzeThres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        myLayout = (ConstraintLayout) findViewById(R.id.scoreScreen);
        applySettings();
        TextView goldStat = findViewById(R.id.goldTV);
        TextView bronzeStat = findViewById(R.id.bronzeTV);
        TextView silverStat = findViewById(R.id.silverTV);
        ImageView bronze = findViewById(R.id.bronze);
        ImageView silver = findViewById(R.id.silver);
        ImageView gold = findViewById(R.id.gold);
        /*bronzeStat.setBackgroundResource(R.color.bronze);
        silverStat.setBackgroundResource(R.color.silver);
        goldStat.setBackgroundResource(R.color.gold);*/
        gold.setVisibility(View.INVISIBLE); bronze.setVisibility(View.INVISIBLE); silver.setVisibility(View.INVISIBLE);
        mpStartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                        }
                    }
                });
        Intent intent = getIntent();
        goldThres = intent.getExtras().getInt("gold");
        silverThres = intent.getExtras().getInt("silver");
        bronzeThres = intent.getExtras().getInt("bronze");
        TextView congratsTV = findViewById(R.id.congratsTV);
        String score = intent.getExtras().getInt("score") + "";
        congratsTV.setText("Congrats!\n You completed the puzzle in " + score + " moves!");
        final Animation fadeIn0 = new AlphaAnimation(0.0F, 1.0F); fadeIn0.setStartOffset(2000); fadeIn0.setDuration(2000);
        final Animation fadeIn1 = new AlphaAnimation(0.0F, 1.0F); fadeIn1.setStartOffset(3000); fadeIn1.setDuration(2000);
        final Animation fadeIn2 = new AlphaAnimation(0.0F, 1.0F); fadeIn2.setStartOffset(4000); fadeIn2.setDuration(2000);
        /*final Animation fadeLock0 = new AlphaAnimation(0.0F, 0.7F); fadeLock0.setStartOffset(2000); fadeLock0.setDuration(1500);
        final Animation fadeLock1 = new AlphaAnimation(0.0F, 0.7F); fadeLock1.setStartOffset(3000); fadeLock1.setDuration(1500);
        final Animation fadeLock2 = new AlphaAnimation(0.0F, 0.7F); fadeLock2.setStartOffset(4000); fadeLock2.setDuration(1500);*/
        if (Integer.parseInt(score) <= goldThres) { //want to animate a fade in and setColor according to stars/numOfMoves, should send intent with score thresholds for higher/increased adaptability
            bronzeStat.setBackgroundResource(R.color.bronze);
            silverStat.setBackgroundResource(R.color.silver);
            goldStat.setBackgroundResource(R.color.gold);
            bronze.startAnimation(fadeIn0);
            silver.startAnimation(fadeIn1);
            gold.startAnimation(fadeIn2);
            bronze.setVisibility(View.VISIBLE);
            silver.setVisibility(View.VISIBLE);
            gold.setVisibility(View.VISIBLE);
        }
        if ((Integer.parseInt(score) > goldThres) && (Integer.parseInt(score) <= silverThres)) {
            bronzeStat.setBackgroundResource(R.color.bronze);
            silverStat.setBackgroundResource(R.color.silver);
            bronze.startAnimation(fadeIn0);
            silver.startAnimation(fadeIn1);
            /*gold.startAnimation(fadeLock2);*/
            bronze.setVisibility(View.VISIBLE);
            silver.setVisibility(View.VISIBLE);
        }
        if ((Integer.parseInt(score) > silverThres) && (Integer.parseInt(score) <= bronzeThres)) {
            bronzeStat.setBackgroundResource(R.color.bronze);
            bronze.startAnimation(fadeIn0);
           /* silver.startAnimation(fadeLock1);
            gold.startAnimation(fadeLock2);*/
            bronze.setVisibility(View.VISIBLE);
        }
        else {
            /*bronze.startAnimation(fadeLock0);
            silver.startAnimation(fadeLock1);
            gold.startAnimation(fadeLock2);*/
        }
        /*bronze.setVisibility(View.VISIBLE);
        silver.setVisibility(View.VISIBLE);
        gold.setVisibility(View.VISIBLE);*/
    }
    public void applySettings(){
        Setting.applySettingToView(myLayout);
    }


    private ActivityResultLauncher<Intent> mpStartLauncher;

    public void returnBTN(View v) {
        Intent retryPuzzle = new Intent(this, PuzzleActivity.class);
        retryPuzzle.putExtra("returned", "returned");
        setResult(Activity.RESULT_OK, retryPuzzle);
        finish();
    }

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



        // Code will be put here to handle where to go