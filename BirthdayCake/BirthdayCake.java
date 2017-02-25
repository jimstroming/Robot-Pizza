/*
It is your 30th birthday (congrats, by the way), and your friends bought you a 
cake with 30 candles on it. You make a wish and try to blow them out. Every time
you blow, you blow out a random number of candles between one and the number 
that remain, including one and that other number. How many times do you blow 
before all the candles are extinguished, on average?

*/



public final class BirthdayCake {

    private int maxcandles;
    private int numbercandles;
    private int numberblows;
    
    public BirthdayCake(int candlesoncake){
        maxcandles = candlesoncake;
        numbercandles = candlesoncake;
        numberblows = 0;
    }
    
    public int blowcandles(int numbercandlesblown){
        if (numberblows > 0) {
            numbercandles -= numbercandlesblown;
            numberblows += 1;
        }
        return numbercandles;
    }
    
    public void lightcake(){
        numbercandles = maxcandles;
    }
    
    public int getnumberblows(){
        return numberblows;
    }

}
