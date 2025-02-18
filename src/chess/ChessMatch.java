package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
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

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target); //responsável pelo movimento da peça
        return (ChessPiece)capturedPiece;
        
    }

    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);//retira a peça na posiçaõ de origem
        Piece capturedPiece = board.removePiece(target);//remove uma possivel peça na posiçaõ de destino
        board.placePiece(p, target);
        return capturedPiece;
    }


    private void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position");
        }
        //Se não tiver nenhum movimento possível eu lanço uma exceção 
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for choosen piece.");

        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    //responsaável por iniciar a partida de xadrez 
    private void intialSetup(){
        //placeNewPiece('b', 6, new Rook(board, Color.WHITE));
        //placeNewPiece('e', 8, new King(board, Color.BLACK));
        //placeNewPiece('e', 1, new King(board, Color.BLACK));
        //board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
        //board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        //board.placePiece(new King(board, Color.BLACK), new Position(7, 4));
        //////////////////////////////////////////////////////////////////////
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
    
}
