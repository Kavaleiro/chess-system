package application;

import chess.ChessPiece;

public class UI {

    public static void printBoard(ChessPiece[][] pieces){
        for (int i = 0; i<pieces.length; i++){
            System.out.print((8 - i) + "  ");
            for(int j = 0; j<pieces.length;j++){
                //Botando par imprimir a peça 
                printPiece(pieces[i][j]);

            }
            System.out.println();
        }
        System.out.println("   a b c d e f g h");

    }

    //Método auxiliar para imprimir 1 peça
    private static void printPiece(ChessPiece piece){
        //Se ão tem peças, ele imprime o Hífen('-')
        if (piece == null){
            System.out.print("-");
        }
        else{
            //Imprime o símbolo da peça
            System.out.print(piece);
        }
        //Evita que as peças fique grudadas uams nas outras
        System.out.print(" ");
    }
    
}
