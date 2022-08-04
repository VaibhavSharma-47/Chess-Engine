package com.example.chessjava.engine.piece;

import com.example.chessjava.engine.board.ChessBoard;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

public class Bishop extends Piece{

    public Bishop(PieceColor pieceColor) {
        super(pieceColor, "B");
        for (int i = 0; i < 8; i++) {
            validPositions.add(new ArrayList<>(Arrays.asList(i, i)));
            validPositions.add(new ArrayList<>(Arrays.asList(i, -i)));
            validPositions.add(new ArrayList<>(Arrays.asList(-i, i)));
            validPositions.add(new ArrayList<>(Arrays.asList(-i, -i)));
        }
        this.value = 300;
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, ChessBoard chessBoard) {
        if(!super.isValidMove(x1, y1, x2, y2, chessBoard)) return false;
        Piece start = chessBoard.getPositionOnBoard().get(x1).get(y1);
        Piece end = chessBoard.getPositionOnBoard().get(x2).get(y2);
        if (abs(x2 - x1) == abs(y2 - y1)) {
            int xDir = x2 > x1 ? 1 : -1;
            int yDir = y2 > y1 ? 1 : -1;
            for (int i = x1, j = y1; i != x2 && j != y2; i += xDir, j += yDir) {
                Piece piece = chessBoard.getPositionOnBoard().get(i).get(j);
                if (!(piece == null || piece == start || piece == end)) return false;
            }
            return true;
        }
        return false;
    }
}
