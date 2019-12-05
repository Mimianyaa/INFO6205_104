package edu.neu.coe.info6205.mymatrix;


import edu.neu.coe.info6205.mymatrix.State;

public class Cell {

    private State state;

    private int aliveNeighborNum;

    public void plusAliveNeighborNum() {
        this.aliveNeighborNum++;
    }

    public void print() {
        if(getState() == State.ALIVE){
            System.out.print("■");
        }else{
            System.out.print("□");
        }
    }

    public boolean count() {
        return getState() == State.ALIVE;
    }
    
    public Cell() {
        this.state = State.DEATH;
        this.aliveNeighborNum = 0;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getAliveNeighborNum() {
        return aliveNeighborNum;
    }

    public void setAliveNeighborNum(int aliveNeighborNum) {
        this.aliveNeighborNum = aliveNeighborNum;
    }

}