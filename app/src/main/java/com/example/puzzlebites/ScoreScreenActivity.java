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
import android.widget.TextView;

import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

public class ScoreScreenActivity extends AppCompatActivity {
    private ConstraintLayout myLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        myLayout = (ConstraintLayout) findViewById(R.id.scoreScreen);
        applySettings();
        mpStartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {

                        }
                    }
                });
        Intent intent =  getIntent();
        TextView congratsTV = findViewById(R.id.congratsTV);
        String score = intent.getExtras().getInt("score") + "";
        congratsTV.setText("Congrats!\n Your score is\n" + score);
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