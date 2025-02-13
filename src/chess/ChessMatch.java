package chess;

import boardgame.Board;
import chessPieces.King;
import chessPieces.Rook;

public class ChessMatch {

    private Board board;//Representa o tabuleiro do jogo 

    public ChessMatch() {
        //Define o tamanho do tabuleiro 
        this.board = new Board(8,8);
        intialSetup();
    }

    //Retorna o estado atual do tabuleiro como uma matriz de peça
    public ChessPiece[][] getPieces(){
        //Verificando se a variável "board" está nula
        if (board == null) {
            throw new IllegalStateException("Erro: a variável board está nula!");
        }
        //Inicializa a matriz de peças com as dimensões do tabuleiro
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i<board.getRows(); i++){
            for(int j=0;j<board.getColumns();j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    //responsaável por iniciar a partida de xadrez 
    private void intialSetup(){
        placeNewPiece('b', 6, new Rook(board, Color.WHITE));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('e', 1, new King(board, Color.BLACK));
        //board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
        //board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        //board.placePiece(new King(board, Color.BLACK), new Position(7, 4));
    }
    
}
