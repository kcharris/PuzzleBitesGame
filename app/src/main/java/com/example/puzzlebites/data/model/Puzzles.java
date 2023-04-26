package com.example.puzzlebites.data.model;

import android.content.Context;

import static com.example.puzzlebites.data.model.PieceType.*;

import java.util.ArrayList;
import java.util.List;

public class Puzzles {
    private Context context;
    public List<Piece> pieces = new ArrayList<>();
    public Puzzles(Context context){
        this.context = context;
    }

    public Puzzle getPuzzle(PieceType pt){
        // for puzzles, order of input into the array matters
        // ealier entries will show behind other views in the layout
        // until other puzzles are created
        Puzzle p = new Puzzle();
        switch (pt) {
            case LEVEL_MAIN:
                p.levelPieces.add(new Piece(context, LEVEL1,1,6 ));
                p.levelPieces.add(new Piece(context, LEVEL2,1,3 ));
                p.levelPieces.add(new Piece(context, LEVEL3,3,2 ));
                p.levelPieces.add(new Piece(context, LEVEL4,5,2 ));
                p.levelPieces.add(new Piece(context, LEVEL5,7,3 ));
                p.levelPieces.add(new Piece(context, LEVEL_TROPHY,7,6 ));
                p.pieces.add(new Piece(context, BAGEL, 4, 6));
                break;
            case LEVEL1:
                p.endPieces.add(new Piece(context, END, 1, 1));
                p.endPieces.add(new Piece(context, END, 3, 2));
                p.endPieces.add(new Piece(context, END, 6, 1));
                p.endPieces.add(new Piece(context, END, 7, 3));
                p.endPieces.add(new Piece(context, END, 8, 2));

                p.switchPieces.add(new Piece(context, BEAR_SWITCH, 1, 5));
                p.switchPieces.add(new Piece(context, CAT_SWITCH, 3, 6));

                p.pieces.add(new Piece(context, CAT, 1, 3));
                p.pieces.add(new Piece(context, CAT, 3, 4));
                p.pieces.add(new Piece(context, BEAR, 4, 1));
                p.pieces.add(new Piece(context, BEAR, 5, 3));
                p.pieces.add(new Piece(context, BEAR, 6, 2));
                p.pieces.add(new Piece(context, BAGEL, 4, 6));

                p.bronzeThres = 17;
                p.silverThres = 12;
                p.goldThres = 8;
                break;
            case LEVEL2:
                p.endPieces.add(new Piece(context, END, 1, 1));
                p.endPieces.add(new Piece(context, END, 2, 3));
                p.endPieces.add(new Piece(context, END, 1, 6));
                p.endPieces.add(new Piece(context, END, 3, 7));
                p.endPieces.add(new Piece(context, END, 2, 8));

                p.switchPieces.add(new Piece(context, BEAR_SWITCH, 5, 1));
                p.switchPieces.add(new Piece(context, CAT_SWITCH, 6, 3));

                p.pieces.add(new Piece(context, CAT, 3, 1));
                p.pieces.add(new Piece(context, CAT, 4, 3));
                p.pieces.add(new Piece(context, BEAR, 1, 4));
                p.pieces.add(new Piece(context, BEAR, 3, 5));
                p.pieces.add(new Piece(context, BEAR, 2, 6));
                p.pieces.add(new Piece(context, BAGEL, 6, 4));

                p.bronzeThres = 17;
                p.silverThres = 12;
                p.goldThres = 8;
                break;
            case LEVEL3:
                p.endPieces.add(new Piece(context, END, 1, 1));
                p.endPieces.add(new Piece(context, END, 3, 2));
                p.endPieces.add(new Piece(context, END, 6, 1));
                p.endPieces.add(new Piece(context, END, 7, 3));
                p.endPieces.add(new Piece(context, END, 8, 2));

                p.switchPieces.add(new Piece(context, BEAR_SWITCH, 1, 5));
                p.switchPieces.add(new Piece(context, CAT_SWITCH, 3, 6));

                p.pieces.add(new Piece(context, CAT, 1, 3));
                p.pieces.add(new Piece(context, CAT, 3, 4));
                p.pieces.add(new Piece(context, BEAR, 4, 1));
                p.pieces.add(new Piece(context, BEAR, 5, 3));
                p.pieces.add(new Piece(context, BEAR, 6, 2));
                p.pieces.add(new Piece(context, BAGEL, 4, 6));

                p.bronzeThres = 17;
                p.silverThres = 12;
                p.goldThres = 8;
                break;
            case LEVEL4:
                p.endPieces.add(new Piece(context, END, 1, 1));
                p.endPieces.add(new Piece(context, END, 3, 2));
                p.endPieces.add(new Piece(context, END, 6, 1));
                p.endPieces.add(new Piece(context, END, 7, 3));
                p.endPieces.add(new Piece(context, END, 8, 2));

                p.switchPieces.add(new Piece(context, BEAR_SWITCH, 1, 5));
                p.switchPieces.add(new Piece(context, CAT_SWITCH, 3, 6));

                p.pieces.add(new Piece(context, CAT, 1, 3));
                p.pieces.add(new Piece(context, CAT, 3, 4));
                p.pieces.add(new Piece(context, BEAR, 4, 1));
                p.pieces.add(new Piece(context, BEAR, 5, 3));
                p.pieces.add(new Piece(context, BEAR, 6, 2));
                p.pieces.add(new Piece(context, BAGEL, 4, 6));

                p.bronzeThres = 17;
                p.silverThres = 12;
                p.goldThres = 8;
                break;
            case LEVEL5:
                p.endPieces.add(new Piece(context, END, 1, 1));
                p.endPieces.add(new Piece(context, END, 3, 2));
                p.endPieces.add(new Piece(context, END, 6, 1));
                p.endPieces.add(new Piece(context, END, 7, 3));
                p.endPieces.add(new Piece(context, END, 8, 2));

                p.switchPieces.add(new Piece(context, BEAR_SWITCH, 1, 5));
                p.switchPieces.add(new Piece(context, CAT_SWITCH, 3, 6));

                p.pieces.add(new Piece(context, CAT, 1, 3));
                p.pieces.add(new Piece(context, CAT, 3, 4));
                p.pieces.add(new Piece(context, BEAR, 4, 1));
                p.pieces.add(new Piece(context, BEAR, 5, 3));
                p.pieces.add(new Piece(context, BEAR, 6, 2));
                p.pieces.add(new Piece(context, BAGEL, 4, 6));

                p.bronzeThres = 17;
                p.silverThres = 12;
                p.goldThres = 8;
                break;
        }
        return p;
    }
}
