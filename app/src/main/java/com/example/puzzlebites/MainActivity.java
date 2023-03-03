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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> trophyPageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult trophyPageExit) {
                        /*My plan is to use one ActivityResult for the exit button and
                            the back space (returnBTN) button
                          "Exit" will be the exitBTN, else will be the returnBTN
                           */
                        int resultTrophy = trophyPageExit.getResultCode();
                        Intent trophyData = trophyPageExit.getData();
                        if(trophyData.getBooleanExtra("Return", false)) {

                        }
                    }
                }
        );


    }


/*
    public void settings(View v)
    {
        Intent settings = new Intent(this, setting.class);
        mStartForResult.launch(settings);
    }

 */
}