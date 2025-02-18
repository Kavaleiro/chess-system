package boardgame;

public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(){
        }

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    //Somente classe e subclasses dentro do mesmo pacote  irão poder acessar o tabuleiro de uma PEÇA
    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMoves(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();
        for (int i = 0; i<mat.length; i++){
            for(int j = 0; j<mat.length; j++){
                //Se for verdadeira.
                if (mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
