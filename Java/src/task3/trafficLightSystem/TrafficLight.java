package task3.trafficLightSystem;

import java.util.Date;

// update traffic light status continuously
public class TrafficLight extends Thread {
    @Override
    public void run() {
        Date startDate = new Date();
        Constant.startTrafficLightTime = startDate.getTime()/1000;   // initialised traffic light start time
        while(true)
        {
            Date date = new Date();
            if((date.getTime()/1000)-Constant.startTrafficLightTime >=60) {     // change traffic light after very 60sec
                if (Constant.greenTrafficlight == 3) {
                    Constant.startTrafficLightTime = (date.getTime() / 1000);
                    Constant.greenTrafficlight = 1;     // update traffic light green light on status
                } else {
                    Constant.startTrafficLightTime = (date.getTime() / 1000);       // update traffic light start time
                    Constant.greenTrafficlight++;       // update traffic light green light on status
                }
            }
        }
    }
}
