package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//import boardgame.Board;
//import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Board board = new Board(8,8);
        ChessMatch chm = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        //# VVV # Enquanto a minha partida não esiver no CheckMate
        while (!chm.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chm, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chm.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chm.getPieces(), possibleMoves);
                

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chm.performChessMove(source, target);

                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }
            }
            catch(ChessException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(InputMismatchException e ){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chm, captured);
    } 
}
