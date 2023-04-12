package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

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
    public int getPXFromDP(double dp){
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int)(dp * dm.density);
    }
    public void moveUp2(View v){
        ImageView mainBagelIV = findViewById(R.id.mainBagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) mainBagelIV.getLayoutParams();
        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;
        ImageButton puzzleBTN1 = findViewById(R.id.PuzzleBTN1);
        if(viewsOverlap(mainBagelIV, puzzleBTN1)) {
            toPuzzle(v);
        }
        if(vlp.topMargin != 0){
            setMargins(mainBagelIV, margin2, margin1- getPXFromDP(40), 0, 0);
        }

    }

    public void moveDown2(View v){
        ImageView mainBagelIV = findViewById(R.id.mainBagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) mainBagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.topMargin != getPXFromDP(40*8)){
            setMargins(mainBagelIV, margin2, margin1+ getPXFromDP(40), 0, 0);
        }
    }

    public void moveRight2(View v){
        ImageView mainBagelIV = findViewById(R.id.mainBagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) mainBagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.leftMargin != getPXFromDP(40*8)){
            setMargins(mainBagelIV, margin2+ getPXFromDP(40), margin1, 0, 0);
        }
    }

    public void moveLeft2(View v){
        ImageView mainBagelIV = findViewById(R.id.mainBagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) mainBagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.leftMargin != 0){
            setMargins(mainBagelIV, margin2- getPXFromDP(40), margin1, 0, 0);
        }
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    private boolean viewsOverlap(View v1, View v2) {
        int[] v1_coords = new int[2];
        v1.getLocationOnScreen(v1_coords);
        int v1_w = v1.getWidth();
        int v1_h = v1.getHeight();
        Rect v1_rect = new Rect(v1_coords[0], v1_coords[1], v1_coords[0] + v1_w, v1_coords[1] + v1_h);

        int[] v2_coords = new int[2];
        v2.getLocationOnScreen(v1_coords);
        int v2_w = v2.getWidth();
        int v2_h = v2.getHeight();
        Rect v2_rect = new Rect(v2_coords[0], v2_coords[1], v2_coords[0] + v2_w, v2_coords[1] + v2_h);

        return v1_rect.intersect(v2_rect) || v1_rect.contains(v2_rect) || v2_rect.contains(v1_rect);
    }

}