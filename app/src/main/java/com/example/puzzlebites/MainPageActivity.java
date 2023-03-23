package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        pStartLauncher = registerForActivityResult(
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

         private ActivityResultLauncher<Intent> pStartLauncher;

    public void returnHome(View v)
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("Return", 0);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }

    public void toPuzzle(View v) {
        Intent puzzle = new Intent(this,PuzzleActivity.class);
        puzzle.putExtra("puzzle",0);
        setResult(Activity.RESULT_OK, puzzle);
        pStartLauncher.launch(puzzle);
        finish();
    }

    public void toTrophies(View v){
        Intent trophy = new Intent(this, TrophyPage.class);
        trophy.putExtra("trophy",0);
        setResult(Activity.RESULT_OK, trophy);
        pStartLauncher.launch(trophy);
        finish();
    }

}