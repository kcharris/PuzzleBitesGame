package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PuzzleActivity extends AppCompatActivity {
    public scoreModel score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        score = new ViewModelProvider(this).get(scoreModel.class);
        score.getNumOfMovesString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView moves = findViewById(R.id.puzzleMovesTV);
                moves.setText(s);
            }
        });
        sStartLauncher = registerForActivityResult(
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
    public class pieces{
        boolean canMove;
        int x;
        int y;
        String type;
        public pieces(String typeStrc, boolean canMoveBool) {
            canMove = canMoveBool;
            x = 0;
            y = 0;
            type = "typeSTR";
        }

        //this class needs methods that will determine viable moves or not
        //methods can be called per piece to determine what pieces can and cannot move
        //move methods (two methods) 1. to check if a move can be made 2. one to actually move it

        public void moveUp(View v){
            ImageView bagelIV = findViewById(R.id.bagelIV);
            ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();
            int margin1 = vlp.topMargin;
            int margin2 = vlp.leftMargin;

            if(vlp.topMargin != 0){
                setMargins(bagelIV, margin2, margin1- getPXFromDP(40), 0, 0);
            }

        }

        public void moveDown(View v){
            ImageView bagelIV = findViewById(R.id.bagelIV);
            ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

            int margin1 = vlp.topMargin;
            int margin2 = vlp.leftMargin;

            if(vlp.topMargin != getPXFromDP(40*8)){
                setMargins(bagelIV, margin2, margin1+ getPXFromDP(40), 0, 0);
            }
        }

        public void moveRight(View v){
            ImageView bagelIV = findViewById(R.id.bagelIV);
            ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

            int margin1 = vlp.topMargin;
            int margin2 = vlp.leftMargin;

            if(vlp.leftMargin != getPXFromDP(40*8)){
                setMargins(bagelIV, margin2+ getPXFromDP(40), margin1, 0, 0);
            }
        }

        public void moveLeft(View v){
            ImageView bagelIV = findViewById(R.id.bagelIV);
            /*ImageButton puzzleBTN1 = findViewById(R.id.PuzzleBTN1);*/
            ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

            int margin1 = vlp.topMargin;
            int margin2 = vlp.leftMargin;
            /*if(puzzleBTN1.getLayoutParams().height == bagelIV.getHeight() && puzzleBTN1.getLayoutParams().width == bagelIV.getWidth()) {

            }*/
            if(vlp.leftMargin != 0){
                setMargins(bagelIV, margin2- getPXFromDP(40), margin1, 0, 0);
            }
        }
    }

    private ActivityResultLauncher<Intent> sStartLauncher;

    public void skipPuzzle(View v) {
        Intent puzzle = new Intent(this,scoreScreen.class);
        puzzle.putExtra("puzzle",0);
        setResult(Activity.RESULT_OK, puzzle);
        sStartLauncher.launch(puzzle);
        finish();
    }
    public void returnMain(View v)
    {
        Intent mainIntent = new Intent(this, MainPageActivity.class);
        mainIntent.putExtra("Return", 0);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }
    public int getPXFromDP(double dp){
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int)(dp * dm.density);
    }
    public void moveUp(View v){
        ImageView bagelIV = findViewById(R.id.bagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();
        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.topMargin != 0){
            setMargins(bagelIV, margin2, margin1- getPXFromDP(40), 0, 0);
        }

    }

    public void moveDown(View v){
        ImageView bagelIV = findViewById(R.id.bagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.topMargin != getPXFromDP(40*8)){
            setMargins(bagelIV, margin2, margin1+ getPXFromDP(40), 0, 0);
        }
    }

    public void moveRight(View v){
        ImageView bagelIV = findViewById(R.id.bagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.leftMargin != getPXFromDP(40*8)){
            setMargins(bagelIV, margin2+ getPXFromDP(40), margin1, 0, 0);
        }
    }

    public void moveLeft(View v){
        ImageView bagelIV = findViewById(R.id.bagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.leftMargin != 0){
            setMargins(bagelIV, margin2- getPXFromDP(40), margin1, 0, 0);
        }
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
    /*public void finishPuzzle(View v){
        Intent scoreIntent = new Intent(this, scoreScreen.class);
        startActivity(scoreIntent);
        finish();
    }*/
}