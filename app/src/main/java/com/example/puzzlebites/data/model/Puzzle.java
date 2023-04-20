package com.example.puzzlebites.data.model;

import com.example.puzzlebites.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Puzzle {
    public List<Piece> endPieces = new ArrayList<>();
    public List<Piece> switchPieces = new ArrayList();
    public List<Piece> levelPieces = new ArrayList();
    public List<Piece> pieces = new ArrayList<>();
    public List<String> moveList = new ArrayList<>();
    private HashSet endLocations;
    private HashSet locationsHash;
    private Piece bagel;
    private List<Piece> allPieces;

    public List<Piece> getAllPieces(){
        if(allPieces == null){
            allPieces = new ArrayList<>();
            allPieces.addAll(levelPieces);
            allPieces.addAll(endPieces);
            allPieces.addAll(switchPieces);
            allPieces.addAll(pieces);
        }
        return allPieces;
    }
    public Piece getBagel(){
        if(this.bagel == null) {
            for (Piece p : pieces) {
                if (p.type == PieceType.BAGEL) {
                    this.bagel = p;
                }
            }
        }
        return this.bagel;
    }
    public boolean moveGeneral(String direction){
        // This first part gets the next location of each moveable piece and store it in that pieces p.next(top/start)margin
        // The checker bool checks to see if a move tries to go outside it's bounds and if so prevents all movement
        // The locationHash checks to make sure no two pieces potentially overlap
        boolean checker = true;
        locationsHash = new HashSet<>();
        for (Piece p : this.pieces) {
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
        if (locationsHash.size() == this.pieces.size() && checker){
            moveList.add(direction);
            for(Piece p : this.pieces){
                p.setMargins();
            }

            // if the bagel lands on a switch, try  and activate it as well as it's related pieces
            for(Piece sp : this.switchPieces){
                if(this.getBagel().nextTopMargin == sp.nextTopMargin && this.getBagel().nextStartMargin == sp.nextStartMargin){
                    if (sp.isActive == false) {
                        sp.isActive = true;
                        sp.setImageResource(R.drawable.switchon);
                        for (Piece p : this.pieces) {
                            if (sp.type.toString().contains(p.type.toString())) {
                                p.isActive = true;
                            }
                        }
                    }
                }
            }
            // Check for win condition after moving, and if true go to score screen with score
            if (endLocations == null){
                endLocations = new HashSet<>();
                for(Piece end : this.endPieces){
                    endLocations.add(end.nextStartMargin + ", " + end.nextTopMargin);
                }
            }
            return true;
        }
        return false;
    }
    public boolean undoMove() {
        if (moveList.size() > 0) {
            String previousDirection = moveList.remove(moveList.size() - 1);

            //Get the next location of each moveable piece
            for (Piece p : this.pieces) {
                switch (previousDirection) {
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
            for (Piece p : this.pieces) {
                p.setMargins();
            }
            // if the bagel lands on a switch, try  and deactivate it as well as it's related pieces
            for (Piece sp : this.switchPieces) {
                if (this.getBagel().nextTopMargin == sp.nextTopMargin && this.getBagel().nextStartMargin == sp.nextStartMargin) {
                    if (sp.isActive == true) {
                        sp.isActive = false;
                        sp.setImageResource(R.drawable.switchoff);
                        for (Piece p : this.pieces) {
                            if (sp.type.toString().contains(p.type.toString())) {
                                p.isActive = false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
    public boolean isWinState(){
        return locationsHash.containsAll(endLocations);
    }
}
