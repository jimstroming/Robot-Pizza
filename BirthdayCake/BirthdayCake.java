/*
It is your 30th birthday (congrats, by the way), and your friends bought you a 
cake with 30 candles on it. You make a wish and try to blow them out. Every time
you blow, you blow out a random number of candles between one and the number 
that remain, including one and that other number. How many times do you blow 
before all the candles are extinguished, on average?

*/

import java.lang.Math;
import java.util.Random;

public final class BirthdayCake {

    private final int maxcandles;
    private int numbercandles;
    private int numberblows;
    
    public BirthdayCake(int candlesoncake){
        maxcandles = candlesoncake;
        numbercandles = candlesoncake;
        numberblows = 0;
    }
    
    public int blowcandles(int numbercandlesblown){
        numbercandles -= numbercandlesblown;
        numberblows += 1;
        System.out.println(numbercandles);
        return numbercandles;
    }
    
    public void lightcake(){
        numbercandles = maxcandles;
        numberblows = 0;
    }
    
    public int getnumberblows(){
        return numberblows;
    }
    
    public void blowrandomcandles(){
        Random rand = new Random();
        int randomnumbercandles =  rand.nextInt(numbercandles)+1;
        blowcandles(randomnumbercandles);
    }
    
    public int blowoutcake(){
        lightcake();
        while (numbercandles > 0){
            blowrandomcandles();
        }
        return numberblows;
    }
    
    public static void main(String[] args){
        BirthdayCake cake = new BirthdayCake(30);
        System.out.println(cake.blowoutcake());
    }

}
