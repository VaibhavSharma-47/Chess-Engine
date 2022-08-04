package com.example.chessjava.engine.piece;

import com.example.chessjava.engine.board.ChessBoard;

import java.util.ArrayList;

public class Piece {
    public PieceColor pieceColor;
    public String name;
    public int value;

    public ArrayList<ArrayList<Integer>> getValidPositions() {
        return validPositions;
    }

    public ArrayList<ArrayList<Integer>> validPositions;

    public boolean isValidMove(int x1, int y1, int x2, int y2,ChessBoard chessBoard) {
        return isInsideBoard(x1) && isInsideBoard(y1) && isInsideBoard(x2) && isInsideBoard(y2) &&
                hasPositionChanged(x1, y1, x2, y2) &&
                !isSuicide(x1, y1, x2, y2, chessBoard);
    }
    private boolean isInsideBoard(int position){
        return position >= 0 && position <= 7;
    }

    private boolean hasPositionChanged(int x1, int y1, int x2, int y2){
        return !(x1 == x2 && y1 == y2);
    }

    private boolean isSuicide(int x1, int y1, int x2, int y2,ChessBoard chessBoard){
        Piece start = chessBoard.getPositionOnBoard().get(x1).get(y1);
        PieceColor startTurn = start.pieceColor;

        Piece end = chessBoard.getPositionOnBoard().get(x2).get(y2);
        if (end == null) return false;
        PieceColor endTurn = end.pieceColor;
        return startTurn == endTurn;
    }

    public Piece(PieceColor pieceColor, String name) {
        this.pieceColor = pieceColor;
        this.name = name;
        this.validPositions = new ArrayList<>();
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

}
