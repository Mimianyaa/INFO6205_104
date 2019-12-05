package edu.neu.coe.info6205.ga;

import java.util.Random;


public class Mutation {
    /**
     * performs swap mutation on an array of ints
     */
    public static int[] swapMutation(int[] parent){
        int[] array = parent.clone();
        int l = array.length;
        //get 2 random integers between 0 and size of array
        int r1 = randomNumber(0,l);
        int r2 = randomNumber(0,l);
        //to make sure the 2 numbers are different
        while(r1 == r2) r2 = randomNumber(0,l);

        //swap array elements at those indices
        int temp = array[r1];
        array[r1] = array[r2];
        array[r2] = temp;

        return array;
    }
    private static int randomNumber(int min , int max) {
        Random r = new Random();
        double d = min + r.nextDouble() * (max - min);
        return (int)d;
    }

    public static Character mutate(Character c) {
        Random random = new Random();
        int i = random.nextInt(100);
        if (i < 2) {
            if (c.equals('1')) {
                c = '0';
            } else if (c.equals('0')) {
                c = '1';
            }
        }
        return c;
    }
}
