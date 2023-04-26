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
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puzzlebites.data.model.Piece;
import com.example.puzzlebites.data.model.PieceType;
import com.example.puzzlebites.data.model.Puzzle;
import com.example.puzzlebites.data.model.Puzzles;
import com.example.puzzlebites.data.model.Score;
import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

import java.util.ArrayList;

public class MainPageActivity  extends AppCompatActivity {
    public Score score;
    private Puzzle puzzle;
    private Puzzles puzzles = new Puzzles(this);
    private ConstraintLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySettings();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        myLayout = (ConstraintLayout) findViewById(R.id.puzzleActivity);

        score = new ViewModelProvider(this).get(Score.class);
        score.getNumOfMovesString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView moves = findViewById(R.id.puzzleMovesTV);
                moves.setText(s);
            }
        });
        pStartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            setPuzzle(PieceType.LEVEL_MAIN);
                        }
                    }
                });
        setPuzzle(PieceType.LEVEL_MAIN);
    }
    public void applySettings(){
        Setting.applySettingToView(this);
    }

    private ActivityResultLauncher<Intent> pStartLauncher;
    private void setPuzzle(PieceType puzzleEnum) {
        TextView levelNameTV = findViewById(R.id.puzzleLevelTV);
        levelNameTV.setText("LEVEL MAIN");
        // If the puzzle already exists, remove it from view and then reset it. Removing from view may be unnecessary.
        if(!(this.puzzle == null)){
            for(Piece p: puzzle.getAllPieces()) {
                myLayout.removeViewInLayout(p);
            }
        }
        puzzle = puzzles.getPuzzle(puzzleEnum);
        for (Piece p : puzzle.getAllPieces()) {
            myLayout.addView(p);
        }
        score.reset();
    }

    // For the following move functions. They attempt to move the pieces with moveGeneral, and ++Score on success.
    public void moveUp(View v) {
        if(puzzle.moveGeneral("up")){
            score.incrementNumOfMove();
            checkForLevelSelect();
        }
    }

    public void moveDown(View v) {
        if(puzzle.moveGeneral("down")){
            score.incrementNumOfMove();
            checkForLevelSelect();
        }
    }

    public void moveRight(View v) {
        if(puzzle.moveGeneral("right")){
            score.incrementNumOfMove();
            checkForLevelSelect();
        }
    }

    public void moveLeft(View v) {
        if(puzzle.moveGeneral("left")){
            score.incrementNumOfMove();
            checkForLevelSelect();
        }
    }
    // Attempts an undo, and if it works decrement the score
    public void undoBTN(View v){
        if(puzzle.undoMove()){
            score.decrementNumOfMoves();
        }
    }
    public void resetPuzzle(View v){
        setPuzzle(PieceType.LEVEL_MAIN);
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

    public void toPuzzle(PieceType puzzleEnum) {
        Intent puzzle = new Intent(this, PuzzleActivity.class);
        puzzle.putExtra("puzzleEnum", puzzleEnum.toString());
        setResult(Activity.RESULT_OK, puzzle);
        pStartLauncher.launch(puzzle);
    }
    public void toTrophies() {
        Intent trophy = new Intent(this, TrophyPage.class);
        setResult(Activity.RESULT_OK, trophy);
        pStartLauncher.launch(trophy);
    }
    public void returnBTN(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }


    // Checks to see if the bagel piece lands on either a level or the trophy piece and if so moves to the respective Activity.
    private void checkForLevelSelect() {
        Piece bagel = puzzle.getBagel();
        ImageView lvlOne = findViewById(R.id.lvlOne);
        if (viewsOverlap(bagel, lvlOne)) {
            toPuzzle(PieceType.LEVEL1);
        }
        ImageView lvlTwo = findViewById(R.id.lvlTwo);
        if (viewsOverlap(bagel, lvlTwo)) {
            toPuzzle(PieceType.LEVEL2);
        }
        ImageView lvlThree = findViewById(R.id.lvlThree);
        if (viewsOverlap(bagel, lvlThree)) {
            toPuzzle(PieceType.LEVEL3);
        }
        ImageView lvlFour = findViewById(R.id.lvlFour);
        if (viewsOverlap(bagel, lvlFour)) {
            toPuzzle(PieceType.LEVEL4);
        }
        ImageView lvlFive = findViewById(R.id.lvlFive);
        if (viewsOverlap(bagel, lvlFive)) {
            toPuzzle(PieceType.LEVEL5);
        }
        ImageView lvlTrophy = findViewById(R.id.lvlTrophy);
        if (viewsOverlap(bagel, lvlTrophy)) {
            toTrophies();
        }

    /*public boolean viableMove() {
    this is kind of useless because each move detects if it's viable before it activates
    }*/
    }
}