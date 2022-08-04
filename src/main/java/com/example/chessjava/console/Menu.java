package com.example.chessjava.console;

import com.example.chessjava.engine.GameMode;
import com.example.chessjava.engine.board.ChessBoard;
import com.example.chessjava.engine.board.Move;
import com.example.chessjava.engine.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public void printBoard(ChessBoard chessBoard){
        ArrayList<ArrayList<Piece>> positionOnBoard = chessBoard.getPositionOnBoard();
        System.out.print(" ");
        for (int j = 0 ; j < positionOnBoard.get(0).size() ;j ++){
            System.out.print("| "+(char)('a'+j)+"|");
        }
        System.out.println();
        for(int i  = 0 ; i < positionOnBoard.size() ; i++){
            System.out.print(i+1);
            for (int j = 0 ; j < positionOnBoard.get(0).size() ;j ++){

                Piece piece = positionOnBoard.get(i).get(j);
                String pieceName;
                if (piece == null) {
                    pieceName = "  ";
                } else {
                    pieceName = piece.pieceColor.toString().substring(0, 1) + piece.name;
                }
                System.out.print("|"+pieceName+"|");
            }
            System.out.println();
            for (int j = 0 ; j < positionOnBoard.get(0).size() ;j ++){
                System.out.print("----");
            }
            System.out.println();
        }
    }

    public GameMode getGameMode(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter game mode Single player/ Multi Player (S/M) ?");
        String mode = scanner.nextLine();
        if(mode.equals("S")) return GameMode.SINGLE;
        else return GameMode.MULTI;
    }
    public Move takeInput(ChessBoard chessBoard){
        System.out.println(chessBoard.getTurn()+"'s turn");
        System.out.println("Please enter move");
        Scanner scanner = new Scanner(System.in);
        // 8a to 1c
        List<String> input = List.of(scanner.nextLine().split(" "));
        int x1 = input.get(0).charAt(0) - '1';
        int y1 = input.get(0).charAt(1) - 'a';
        int x2 = input.get(2).charAt(0) - '1';
        int y2 = input.get(2).charAt(1) - 'a';
        return new Move(x1,y1,x2,y2);
    }

}
