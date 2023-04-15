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

    public List<Piece> getPuzzle1(){
        pieces = new ArrayList<>();
        pieces.add(new Piece(context, "bagel", 4, 6));
        pieces.add(new Piece(context, "switch", 3, 5));
        pieces.add(new Piece(context, "switch", 2, 6));
        pieces.add(new Piece(context, "cat", 3, 4));
        pieces.add(new Piece(context, "cat", 2, 4));
        pieces.add(new Piece(context, "plate", 4, 1));
        pieces.add(new Piece(context, "plate", 5, 4));
        pieces.add(new Piece(context, "plate", 6, 2));

        pieces.add(new Piece(context, "end", 1, 1));
        pieces.add(new Piece(context, "end", 3, 2));
        pieces.add(new Piece(context, "end", 6, 1));
        pieces.add(new Piece(context, "end", 7, 3));
        pieces.add(new Piece(context, "end", 8, 2));

        return pieces;
    }
}
