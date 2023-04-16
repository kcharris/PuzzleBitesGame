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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

public class PuzzleActivity extends AppCompatActivity {
    private scoreModel score;
    public Puzzle puzzle;
    Puzzles puzzles = new Puzzles(this);
    private HashSet<String> endLocations;
    private String puzzleStr;
    ConstraintLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.puzzleStr = getIntent().getExtras().getString("puzzle");
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
        myLayout = (ConstraintLayout) findViewById(R.id.puzzleActivity);
        setPuzzle(puzzleStr);

    }

    private ActivityResultLauncher<Intent> sStartLauncher;
    private void setPuzzle(String puzzleStr){
        if(!(this.puzzle == null)){
            for(Piece p: puzzle.getAllPieces()) {
                myLayout.removeViewInLayout(p);
            }
        }
        puzzle = puzzles.getPuzzle(puzzleStr);
        for (Piece p : puzzle.getAllPieces()) {
            myLayout.addView(p);
        }
        if(Global.lastMove.size() > 0){
            Global.lastMove = new ArrayList<>();
        }
        TextView puzzleMoves = findViewById(R.id.puzzleMovesTV);
        Global.moveCount = 0;
        puzzleMoves.setText("Move Count: " + Global.moveCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPuzzle(this.puzzleStr);
    }

    public void skipPuzzle(View v) {
        Global.moveCount = 0;
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
    public void moveGeneral(String direction){
        // This first part gets the next location of each moveable piece and store it in that pieces p.next(top/start)margin
        // The checker bool checks to see if a move tries to go outside it's bounds and if so prevents all movement
        // The locationHash checks to make sure no to pieces potentially overlap
        boolean checker = true;
        HashSet<String> locationsHash = new HashSet<>();
        for (Piece p : puzzle.pieces) {
            switch (direction){
                case "up":
                    checker = p.moveUp();
                    break;
                case "down":
                    checker = p.moveDown();
                    break;
                case "left":
                    checker = p.moveLeft();
                    break;
                case "right":
                    checker = p.moveRight();
                    break;
            }
            if (checker == false) break;
            locationsHash.add(p.nextStartMargin + ", " + p.nextTopMargin);
        }
//      If the pieces do not share an endlocation, go ahead and move
        if (locationsHash.size() == puzzle.pieces.size() && checker){
            Global.lastMove.add(direction);
            for(Piece p : puzzle.pieces){
                p.setMargins();
            }

            // if the bagel lands on a switch, try  and activate it as well as it's related pieces
            for(Piece sp : puzzle.switchPieces){
                if(puzzle.getBagel().nextTopMargin == sp.nextTopMargin && puzzle.getBagel().nextStartMargin == sp.nextStartMargin){
                    if (sp.isActive == false) {
                        sp.isActive = true;
                        sp.setImageResource(R.drawable.switchon);
                        for (Piece p : puzzle.pieces) {
                            if (sp.type.contains(p.type)) {
                                p.isActive = true;
                            }
                        }
                    }
                }
            }

            // Add to the score after moving
            TextView puzzleMoves = findViewById(R.id.puzzleMovesTV);
            Global.moveCount += 1;
            puzzleMoves.setText("Move Count: " + Global.moveCount);

            // Check for win condition after moving, and if true go to score screen with score
            if (endLocations == null){
                endLocations = new HashSet<>();
                for(Piece end : puzzle.endPieces){
                    endLocations.add(end.nextStartMargin + ", " + end.nextTopMargin);
                }
            }
            if(locationsHash.containsAll(endLocations)){
                Intent intent = new Intent(this, scoreScreen.class);
                intent.putExtra("score", Global.moveCount);
                sStartLauncher.launch(intent);
                finish();
            }

        }


    }
    public void undoMoveGeneral(String previousDirection){
        //Get the next location of each moveable piece
        for (Piece p : puzzle.pieces) {
            switch (previousDirection){
                case "up":
                    p.moveDown();
                    break;
                case "down":
                    p.moveUp();
                    break;
                case "left":
                    p.moveRight();
                    break;
                case "right":
                    p.moveLeft();
                    break;
            }
        }
        for(Piece p : puzzle.pieces){
            p.setMargins();
        }
        // if the bagel lands on a switch, try  and deactivate it as well as it's related pieces
        for(Piece sp : puzzle.switchPieces) {
            if (puzzle.getBagel().nextTopMargin == sp.nextTopMargin && puzzle.getBagel().nextStartMargin == sp.nextStartMargin) {
                if (sp.isActive == true) {
                    sp.isActive = false;
                    sp.setImageResource(R.drawable.switchoff);
                    for (Piece p : puzzle.pieces) {
                        if (sp.type.contains(p.type)) {
                            p.isActive = false;
                        }
                    }
                }
            }
        }
        // Reduce the score after moving
        Global.moveCount -= 1;
        TextView puzzleMoves = findViewById(R.id.puzzleMovesTV);
        puzzleMoves.setText("Move Count: " + Global.moveCount);
    }
    public void moveUp(View v) {
        moveGeneral("up");
    }

    public void moveDown(View v) {
        moveGeneral("down");
    }

    public void moveRight(View v) {
        moveGeneral("right");
    }

    public void moveLeft(View v) {
        moveGeneral("left");
    }

    public void undoBTN(View v){
        if (Global.lastMove.size() > 0){
            undoMoveGeneral(Global.lastMove.remove(Global.lastMove.size() - 1));
        }
    }
    public void resetPuzzle(View v){
        setPuzzle(puzzleStr);
    }

}