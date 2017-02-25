/* 

Can you save the drowning Swimmer?

From 
https://fivethirtyeight.com/features/can-you-save-the-drowning-swimmer/

You are a lifeguard standing on the beach, right at the edge of the water, 
and gazing out over the ocean. You see someone drowning 100 meters to the 
right of you and 100 meters away from shore. You can run 100 meters in 15 
seconds and swim 100 meters in 75 seconds. (The beach drops off steeply, 
meaning that you can not run in the water.) What is the fastest you can get 
to the victim?

*/
 
/*

What if we run 100 meters, then swim 100 meters?
trun = d/v =  100/(100/15) = 15 seconds
tswim = d/v = 100/(100/75) = 75 seconds
ttotal = 15+75 = 90 seconds

What if we just swim?
tswim = d/v = 100 sqrt(2)/(100/75) = 75 sqrt(2) = 106.1 seconds

Run (100-x), then swim.
trun = d/v = (100-x)/(100/15) = 15 - (15x/100)
tswim = d/v = sqrt(100^2 + x^2)/(100/75) = (75/100)sqrt(100^2 +x^2)

ttotal = 15 - (15x/100) + (75/100)sqrt(100^2+x^2)

To find the minimum, lets set the derivative to zero.

0 = -15/100 + (75/100)(1/2)(2x)(x^2+100^2)^(-1/2)
7500x = 15 sqrt(x^2 + 100^2)
500x = sqrt(x^2 + 100^2)
x^2 + 100^2 = (500)^2(x^2)
x^2 = 1/25
x = 1/5


*/

/* Let's try validating with a java simulation */

import java.lang.Math;


public final class DrowningSwimmer {

    private final double swimmingspeed;
    private final double runningspeed;
    private final double waterdistance;
    private final double landdistance;
    
    public DrowningSwimmer(double swimspeed, double runspeed, double waterdist, double landdist) {
        swimmingspeed = swimspeed;
        runningspeed = runspeed;
        waterdistance = waterdist;
        landdistance = landdist;
    }
    
    public double calchypotenuse(double x, double y){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    
    public double calctraveltime(double distance, double speed){
        return distance/speed;
    }

    public double calcrescuetime(double walkingdistance){
        double walkingtime = calctraveltime(walkingdistance, runningspeed);
        double swimmingdistance = calchypotenuse(waterdistance, landdistance-walkingdistance);
        double swimmingtime = calctraveltime(swimmingdistance, swimmingspeed);
        return walkingtime+swimmingtime;
    }
    
    public double calcoptimalrescuedistance(double increment){
        double x = 0;
        double mindistance = 0;
        double mintime = calcrescuetime(0);
        while (x < landdistance) {
            double currenttime = calcrescuetime(x);
            if (currenttime < mintime) {
                mintime = currenttime;
                mindistance = x;
            }    
            x += increment;
        }
        return mindistance;
    }

    public static void main(String[] args){
        DrowningSwimmer Swimmer = new DrowningSwimmer(100.0/75.0,100.0/15.0,100,100);
        
        System.out.println(Swimmer.calcrescuetime(12));
        System.out.println(Swimmer.calcrescuetime(1));
        System.out.println(Swimmer.calcrescuetime(99));
                              
        double optimaldistance = Swimmer.calcoptimalrescuedistance(0.01);
        System.out.println(optimaldistance);
        System.out.println(Swimmer.calcrescuetime(optimaldistance));

    }
}

/*  

Which outputs 

101.70495483208028
105.68701957133337
89.85374990625469
79.59000000000381
88.48469230405912

So, we should run 79.6 meters, which gives us an 88.5 second rescue

/*