package com.example.chessjava.engine.board;

import com.example.chessjava.engine.piece.*;
import com.example.chessjava.exception.InvalidMove;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    ArrayList<ArrayList<Piece>> positionOnBoard;
    private PieceColor turn;
    public ChessBoard(){
        this.turn = PieceColor.WHITE;
        positionOnBoard = new ArrayList<>();
        ArrayList<Piece> rowOne = new ArrayList<>();
        rowOne.add(new Rook(PieceColor.BLACK));
        rowOne.add(new Knight(PieceColor.BLACK));
        rowOne.add(new Bishop(PieceColor.BLACK));
        rowOne.add(new Queen(PieceColor.BLACK));
        rowOne.add(new King(PieceColor.BLACK));
        rowOne.add(new Bishop(PieceColor.BLACK));
        rowOne.add(new Knight(PieceColor.BLACK));
        rowOne.add(new Rook(PieceColor.BLACK));
        positionOnBoard.add(rowOne);
        ArrayList<Piece> rowTwo = new ArrayList<>();
        rowTwo.add(new Pawn(PieceColor.BLACK));
        rowTwo.add(new Pawn(PieceColor.BLACK));
        rowTwo.add(new Pawn(PieceColor.BLACK));
        rowTwo.add(new Pawn(PieceColor.BLACK));
        rowTwo.add(new Pawn(PieceColor.BLACK));
        rowTwo.add(new Pawn(PieceColor.BLACK));
        rowTwo.add(new Pawn(PieceColor.BLACK));
        rowTwo.add(new Pawn(PieceColor.BLACK));
        positionOnBoard.add(rowTwo);
        for(int i = 0 ;i < 4 ; i++){
            ArrayList<Piece> row = new ArrayList<Piece>();
            for(int j = 0 ; j < 8 ; j ++){
                row.add(null);
            }
            positionOnBoard.add(row);
        }
        ArrayList<Piece> rowEight = new ArrayList<>();
        rowEight.add(new Pawn(PieceColor.WHITE));
        rowEight.add(new Pawn(PieceColor.WHITE));
        rowEight.add(new Pawn(PieceColor.WHITE));
        rowEight.add(new Pawn(PieceColor.WHITE));
        rowEight.add(new Pawn(PieceColor.WHITE));
        rowEight.add(new Pawn(PieceColor.WHITE));
        rowEight.add(new Pawn(PieceColor.WHITE));
        rowEight.add(new Pawn(PieceColor.WHITE));
        positionOnBoard.add(rowEight);
        ArrayList<Piece> rowNine = new ArrayList<>();
        rowNine.add(new Rook(PieceColor.WHITE));
        rowNine.add(new Knight(PieceColor.WHITE));
        rowNine.add(new Bishop(PieceColor.WHITE));
        rowNine.add(new Queen(PieceColor.WHITE));
        rowNine.add(new King(PieceColor.WHITE));
        rowNine.add(new Bishop(PieceColor.WHITE));
        rowNine.add(new Knight(PieceColor.WHITE));
        rowNine.add(new Rook(PieceColor.WHITE));
        positionOnBoard.add(rowNine);

    }

    public ArrayList<ArrayList<Piece>> getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Move piece from x1, y1 to x2, y2
     **/
    public boolean movePiece(Move move) throws InvalidMove {
        int x1 = move.x1;
        int y1 = move.y1;
        int x2 = move.x2;
        int y2 = move.y2;
        // TODO: Is valid move ?
        Piece piece = positionOnBoard.get(x1).get(y1);
        if (isValidMove(x1, y1, x2, y2)) {
            Piece underAttack = positionOnBoard.get(x2).get(y2);
            positionOnBoard.get(x2).set(y2, piece);
            positionOnBoard.get(x1).set(y1, null);
            try {
                if (isCheck()) {
                    if(isCheckMate()){
                        System.out.println("CheckMate");
                        return false;
                    }else{
                        System.out.println("Check");
                    };
                }
            } catch (InvalidMove e){
                positionOnBoard.get(x2).set(y2, underAttack);
                positionOnBoard.get(x1).set(y1, piece);
                throw new InvalidMove("Invalid move");
            }

        } else {
            throw new InvalidMove("Invalid move");
        }
        return true;
    }


    public boolean isCheck() throws InvalidMove {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isAttackingKing(i, j)) return true;
            }
        }
        return false;
    }

    public boolean isCheckMate(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canCancelCheck(i, j)) return false;
            }
        }
        return true;
    }
    private boolean canCancelCheck(int x, int y) {
        Piece piece = positionOnBoard.get(x).get(y);
        // Turn is of the player that can be under check
        if(piece == null || piece.pieceColor == turn) return false;
        for(ArrayList<Integer> validPos : piece.getValidPositions()){
            int moveX = x + validPos.get(0);
            int moveY = y + validPos.get(1);
            if (piece.isValidMove(x, y, moveX, moveY, this)) {
                Piece pieceAttacking = positionOnBoard.get(x).get(y);
                Piece pieceUnderAttack = positionOnBoard.get(moveX).get(moveY);
                positionOnBoard.get(moveX).set(moveY, pieceAttacking);
                positionOnBoard.get(x).set(y, null);
                try{
                    if(!isCheck()){
                        return true;
                    }
                } catch (InvalidMove e){

                }finally {
                    positionOnBoard.get(moveX).set(moveY, pieceUnderAttack);
                    positionOnBoard.get(x).set(y, pieceAttacking);
                }
            }
        }
        return false;
    }
    private boolean isAttackingKing(int x, int y) throws InvalidMove {
        Piece piece = positionOnBoard.get(x).get(y);
        // Turn is of the player that can be under check
        if(piece == null) return false;
        for(ArrayList<Integer> validPos : piece.getValidPositions()){
            int attackX = x + validPos.get(0);
            int attackY = y + validPos.get(1);
            if(piece.isValidMove(x,y,attackX,attackY,this)){
                Piece pieceUnderAttack = positionOnBoard.get(attackX).get(attackY);
                if(pieceUnderAttack instanceof King){
                    if(turn == pieceUnderAttack.pieceColor) throw new InvalidMove("King Under attack");
                    else return true;
                }
            }
        }
        return false;
    }

    public void setPositionOnBoard(ArrayList<ArrayList<Piece>> positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    public PieceColor getTurn() {
        return turn;
    }

    public void changeTurn() {
        if(turn == PieceColor.BLACK) turn = PieceColor.WHITE;
        else turn = PieceColor.BLACK;
    }

    private boolean isValidMove(int x1, int y1, int x2, int y2) {
        Piece piece = positionOnBoard.get(x1).get(y1);
        if(piece == null || piece.pieceColor != turn) return false;

        return piece.isValidMove(x1, y1, x2, y2, this);
    }


}
