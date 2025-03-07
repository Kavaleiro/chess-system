package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chessPieces.King;
import chessPieces.Rook;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;//Representa o tabuleiro do jogo  
    private boolean check;  

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        //Define o tamanho do tabuleiro 
        this.board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        check = false;
        intialSetup();
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean getCheck(){
        return check;
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

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();//Origem
        Position target = targetPosition.toPosition();//Destino 
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target); //responsável pelo movimento da peça

        //Verificando se o check será possível
        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;

        nextTurn();
        return (ChessPiece)capturedPiece;
        
    }

    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);//retira a peça na posiçaõ de origem
        Piece capturedPiece = board.removePiece(target);//remove uma possivel peça na posiçaõ de destino
        board.placePiece(p, target);
        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece;
    }
    private void undoMove(Position source, Position target, Piece capturedPiece){
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }


    private void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("The chosen piece is not yours");
        }
        //Se não tiver nenhum movimento possível eu lanço uma exceção 
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for choosen piece.");

        }
    }
    private void validateTargetPosition(Position source, Position target){
        if (!board.piece(source).possibleMoves(target)){
            throw new ChessException("The chosen piece can't move to target position.");
        }
    }

    private void nextTurn(){
        turn ++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color ){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list ) {
            if(p instanceof King){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck (Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        
        for (Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
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
