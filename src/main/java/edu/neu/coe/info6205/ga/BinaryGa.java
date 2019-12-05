package edu.neu.coe.info6205.ga;

import edu.neu.coe.info6205.life.base.Game;

/**
 * @author
 **/
public class BinaryGa {

    public static boolean fitness(String pattern) {
        return Game.run(0L, pattern).generation >= 1000;
    }
}
