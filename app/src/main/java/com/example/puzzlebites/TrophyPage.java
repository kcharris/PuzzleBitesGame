package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TrophyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_page2);

        ActivityResultLauncher<Intent> trophyPageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult trophyPageExit) {
                        /*My plan is to use one ActivityResult for the exit button and
                            the back space (returnBTN) button
                          "Return" will be the returnBTN, else will be the exitBTN
                        */
                        int resultTrophy = trophyPageExit.getResultCode();
                        Intent trophyData = trophyPageExit.getData();
                    }
                }
        );
    }

    public void returnButton(View v){
        Intent trophy = new Intent(this, MainPageActivity.class);
        startActivity(trophy);
        finish();
    }

    public void exitButton(View v){
        Intent trophy = new Intent(this, MainPageActivity.class);
        startActivity(trophy);
        finish();
    }
}