package com.example.puzzlebites;

import android.content.Context;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzles {
    private Context context;
    public List<Piece> pieces = new ArrayList<>();

    Puzzles(Context context){
        this.context = context;
    }

    public Puzzle getPuzzle(String s){
        // for puzzles, order of input into the array matters
        // ealier entries will show behind other views in the layout
        s = "one"; // until other puzzles are created
        Puzzle p = new Puzzle();
        switch (s) {
            case "one":
                p.endPieces.add(new Piece(context, "end", 1, 1));
                p.endPieces.add(new Piece(context, "end", 3, 2));
                p.endPieces.add(new Piece(context, "end", 6, 1));
                p.endPieces.add(new Piece(context, "end", 7, 3));
                p.endPieces.add(new Piece(context, "end", 8, 2));

                p.switchPieces.add(new Piece(context, "plateSwitch", 1, 5));
                p.switchPieces.add(new Piece(context, "catSwitch", 3, 6));

                p.pieces.add(new Piece(context, "cat", 1, 3));
                p.pieces.add(new Piece(context, "cat", 3, 4));
                p.pieces.add(new Piece(context, "plate", 4, 1));
                p.pieces.add(new Piece(context, "plate", 5, 3));
                p.pieces.add(new Piece(context, "plate", 6, 2));
                p.pieces.add(new Piece(context, "bagel", 4, 6));
                break;
            case "two":
                break;
            case "three":
                break;
            case "four":
                break;
            case "five":
                break;
        }
        return p;
    }
}
