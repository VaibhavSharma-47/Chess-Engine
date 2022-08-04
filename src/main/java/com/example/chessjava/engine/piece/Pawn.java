package com.example.chessjava.engine.piece;

import com.example.chessjava.engine.board.ChessBoard;

import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Piece{
    public Pawn(PieceColor pieceColor) {
        super(pieceColor,"P");
        this.validPositions = new ArrayList<>();
        int dir ;
        if(pieceColor == PieceColor.WHITE){
            dir = -1;
        }else{
            dir = 1;
        }
        validPositions.add(new ArrayList<>(Arrays.asList(dir,0,0)));
        validPositions.add(new ArrayList<>(Arrays.asList(dir,1,1)));
        validPositions.add(new ArrayList<>(Arrays.asList(dir,-1,1)));
        this.value = 100;
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, ChessBoard chessBoard){
        if(!super.isValidMove(x1, y1, x2, y2, chessBoard)) return false;
        int diffX = x2 -x1 ;
        int diffY = y2 - y1;
        for(ArrayList<Integer> validMove : validPositions){
            if(validMove.get(0) == diffX && validMove.get(1) == diffY){
                if(validMove.get(1) == 0 && chessBoard.getPositionOnBoard().get(x2).get(y2)== null) return true;
                else{
                    Piece start = chessBoard.getPositionOnBoard().get(x1).get(y1);
                    Piece end = chessBoard.getPositionOnBoard().get(x2).get(y2);
                    if(end == null) return false;
                    if(end.getPieceColor() != start.pieceColor) return true;
                }
            }
        }
        return false;
    }

}
