package task3.trafficLightSystem;

import java.util.Date;

public class LightSystem {

    private void startSystem() {
        Date date = new Date();
        Constant.startTime = date.getTime()/1000;       // program start time assigned value

        // program current time status updated continuously in this class
        TimeThread timeThread = new TimeThread();
        timeThread.start();

        // traffic light status is continuously update in this class
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.start();

        // start user thread when current program time become vehicles arrival time
        UserThread userThread = new UserThread();
        userThread.start();

        // here user interface is created and updated continuously
        UserInterface userInterface = new UserInterface();
        userInterface.start();
    }

    public static void main(String[]args){
        LightSystem lightSystem = new LightSystem();
        lightSystem.startSystem();
    }
}
