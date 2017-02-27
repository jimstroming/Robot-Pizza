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
    private int numbercandles;   // two thread safe variables we need to protect
    private int numberblows;     // they are not independent
    
    public BirthdayCake(int candlesoncake){
        maxcandles = candlesoncake;
        numbercandles = candlesoncake;      // invariant: numbercandles >= 0
        numberblows = 0;
    }
    
    public synchronized int blowcandles(int numbercandlesblown){
        numbercandles -= numbercandlesblown; // potential race
        numberblows += 1;  // need the decrement in number of candles
                           // to be atomic with the increment in numberblows
        System.out.println(numbercandles);
        return numbercandles;
    }
    
    public synchronized void lightcake(){
        numbercandles = maxcandles;  // potential race
        numberblows = 0;    // need the resetting of number of candles to be
                            // atomic with the resetting of numberofblows
    }
    
    // also, can't have one thread lighting the cake while another thread
    // is blowing the candles.
    
    public synchronized int getnumberblows(){
        return numberblows;     // synchronized because reading numberblows
    }
    
    public void blowrandomcandles(){
        Random rand = new Random();
        synchronized(this){     // need to lock because we are reading numbercandles
            int randomnumbercandles =  rand.nextInt(numbercandles)+1;
            blowcandles(randomnumbercandles);
        }
    }
    
    public int blowoutcake(){
        lightcake();
        while (true){      // potential races.
            synchronized(this){
                if (numbercandles == 0) return numberblows;
                blowrandomcandles();        // numbercandles could change before we 
            }                            // call blowrandomcandles
        }
    }
    
    public static void main(String[] args){
        BirthdayCake cake = new BirthdayCake(300000);
        System.out.println(cake.blowoutcake());
    }

}
