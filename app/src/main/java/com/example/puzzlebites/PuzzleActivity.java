package com.example.puzzlebites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
    }
    public void returnMain(View v)
    {
        Intent mainIntent = new Intent(this, MainPageActivity.class);
        mainIntent.putExtra("Return", 0);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }
    public void finishPuzzle(View v){
        Intent scoreIntent = new Intent(this, scoreScreen.class);
        startActivity(scoreIntent);
        finish();
    }
}