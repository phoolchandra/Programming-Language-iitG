package task3.trafficLightSystem;

import org.javatuples.Quartet;

public class User extends Thread {
    private int vehicleNumber;      // contain vehicle number of user
    private long waitTime;      // contain waiting time of vehicle
    private int arrivalTime;        // contain arrival time of vehicle
    private String sourceDirection;     // contain source direction of vehicle
    private String destinationDirection;        // contain destination direction of vehicle
    private int index;      // contain index in user list of vehicle
    private int numCarWait;     // contain number of car waiting to go from this source to destination of vehicle

    // user values initialised
    public  User(int vehicleNumber, String sourceDirection, String destinationDirection, int arrivalTime){
        this.vehicleNumber = vehicleNumber-1;
        this.sourceDirection = sourceDirection;
        this.destinationDirection = destinationDirection;
        this.arrivalTime = arrivalTime;
    }

    //  User status and wait time updated as per traffic light which is green
    private void updateUserDetails() {
        long time;      // contain time left for traffic light to change color
        Quartet<Integer, String, String,Integer> user = Constant.userDetails.get(this.index);       // user contain details of this specific user
        time = 60 - ( Constant.programTime - Constant.startTrafficLightTime );      // time value updated

        // check if user going from south to east
        if (user.getValue1().equalsIgnoreCase(Constant.southDirection) && user.getValue2().equalsIgnoreCase(Constant.eastDirection)) {
            if (Constant.greenTrafficlight == 1) {      // checks if traffic light on is T1
                if ( this.numCarWait > 0 ) {          // checks if there is any car waiting to go through same path
                    time = Constant.southEastExtraWait;
                    if (time < this.waitTime + 6) {     // checks if car can pass before light changes

                        long numCarExit = time/6;       // get number of car which can pass before traffic light changes
                        this.numCarWait -= numCarExit;      // subtract number of car which can go before traffic light changes from total car waiting to go through same path

                        // calculate wait time by by adding time for each car waiting to take 6sec each and only 10 car can pass in 60 sec of traffic light time and remaing car wait for 120 sec more
                        long carWait = time + 120 + (this.numCarWait*6) + (((this.numCarWait)/10)*120);
                        this.waitTime = carWait;
                    }
                    // vehicle status updated
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else if (this.waitTime == 0 && time < 6) {    // if car arrive after 54sec and so it cannot pass during this traffic light time
                    this.waitTime = time + 120;     // vehicle wait for 120sec
                    // vehicle status updated
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else {        // if car can directly pass i.e, no car waiting to go through same path
                    Constant.vehicleStatus.setElementAt("Pass", this.index);
                    Constant.vehicleTimeStatus.setElementAt("--", this.index);
                }
            } else {
                if (Constant.greenTrafficlight == 2) {      // check if T2 traffic light is green
                    time += 60;
                } else {
                    time += 0;
                }
                this.waitTime += time;
                // vehicle status updated
                Constant.vehicleStatus.setElementAt("Wait", this.index);
                Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
            }
        }

        // check if user going from west to south
        else if (user.getValue1().equalsIgnoreCase(Constant.westDirection) && user.getValue2().equalsIgnoreCase(Constant.southDirection)) {
            if (Constant.greenTrafficlight == 2) {      // checks if traffic light on is T2
                if ( this.numCarWait > 0 ) {          // checks if there is any car waiting to go through same path
                    time = Constant.westSouthExtraWait;
                    if (time < this.waitTime + 6) {     // checks if car can pass before light changes
                        long numCarExit = time/6;       // get number of car which can pass before traffic light changes
                        this.numCarWait -= numCarExit;      // subtract number of car which can go before traffic light changes from total car waiting to go through same path

                        // calculate wait time by by adding time for each car waiting to take 6sec each and only 10 car can pass in 60 sec of traffic light time and remaing car wait for 120 sec more
                        long carWait = time + 120 + (this.numCarWait*6) + (((this.numCarWait)/10)*120);
                        this.waitTime = carWait;
                    }
                    // vehicle status updated
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else if (this.waitTime == 0 && time < 6) {    // if car arrive after 54sec and so it cannot pass during this traffic light time
                    this.waitTime = time + 120;     // vehicle wait for 120sec
                    // vehicle status updated
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else {        // if car can directly pass i.e, no car waiting to go through same path
                    Constant.vehicleStatus.setElementAt("Pass", this.index);
                    Constant.vehicleTimeStatus.setElementAt("--", this.index);
                }
            } else {
                if (Constant.greenTrafficlight == 3) {      // check if T3 traffic light is green
                    time += 60;
                } else {
                    time += 0;
                }
                this.waitTime += time;
                // vehicle status updated
                Constant.vehicleStatus.setElementAt("Wait", this.index);
                Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
            }
        }
        // check if user going from east to west
        else if (user.getValue1().equalsIgnoreCase(Constant.eastDirection) && user.getValue2().equalsIgnoreCase(Constant.westDirection)) {
            if (Constant.greenTrafficlight == 3) {      // checks if traffic light on is T3
                if ( this.numCarWait > 0 ) {          // checks if there is any car waiting to go through same path
                    time = Constant.eastWestExtraWait;
                    if (time < this.waitTime + 6) {     // checks if car can pass before light changes
                        long numCarExit = time/6;       // get number of car which can pass before traffic light changes
                        this.numCarWait -= numCarExit;      // subtract number of car which can go before traffic light changes from total car waiting to go through same path

                        // calculate wait time by by adding time for each car waiting to take 6sec each and only 10 car can pass in 60 sec of traffic light time and remaing car wait for 120 sec more
                        long carWait = time + 120 + (this.numCarWait*6) + (((this.numCarWait)/10)*120);
                        this.waitTime = carWait;
                    }
                    // vehicle status updated
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else if (this.waitTime == 0 && time < 6) {    // if car arrive after 54sec and so it cannot pass during this traffic light time
                    this.waitTime = time + 120;     // vehicle wait for 120sec
                    // vehicle status updated
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else {        // if car can directly pass i.e, no car waiting to go through same path
                    Constant.vehicleStatus.setElementAt("Pass", this.index);
                    Constant.vehicleTimeStatus.setElementAt("--", this.index);
                }
            } else {
                if (Constant.greenTrafficlight == 1) {      // check if T1 traffic light is green
                    time += 60;
                } else {
                    time += 0;
                }
                this.waitTime += time;
                // vehicle status updated
                Constant.vehicleStatus.setElementAt("Wait", this.index);
                Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
            }
        }
    }

    // give index of the user vehicle number
    private void getIndex() {
        synchronized (Constant.userDetails) {
            for (Quartet<Integer, String, String, Integer> user : Constant.userDetails) {
                if (user.getValue0() == vehicleNumber + 1) {
                    this.index = Constant.userDetails.indexOf(user);
                    break;
                }
            }
        }
    }

    // update waiting time for the user
    private void updateTime() {
        this.waitTime--;
        Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);

        if (this.waitTime <= 0) {
            Constant.vehicleStatus.setElementAt("Pass", this.index);
            Constant.vehicleTimeStatus.setElementAt("--", this.index);
        }
    }

    // add a user for going from source to destination to aid in calculating waiting time
    private static int directionWaitTimeAdd(String source, String destination) {
        if(source.equalsIgnoreCase(Constant.southDirection) && destination.equalsIgnoreCase(Constant.eastDirection)){
            synchronized (Constant.southEastWait) {
                return Constant.southEastWait++;
            }
        }
        else if(source.equalsIgnoreCase(Constant.westDirection) && destination.equalsIgnoreCase(Constant.southDirection)){
            synchronized (Constant.westSouthWait) {
                return Constant.westSouthWait++;
            }
        }
        else if(source.equalsIgnoreCase(Constant.eastDirection) && destination.equalsIgnoreCase(Constant.westDirection)) {
            synchronized (Constant.eastWestWait) {
                return Constant.eastWestWait++;
            }
        }
        else {
            return 0;
        }
    }

    // remove user from waiting counter for going from source to destination direction
    private static void directionWaitTimeRemove(String source, String destination) {
        if(source.equalsIgnoreCase(Constant.southDirection) && destination.equalsIgnoreCase(Constant.eastDirection)){
            synchronized (Constant.southEastWait) {
                Constant.southEastWait--;
            }
        }
        else if(source.equalsIgnoreCase(Constant.westDirection) && destination.equalsIgnoreCase(Constant.southDirection)){
            synchronized (Constant.westSouthWait) {
                Constant.westSouthWait--;
            }
        }
        else if(source.equalsIgnoreCase(Constant.eastDirection) && destination.equalsIgnoreCase(Constant.westDirection)) {
            synchronized (Constant.eastWestWait) {
                Constant.eastWestWait--;
            }
        }
    }

    // store remaining time for traffic light change from  car which just previously went to pass state
    private void directionExtraWaitTime(String source, String destination) {
        if(source.equalsIgnoreCase(Constant.southDirection) && destination.equalsIgnoreCase(Constant.eastDirection)){
            Constant.southEastExtraWait=60-(Constant.programTime - Constant.startTrafficLightTime );
        }
        else if(source.equalsIgnoreCase(Constant.westDirection) && destination.equalsIgnoreCase(Constant.southDirection)){
            Constant.westSouthExtraWait=60-(Constant.programTime - Constant.startTrafficLightTime );
        }
        else if(source.equalsIgnoreCase(Constant.eastDirection) && destination.equalsIgnoreCase(Constant.westDirection)){
            Constant.eastWestExtraWait=60-(Constant.programTime - Constant.startTrafficLightTime );
        }
    }

    @Override
    public void run() {
        // added user in list with its status in another vector
        synchronized (Constant.userDetails) {
            Constant.userDetails.add(new Quartet<>(vehicleNumber + 1, sourceDirection, destinationDirection, arrivalTime));
            Constant.vehicleStatus.add("Pass");
            Constant.vehicleTimeStatus.add("--");
        }

        this.numCarWait = directionWaitTimeAdd(this.sourceDirection,this.destinationDirection); // get its waiting number for going from source to destination
        this.waitTime = (this.numCarWait*6) + (((this.numCarWait)/10)*120) ;
        getIndex();     // get index of user in userDetail list
        updateUserDetails();  // update wait time and status as per the traffic light which is green

        while (true) {
            updateTime();   // continuously decrement waiting time after every 1sec and update its status
            if (Constant.vehicleStatus.get(this.index).equals("Pass")) {    // check if vehicle as status pass
                try {
                    directionExtraWaitTime(this.sourceDirection,this.destinationDirection);
                    sleep(6000);        // vehicle take 6sec to pass
                    // update status to passed
                    Constant.vehicleStatus.setElementAt("Passed", this.index);
                    Constant.vehicleTimeStatus.setElementAt("--", this.index);
                    directionWaitTimeRemove(this.sourceDirection,this.destinationDirection);  // remove user from waiting to pass from source to destination
                    stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
