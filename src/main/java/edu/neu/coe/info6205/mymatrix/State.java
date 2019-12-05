package edu.neu.coe.info6205.mymatrix;

public enum State {

    ALIVE{
        @Override
        public State judge(int aliveNeighborNum) {
            return aliveNeighborNum >= 2 && aliveNeighborNum <= 3 ? ALIVE : DEATH;
        }
    },
    DEATH{
        @Override
        public State judge(int aliveNeighborNum) {
            return aliveNeighborNum == 3 ? ALIVE : DEATH;
        }
    };
    

    public abstract State judge(int aliveNeighborNum);
}
