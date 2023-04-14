package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
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
import android.widget.TextView;

public class MainPageActivity extends AppCompatActivity {
    public scoreModel score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        score = new ViewModelProvider(this).get(scoreModel.class);
        score.getNumOfMovesString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView moves = findViewById(R.id.movesTV);
                moves.setText(s);
            }
        });
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
        if(vlp.topMargin != 0){
            mainBagelIV.animate().setDuration(2000).translationY(margin1- getPXFromDP(40));
            //setMargins(mainBagelIV, margin2, margin1- getPXFromDP(40), 0, 0);
        }
        score.addNumOfMove();
        ImageView lvlOne = findViewById(R.id.lvlOne);
        if(viewsOverlap(mainBagelIV, lvlOne)) {
            toPuzzle(v);
        }ImageView lvlTwo = findViewById(R.id.lvlTwo);
        if(viewsOverlap(mainBagelIV, lvlTwo)) {
            toPuzzle(v);
        }ImageView lvlThree = findViewById(R.id.lvlThree);
        if(viewsOverlap(mainBagelIV, lvlThree)) {
            toPuzzle(v);
        }ImageView lvlFour = findViewById(R.id.lvlFour);
        if(viewsOverlap(mainBagelIV, lvlFour)) {
            toPuzzle(v);
        }ImageView lvlFive = findViewById(R.id.lvlFive);
        if(viewsOverlap(mainBagelIV, lvlFive)) {
            toPuzzle(v);
        }ImageView lvlSetting = findViewById(R.id.lvlSetting);
        if(viewsOverlap(mainBagelIV, lvlSetting)) {
            toPuzzle(v);
        }ImageView lvlTrophy = findViewById(R.id.lvlTrophy);
        if(viewsOverlap(mainBagelIV, lvlTrophy)) {
            toPuzzle(v);
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
        score.addNumOfMove();
        ImageView lvlOne = findViewById(R.id.lvlOne);
        if(viewsOverlap(mainBagelIV, lvlOne)) {
            toPuzzle(v);
        }ImageView lvlTwo = findViewById(R.id.lvlTwo);
        if(viewsOverlap(mainBagelIV, lvlTwo)) {
            toPuzzle(v);
        }ImageView lvlThree = findViewById(R.id.lvlThree);
        if(viewsOverlap(mainBagelIV, lvlThree)) {
            toPuzzle(v);
        }ImageView lvlFour = findViewById(R.id.lvlFour);
        if(viewsOverlap(mainBagelIV, lvlFour)) {
            toPuzzle(v);
        }ImageView lvlFive = findViewById(R.id.lvlFive);
        if(viewsOverlap(mainBagelIV, lvlFive)) {
            toPuzzle(v);
        }ImageView lvlSetting = findViewById(R.id.lvlSetting);
        if(viewsOverlap(mainBagelIV, lvlSetting)) {
            toPuzzle(v);
        }ImageView lvlTrophy = findViewById(R.id.lvlTrophy);
        if(viewsOverlap(mainBagelIV, lvlTrophy)) {
            toPuzzle(v);
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
        score.addNumOfMove();
        ImageView lvlOne = findViewById(R.id.lvlOne);
        if(viewsOverlap(mainBagelIV, lvlOne)) {
            toPuzzle(v);
        }ImageView lvlTwo = findViewById(R.id.lvlTwo);
        if(viewsOverlap(mainBagelIV, lvlTwo)) {
            toPuzzle(v);
        }ImageView lvlThree = findViewById(R.id.lvlThree);
        if(viewsOverlap(mainBagelIV, lvlThree)) {
            toPuzzle(v);
        }ImageView lvlFour = findViewById(R.id.lvlFour);
        if(viewsOverlap(mainBagelIV, lvlFour)) {
            toPuzzle(v);
        }ImageView lvlFive = findViewById(R.id.lvlFive);
        if(viewsOverlap(mainBagelIV, lvlFive)) {
            toPuzzle(v);
        }ImageView lvlSetting = findViewById(R.id.lvlSetting);
        if(viewsOverlap(mainBagelIV, lvlSetting)) {
            toPuzzle(v);
        }ImageView lvlTrophy = findViewById(R.id.lvlTrophy);
        if(viewsOverlap(mainBagelIV, lvlTrophy)) {
            toPuzzle(v);
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
        score.addNumOfMove();
        ImageView lvlOne = findViewById(R.id.lvlOne);
        if(viewsOverlap(mainBagelIV, lvlOne)) {
            toPuzzle(v);
        }ImageView lvlTwo = findViewById(R.id.lvlTwo);
        if(viewsOverlap(mainBagelIV, lvlTwo)) {
            toPuzzle(v);
        }ImageView lvlThree = findViewById(R.id.lvlThree);
        if(viewsOverlap(mainBagelIV, lvlThree)) {
            toPuzzle(v);
        }ImageView lvlFour = findViewById(R.id.lvlFour);
        if(viewsOverlap(mainBagelIV, lvlFour)) {
            toPuzzle(v);
        }ImageView lvlFive = findViewById(R.id.lvlFive);
        if(viewsOverlap(mainBagelIV, lvlFive)) {
            toPuzzle(v);
        }ImageView lvlSetting = findViewById(R.id.lvlSetting);
        if(viewsOverlap(mainBagelIV, lvlSetting)) {
            toPuzzle(v);
        }ImageView lvlTrophy = findViewById(R.id.lvlTrophy);
        if(viewsOverlap(mainBagelIV, lvlTrophy)) {
            toPuzzle(v);
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
        if (v1 == null || v2 == null) return false;
        final int[] view1Loc = new int[2];
        v1.getLocationOnScreen(view1Loc);
        final Rect view1Rect = new Rect(view1Loc[0],
                view1Loc[1],
                view1Loc[0] + v1.getWidth(),
                view1Loc[1] + v1.getHeight());
        int[] view2Loc = new int[2];
        v2.getLocationOnScreen(view2Loc);
        final Rect view2Rect = new Rect(view2Loc[0],
                view2Loc[1],
                view2Loc[0] + v2.getWidth(),
                view2Loc[1] + v2.getHeight());
        return view1Rect.intersect(view2Rect);
    }

    /*public boolean viableMove() {
    this is kind of useless because each move detects if it's viable before it activates
    }*/

}