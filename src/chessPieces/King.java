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

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
        //throw new UnsupportedOperationException("Unimplemented method 'possibleMoves'");
    }
}
