package com.example.puzzlebites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Puzzle {

    public List<Piece> endPieces = new ArrayList<>();
    public List<Piece> switchPieces = new ArrayList();
    public List<Piece> pieces = new ArrayList<>();
    private Piece bagel;
    private List<Piece> allPieces;

    public List<Piece> getAllPieces(){
        if(allPieces == null){
            allPieces = new ArrayList<>();
            allPieces.addAll(endPieces);
            allPieces.addAll(switchPieces);
            allPieces.addAll(pieces);
        }
        return allPieces;
    }
    public Piece getBagel(){
        if(this.bagel == null) {
            for (Piece p : pieces) {
                if (p.type == "bagel") {
                    this.bagel = p;
                }
            }
        }
        return this.bagel;
    }
}
