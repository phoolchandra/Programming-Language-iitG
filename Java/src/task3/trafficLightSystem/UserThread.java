package task3.trafficLightSystem;

import org.javatuples.Quartet;

import java.util.Date;

// Each user thread started when its arrival time occurs
public class UserThread extends Thread {
    private long counter = 0;   // contain value of current time
    @Override
    public void run() {
        counter = (Constant.programTime - Constant.startTime);      // current time value updated
        while (true) {
            // check through each user if their arrival time occured ang if it is then start thread for that user
            for (Quartet<Integer, String, String,Integer> user : Constant.allUser)
            {
                if ( user.getValue3() == counter) {  // check arrival time with counter
                    User userInfo = new User(user.getValue0(), user.getValue1(), user.getValue2(), user.getValue3());
                    userInfo.start();
                }
            }
            while (true){
                if(counter < (Constant.programTime - Constant.startTime)){
                    counter++;        // current time value updated
                    break;
                } else {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
