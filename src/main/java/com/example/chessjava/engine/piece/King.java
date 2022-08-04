package com.example.chessjava.engine.piece;

import com.example.chessjava.engine.board.ChessBoard;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

public class King extends Piece {
    public King(PieceColor pieceColor) {
        super(pieceColor,"K");
        this.validPositions.add(new ArrayList<>(Arrays.asList(1, 1)));
        this.validPositions.add(new ArrayList<>(Arrays.asList(0, 1)));
        this.validPositions.add(new ArrayList<>(Arrays.asList(-1, 1)));
        this.validPositions.add(new ArrayList<>(Arrays.asList(-1, 0)));
        this.validPositions.add(new ArrayList<>(Arrays.asList(-1, -1)));
        this.validPositions.add(new ArrayList <>(Arrays.asList(0, -1)));
        this.validPositions.add(new ArrayList<>(Arrays.asList(1, -1)));
        this.validPositions.add(new ArrayList<>(Arrays.asList(1, 0)));
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, ChessBoard chessBoard) {
        if(!super.isValidMove(x1, y1, x2, y2, chessBoard)) return false;
        if (abs(x1 - x2) <= 1 && abs(y2 - y1) <= 1) return true;
        return false;
    }
}
