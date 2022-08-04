package com.example.chessjava.engine.piece;

import com.example.chessjava.engine.board.ChessBoard;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

public class Knight extends Piece{
    public Knight(PieceColor pieceColor) {
        super(pieceColor, "N");
        validPositions.add(new ArrayList<>(Arrays.asList(2,1)));
        validPositions.add(new ArrayList<>(Arrays.asList(-2,1)));
        validPositions.add(new ArrayList<>(Arrays.asList(2,-1)));
        validPositions.add(new ArrayList<>(Arrays.asList(-2,-1)));
        validPositions.add(new ArrayList<>(Arrays.asList(1,2)));
        validPositions.add(new ArrayList<>(Arrays.asList(-1,2)));
        validPositions.add(new ArrayList<>(Arrays.asList(1,-2)));
        validPositions.add(new ArrayList<>(Arrays.asList(-1, -2)));
        this.value = 300;
    }

    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, ChessBoard chessBoard) {
        if(!super.isValidMove(x1, y1, x2, y2, chessBoard)) return false;
        int diff1 = abs(x2 - x1);
        int diff2 = abs(y2 - y1);
        if (diff1 == 1 && diff2 == 2) return true;
        if (diff1 == 2 && diff2 == 1) return true;
        return false;
    }

}
