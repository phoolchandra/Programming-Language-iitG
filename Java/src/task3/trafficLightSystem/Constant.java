package task3.trafficLightSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.javatuples.Quartet;

public class Constant {
    public static long startTime;       // contains program start time
    public static long programTime;        // contains program current time
    public static int vehicleNumber=0;      // container counter for vehicle number
    public static long startTrafficLightTime;       // contains time( in sec) when current traffic light started
    public static int greenTrafficlight = 1;        // contains which traffic light is green at present
    public static String southDirection = "South";
    public static String westDirection = "West";
    public static String eastDirection = "East";
    public static List<Quartet<Integer, String, String, Integer>> userDetails = new ArrayList<>();      // Contains all vehicle entry except starting in future
    public static List<String> directions = new ArrayList<>();      // contains direction name list
    public static Vector vehicleTimeStatus = new Vector();      // Contains all vehicle wait time status in term of time except vehicle starting in future
    public static Vector vehicleStatus = new Vector();      // Contains all vehicle wait time status in term of boolean except vehicle starting in future
    public static Integer westSouthWait = 0;       // Contains number of car waiting to go from west to south direction
    public static Integer eastWestWait = 0;         // Contains number of car waiting to go from east to west direction
    public static Integer southEastWait = 0;        // Contains number of car waiting to go from south to east direction
    public static List<Quartet<Integer, String, String, Integer>> allUser = new ArrayList<>();      // Contains all vehicle entry

    public static long westSouthExtraWait = 0;       // Contains time to go from west to south direction if green light is T2
    public static long eastWestExtraWait = 0;         // Contains time to go from east to west direction if green light is T3
    public static long southEastExtraWait = 0;        // Contains time to go from south to east direction if green light is T1
}
