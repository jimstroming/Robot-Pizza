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

*/