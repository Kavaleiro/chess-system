package chessPieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Criando a peça Rei representado por "K"
public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "K";
    }
}
