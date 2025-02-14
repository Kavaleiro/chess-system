package boardgame;

import exceptions.BoardException;

public class Board {

    private Integer rows;
    private Integer columns;
    //Matriz de peças
    private Piece[][] pieces;

    //Aqui é onde o tamnho do tabuleiro é definido.
    public Board(Integer rows, Integer columns) {
        //Programação defensiva
        if (rows < 1 || columns < 1){
            throw new BoardException("Error creating board: there must be at last 1 row an 1 column.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }    
    
    public Integer getRows() {
        return rows;
    }
    //Foi retirado o método setRows() para não deixar que seja alterado a quantida de linhas.

    public Integer getColumns() {
        return columns;
    }
    //Foi retirado o método setColumns() para não deixar que seja alterado a quantida de colunas. 

    // Retorna a peça na posição (row, column) do tabuleiro
    public Piece piece(Integer row, Integer column){
        //Programção defensiva 
        if (!positionExists(row, column)){
            throw new BoardException("Position not on the Board");
        } 
        return pieces[row][column];
    }

    // Retorna a peça em uma posição específica usando o objeto Position
    public Piece piece(Position position){
        //Programção defensiva
        if (!positionExists(position)){
            throw new BoardException("Position not on the Board");
        } 
        return pieces[position.getRow()][position.getColumn()];
    }

    //Coloca uma peça em uam dada posição 
    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException("Theres is alredy a piece on position."+ position);
        }
        //"pieces" foi instânciada neste construtor
        pieces[position.getRow()][position.getColumn()] = piece;
        //Mostrando que a peça não está mais na posiçaõ nula 
        piece.position = position;
    }

    //removendo uma Peça 
    public Piece removePiece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        //Peça do tabuleiro nesta posiçaõ é nulla 
        if (piece(position) == null) {
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    private boolean positionExists(int row, int column){
        return row >= 0 && row < rows && column >=0 && column < columns;

    }

    public boolean positionExists(Position position){
        return positionExists(position.getRow(),position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        //Programção defensiva
        if (!positionExists(position)){
            throw new BoardException("Position not on the Board");
        } 
        return piece(position) != null;
    }

}
