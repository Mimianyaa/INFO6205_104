package edu.neu.coe.info6205.mymatrix;


public class GameException extends RuntimeException {

    private static final long serialVersionUID = -3761677827337747980L;
    
    public GameException(String message) {
        super(message);
    }
    
    public GameException(Throwable cause) {
        super(cause);
    }
    
    public GameException(String message, Throwable cause) {
        super(message, cause);
    }

}
