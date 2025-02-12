package boardgame;


public class Board {

    private Integer rows;
    private Integer columns;
    //Matriz de peças
    private Piece[][] pieces;

    //Aqui é onde o tamnho do tabuleiro é definido.
    public Board(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }    
    
    public Integer getRows() {
        return rows;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }
    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    // Retorna a peça na posição (row, column) do tabuleiro
    public Piece piece(Integer row, Integer column){
        return pieces[row][column];
    }

    // Retorna a peça em uma posição específica usando o objeto Position
    public Piece piece(Position position){
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position){
        //"pieces" foi instânciada neste construtor
        pieces[position.getRow()][position.getColumn()] = piece;
        //Mostrando que a peça não está mais na posiçaõ nula 
        piece.position = position;
    }

}
