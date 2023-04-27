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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.puzzlebites.data.model.MoveTimer;
import com.example.puzzlebites.data.model.Piece;
import com.example.puzzlebites.data.model.PieceType;
import com.example.puzzlebites.data.model.Puzzle;
import com.example.puzzlebites.data.model.Puzzles;
import com.example.puzzlebites.data.model.Score;
import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

public class MainPageActivity  extends AppCompatActivity implements MoveTimer.MoveTimerInterface {
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
    public void disableThenReenableButtons(){
        Button upBTN = findViewById(R.id.puzzleUpBTN);
        Button rightBTN = findViewById(R.id.puzzleRightBTN);
        Button downBTN = findViewById(R.id.puzzleDownBTN);
        Button leftBTN = findViewById(R.id.puzzleLeftBTN);

        upBTN.setEnabled(false);
        rightBTN.setEnabled(false);
        downBTN.setEnabled(false);
        leftBTN.setEnabled(false);

        SettingRepository settingRepository = new SettingRepository(this);
        new MoveTimer(this, settingRepository.getSettings().animationMilliseconds);
    }
    public void reenableButtons(){
        Button upBTN = findViewById(R.id.puzzleUpBTN);
        Button rightBTN = findViewById(R.id.puzzleRightBTN);
        Button downBTN = findViewById(R.id.puzzleDownBTN);
        Button leftBTN = findViewById(R.id.puzzleLeftBTN);

        upBTN.setEnabled(true);
        rightBTN.setEnabled(true);
        downBTN.setEnabled(true);
        leftBTN.setEnabled(true);
        if(puzzle.checkLevelSelect()){
            PieceType puzzleEnum = puzzle.getOverlappedLevel();
            toNext(puzzleEnum);
        }
    }

    // For the following move functions. They attempt to move the pieces with moveGeneral, and ++Score on success.
    public void moveUp(View v) {
        if(puzzle.moveGeneral("up", MainPageActivity.this)){
            score.incrementNumOfMove();
            disableThenReenableButtons();
        }
    }

    public void moveDown(View v) {
        if(puzzle.moveGeneral("down", MainPageActivity.this)){
            score.incrementNumOfMove();
            disableThenReenableButtons();
        }
    }

    public void moveRight(View v) {
        if(puzzle.moveGeneral("right", MainPageActivity.this)){
            score.incrementNumOfMove();
            disableThenReenableButtons();
        }
    }

    public void moveLeft(View v) {
        if(puzzle.moveGeneral("left", MainPageActivity.this)){
            score.incrementNumOfMove();
            disableThenReenableButtons();
        }
    }
    // Attempts an undo, and if it works decrement the score
    public void undoBTN(View v){
        if(puzzle.undoMove()){
            disableThenReenableButtons();
        }
    }
    public void resetPuzzle(View v){
        setPuzzle(PieceType.LEVEL_MAIN);
    }

    public void toNext(PieceType puzzleEnum) {
        if(puzzleEnum == PieceType.LEVEL_TROPHY){
            Intent trophy = new Intent(this, TrophyPage.class);
            setResult(Activity.RESULT_OK, trophy);
            pStartLauncher.launch(trophy);
        }
        else {
            Intent puzzle = new Intent(this, PuzzleActivity.class);
            puzzle.putExtra("puzzleEnum", puzzleEnum.toString());
            setResult(Activity.RESULT_OK, puzzle);
            pStartLauncher.launch(puzzle);
        }
    }
    public void returnBTN(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }
}