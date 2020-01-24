package task3.trafficLightSystem;

import java.util.Date;

// update program current time in every 0.1sec
public class TimeThread extends Thread {

    @Override
    public void run() {
        while(true) {
            Date date = new Date();
            Constant.programTime = date.getTime()/1000;  // program current time updated
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
