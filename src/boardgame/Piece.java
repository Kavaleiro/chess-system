package boardgame;

public class Piece {

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
}
