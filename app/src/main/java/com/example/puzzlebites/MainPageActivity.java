package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends AppCompatActivity {
    public scoreModel score;
    public List<Piece> pieces = new ArrayList<>();
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
        Piece piece = new Piece(this, "bagel", 6,6);
        pieces.add(piece);
        this.addContentView(piece, new ViewGroup.LayoutParams(0, 0));
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
    public void moveUp2(View v){
        score.addNumOfMove();
        for (Piece p : pieces){
            p.moveUp();
        }
    }

    public void moveDown2(View v){

        score.addNumOfMove();
        for (Piece p : pieces){
            p.moveDown();
        }
    }

    public void moveRight2(View v){
        for (Piece p : pieces){
            p.moveRight();
        }
    }

    public void moveLeft2(View v){
        for (Piece p : pieces){
            p.moveLeft();
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
//    private checkForLevelSelect(View mg){
//        ImageView lvlOne = findViewById(R.id.lvlOne);
//        if(viewsOverlap(mg, lvlOne)) {
//            toPuzzle(1);
//        }ImageView lvlTwo = findViewById(R.id.lvlTwo);
//        if(viewsOverlap(mg, lvlTwo)) {
//            toPuzzle(2);
//        }ImageView lvlThree = findViewById(R.id.lvlThree);
//        if(viewsOverlap(mg, lvlThree)) {
//            toPuzzle(3);
//        }ImageView lvlFour = findViewById(R.id.lvlFour);
//        if(viewsOverlap(mg, lvlFour)) {
//            toPuzzle(4);
//        }ImageView lvlFive = findViewById(R.id.lvlFive);
//        if(viewsOverlap(mg, lvlFive)) {
//            toPuzzle(5);
//        }ImageView lvlSetting = findViewById(R.id.lvlSetting);
//        if(viewsOverlap(mg, lvlSetting)) {
//            toPuzzle();
//        }ImageView lvlTrophy = findViewById(R.id.lvlTrophy);
//        if(viewsOverlap(mg, lvlTrophy)) {
//            toPuzzle();
//        }
}
