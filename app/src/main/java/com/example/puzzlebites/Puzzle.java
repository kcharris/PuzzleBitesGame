package com.example.puzzlebites;

import java.util.List;

public class Puzzle {

    public List<Piece> pieces;
    public int movablePieces;

    public Puzzle(List<Piece> pieces){
        this.pieces = pieces;

        //to implement
        movablePieces = 0;
        for(Piece p : pieces){
            if (p.canMove == true){
                movablePieces += 1;
            }
        }
    }
}
