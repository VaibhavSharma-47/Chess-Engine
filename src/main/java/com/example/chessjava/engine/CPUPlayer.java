package com.example.chessjava.engine;

import com.example.chessjava.engine.board.ChessBoard;
import com.example.chessjava.engine.board.Move;
import com.example.chessjava.engine.piece.Piece;
import com.example.chessjava.engine.piece.PieceColor;
import com.example.chessjava.exception.InvalidMove;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.max;

public class CPUPlayer {
    ChessBoard chessBoard;
    public int depth;

    public CPUPlayer(ChessBoard chessBoard, int depth) {
        this.chessBoard = chessBoard;
        this.depth = depth;
    }

    public Move playMove(){
        return playMove(depth).getValue();
    };

    public Pair<Integer,Move> playMove(int depth){
        PieceColor turn = chessBoard.getTurn();
        if (depth == 0) return new Pair<>(getBoardValue(turn),null);
        Pair<Integer,Move> finalMove = new Pair<>(Integer.MIN_VALUE,null);
        ArrayList<Move> validMoves = generateValidMoves(chessBoard, turn);
        if(validMoves.size() == 0){
            if(chessBoard.isCheckMate()) return new Pair<>(Integer.MIN_VALUE,null);
            return new Pair<>(0,null);
        }
        for (Move validMove : validMoves) {
            Piece pieceAttacking = chessBoard.getPositionOnBoard().get(validMove.x1).get(validMove.y1);
            Piece pieceUnderAttack = chessBoard.getPositionOnBoard().get(validMove.x2).get(validMove.y2);
            chessBoard.getPositionOnBoard().get(validMove.x1).set(validMove.y1, null);
            chessBoard.getPositionOnBoard().get(validMove.x2).set(validMove.y2, pieceAttacking);
            Pair<Integer, Move> move;
            chessBoard.changeTurn();
            move = playMove(depth - 1);
            chessBoard.changeTurn();
            if (-move.getKey() > finalMove.getKey()) {
                finalMove = new Pair<>(-move.getKey(), new Move(validMove.x1, validMove.y1, validMove.x2, validMove.y2));
            }
            chessBoard.getPositionOnBoard().get(validMove.x2).set(validMove.y2, pieceUnderAttack);
            chessBoard.getPositionOnBoard().get(validMove.x1).set(validMove.y1, pieceAttacking);
        }

        //System.out.println("moves " + movesPlayed);
        return finalMove;
    }


    private ArrayList<Move> generateValidMoves(ChessBoard chessBoard, PieceColor turn) {
        ArrayList<Move> validMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = chessBoard.getPositionOnBoard().get(i).get(j);
                if (piece != null && piece.pieceColor == turn) {
                    for (ArrayList<Integer> validPos : piece.getValidPositions()) {
                        int moveX = i + validPos.get(0);
                        int moveY = j + validPos.get(1);
                        if (piece.isValidMove(i, j, moveX, moveY, chessBoard)) {
                            Piece pieceAttacking = chessBoard.getPositionOnBoard().get(i).get(j);
                            Piece pieceUnderAttack = chessBoard.getPositionOnBoard().get(moveX).get(moveY);
                            Move move = new Move(i,j,moveX,moveY);
                            try{
                                chessBoard.movePiece(move);
                                validMoves.add(move);
                            } catch (InvalidMove e) {
                            }
                            chessBoard.getPositionOnBoard().get(moveX).set(moveY, pieceUnderAttack);
                            chessBoard.getPositionOnBoard().get(i).set(j, pieceAttacking);
                        }
                    }
                }
            }
        }
        return validMoves;
    }
    private int getBoardValue(PieceColor turn) {
        int value = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = chessBoard.getPositionOnBoard().get(i).get(j);
                if (piece != null) {
                    if (turn == piece.pieceColor) value += piece.value;
                    else value -= piece.value;
                }
            }
        }
        //System.out.println(value);
        return value;
    }
}
