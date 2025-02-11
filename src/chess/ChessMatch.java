package chess;

import boardgame.Board;

public class ChessMatch {

    private Board board;//Representa o tabuleiro do jogo 

    public ChessMatch() {
        //Define o tamanho do tabuleiro 
        this.board = new Board(8,8);
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
    
}
