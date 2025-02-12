package chessPieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Criando a peça Torre representado por "R"
public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }
}
