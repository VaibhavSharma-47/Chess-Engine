package com.example.chessjava;

import com.example.chessjava.console.Menu;
import com.example.chessjava.engine.CPUPlayer;
import com.example.chessjava.engine.GameMode;
import com.example.chessjava.engine.board.ChessBoard;
import com.example.chessjava.engine.board.Move;

import java.util.List;

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        Menu menu = new Menu();
        GameMode gameMode = menu.getGameMode();
        if(gameMode == GameMode.MULTI){
            multiPlayerGame(chessBoard,menu);
        }else{
            singlePlayerGame(chessBoard,menu);
        }

    }
    private static void multiPlayerGame(ChessBoard chessBoard, Menu menu){
        while(true){
            menu.printBoard(chessBoard);
            try {
                var move = menu.takeInput(chessBoard);
                try {
                    if(!chessBoard.movePiece(move)) return;
                    chessBoard.changeTurn();
                } catch (Exception e) {
                    System.out.println("Invalid move");
                    e.printStackTrace();
                }
            }catch (Exception e) {
                System.out.println("Invalid input format");
                e.printStackTrace();
            }
        }
    }
    private static void singlePlayerGame(ChessBoard chessBoard, Menu menu){
        CPUPlayer cpuPlayer = new CPUPlayer(chessBoard, 2);
        while(true){
            menu.printBoard(chessBoard);
            try {
                Move move = menu.takeInput(chessBoard);
                try {
                    if(!chessBoard.movePiece(move)) return;
                    chessBoard.changeTurn();
                    Move cpuMove = cpuPlayer.playMove();
                    chessBoard.movePiece(cpuMove);
                    chessBoard.changeTurn();
                } catch (Exception e) {
                    System.out.println("Invalid move");
                    e.printStackTrace();
                }
            }catch (Exception e) {
                System.out.println("Invalid input format");
                e.printStackTrace();
            }
        }
    }
}
