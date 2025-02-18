package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
//import boardgame.Board;
//import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Board board = new Board(8,8);
        ChessMatch chm = new ChessMatch();

        while (true){
            try {
                UI.clearScreen();
                UI.printBoard(chm.getPieces());
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chm.performChessMove(source, target);
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
    } 
}
