package edu.neu.coe.info6205.mymatrix;


public interface MatrixWriter {

    int count(Cell[][] cells);

    /**
     * 打印
     * @param cells
     */
    public void write(Cell[][] cells);
}
