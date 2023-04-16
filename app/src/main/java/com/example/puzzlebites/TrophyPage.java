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
import android.widget.TextView;

import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

public class TrophyPage extends AppCompatActivity {
    private ConstraintLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_page2);
        myLayout = (ConstraintLayout) findViewById(R.id.trophyPage);
        applySettings();
        ActivityResultLauncher<Intent> trophyPageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult trophyPageExit) {
                        // Not used
                    }
                }
        );
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