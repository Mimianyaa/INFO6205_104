package edu.neu.coe.info6205.mymatrix;


public class ConsoleMatrixWriter implements MatrixWriter {

    @Override
    public int count(Cell[][] cells) {
        int count = 0;
        int row = cells.length;
        int col = cells[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (cells[i][j].count()) {
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    @Override
    public void write(Cell[][] cells) {
        int row = cells.length;
        int col = cells[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cells[i][j].print();
            }
            System.out.println();
        }
    }
}
