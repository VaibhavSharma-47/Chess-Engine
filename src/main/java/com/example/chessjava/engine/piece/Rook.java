package com.example.chessjava.engine.piece;

import com.example.chessjava.engine.board.ChessBoard;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

public class Rook extends Piece{

    public Rook(PieceColor pieceColor) {
        super(pieceColor, "R");
        for (int i = 1; i <= 8; i++) {
            validPositions.add(new ArrayList<>(Arrays.asList(0,i)));
            validPositions.add(new ArrayList<>(Arrays.asList(i,0)));
            validPositions.add(new ArrayList<>(Arrays.asList(0,-i)));
            validPositions.add(new ArrayList<>(Arrays.asList(-i,0)));
        }
        this.value = 500;
    }

    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, ChessBoard chessBoard) {
        if (!super.isValidMove(x1, y1, x2, y2, chessBoard)) return false;
        Piece start = chessBoard.getPositionOnBoard().get(x1).get(y1);
        Piece end = chessBoard.getPositionOnBoard().get(x2).get(y2);
        if ((abs(x1 - x2) > 0 && y1 == y2)) {
            for (int a = max(x1,x2); a >= min(x1,x2); a--) {
                Piece piece = chessBoard.getPositionOnBoard().get(a).get(y1);
                if (!(piece == null || piece == start || piece == end)) return false;
            }
        } else if ((abs(y1 - y2) > 0 && x1 == x2)) {
            for (int a = max(y1,y2); a >= min(y1,y2); a--) {
                Piece piece = chessBoard.getPositionOnBoard().get(y1).get(a);
                if (!(piece == null || piece == start || piece == end)) return false;
            }
        } else{
            return false;
        }
        return true;
    }

}
