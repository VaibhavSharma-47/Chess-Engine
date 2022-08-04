package com.example.chessjava.engine.piece;

import com.example.chessjava.engine.board.ChessBoard;


public class Queen extends Piece {
    Bishop bishop ;
    Rook rook ;

    public Queen(PieceColor pieceColor) {
        super(pieceColor, "Q");
        this.bishop = new Bishop(pieceColor);
        this.rook = new Rook(pieceColor);
        this.validPositions.addAll(this.bishop.validPositions);
        this.validPositions.addAll(this.rook.validPositions);
        this.value = 900;
    }

    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, ChessBoard chessBoard) {
        if(!super.isValidMove(x1, y1, x2, y2, chessBoard)) return false;
        if(bishop.isValidMove(x1, y1, x2, y2, chessBoard) || rook.isValidMove(x1, y1, x2, y2, chessBoard)) return true;
        return false;
    }
}
