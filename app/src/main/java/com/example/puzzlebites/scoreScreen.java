package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class scoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
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
    }


    private ActivityResultLauncher<Intent> mpStartLauncher;

    public void returnBTN(View v) {
        Intent mainPageIntent = new Intent(this, MainPageActivity.class);
        mainPageIntent.putExtra("Return", 0);
        setResult(Activity.RESULT_OK, mainPageIntent);
        mpStartLauncher.launch(mainPageIntent);
        finish();
    }

    public void exitBTN(View v) {
        Intent mainPageIntent = new Intent(this, MainPageActivity.class);
        setResult(Activity.RESULT_OK, mainPageIntent);
        mpStartLauncher.launch(mainPageIntent);
        finish();
    }

    public void retryBTN(View v) {
        Intent retryPuzzle = new Intent(this, PuzzleActivity.class);
        setResult(Activity.RESULT_OK, retryPuzzle);
        mpStartLauncher.launch(retryPuzzle);
        finish();
    }
}



        // Code will be put here to handle where to go