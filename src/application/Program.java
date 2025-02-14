package application;

import java.util.Scanner;

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
            UI.printBoard(chm.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(sc);

            System.out.println();
            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(sc);

            ChessPiece capturedPiece = chm.performChessMove(source, target);
        }
    } 
}
