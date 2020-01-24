package task2.coldDrinkManufacturing;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/************* Class for global variables ***********/
public class Constant {

    public static String fileName = "src/task2/coldDrinkManufacturing/input.txt";

    public static int totalB1Bottles;      // contains total B1
    public static int totalB2Bottles;       // contains total B2 bottles
    public static int observationTime;    // contains time at which output to be displayed

    public static int packagingB1TraySize=2;      // contains total B1 bottles in packaging tray
    public static int packagingB2TraySize=3;    // contains total B2 bottles in packaging tray
    public static int unfinishedTrayPackagingInput = 2;   // contains current priority for packaging which picking from UF tray
    public static int trayPackagingInput = 1;      // contains current priority for Packaging tray while picking from packaging tray
    public static int unfinishedTraySealingInput = 1;  // contains current priority for sealing which picking from UF tray
    public static int packagedB1Bottles=0;        // contains total B1 bottles packed
    public static int packagedB2Bottles=0;     // contains total B2 bottles packed
    public static int sealedB1Bottles=0;        // contains total B1 bottles sealed
    public static int sealedB2Bottles=0;     // contains total B2 bottles sealed

    /********* Blocking queue declaration **********/
    public static BlockingQueue packagingB1 = new ArrayBlockingQueue(2);     // contains list of B1 bottles in packaging tray
    public static BlockingQueue packagingB2 = new ArrayBlockingQueue(3);    // contains list of B2 bottles in packaging tray
    public static BlockingQueue sealing = new ArrayBlockingQueue(2);      // contains list of  bottles in sealing tray
}
