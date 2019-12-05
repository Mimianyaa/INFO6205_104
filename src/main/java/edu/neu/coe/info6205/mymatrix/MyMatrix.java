package edu.neu.coe.info6205.mymatrix;


public class MyMatrix {


    private int row;

    private int column;
    

    private Cell[][] cells;
    
    public MyMatrix(int row,int column) {
        this.row = row;
        this.column = column;
        this.cells = new Cell[row][column];
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void activateCell(int row,int column) {
        if(row < 0 || row >= this.row || column < 0 || column >= this.column) {
            throw new GameException("out of bound");
        }
        Cell cell = this.cells[row][column];
        cell.setState(State.ALIVE);
        plusAliveNeighborNum(row,column);
    }

    public void plusAliveNeighborNum(int row,int column) {
        plusAliveNeighborNum(this.cells,row,column);
    }

    public void plusAliveNeighborNum(Cell[][] copy,int row,int column) {
        for(int i=-1;i<=1;i++) {
            for(int j=-1;j<=1;j++) {
                if(row + i < 0 || row + i >= this.row || column + j < 0 || column + j >= this.column) {
                    continue;
                }
                if(i==0 && j==0) {
                    continue;
                }
                copy[row + i][column + j].plusAliveNeighborNum();
            }
        }
    }

    public MyMatrix judgeNextGeneration() {
        MyMatrix matrix = new MyMatrix(row,column);
        Cell[][] next = matrix.getCells(); 
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                Cell cell = this.cells[i][j];
                State state = cell.getState().judge(cell.getAliveNeighborNum());
                next[i][j].setState(state);
                if(state == State.ALIVE) {
                    plusAliveNeighborNum(next,i,j);
                }
            }
        }
        return matrix;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
