package task2.coldDrinkManufacturing;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedCounter {

    /************ All shared variables declard as atomic variables **********/
    private static final AtomicInteger goDownCounterB1 = new AtomicInteger(0);
    private static final AtomicInteger goDownCounterB2 = new AtomicInteger(0);
    private static final AtomicInteger unFinishedTrayB1 = new AtomicInteger(Constant.totalB1Bottles);
    private static final AtomicInteger unFinishedTrayB2 = new AtomicInteger(Constant.totalB2Bottles);
    private static final AtomicInteger timeCounter = new AtomicInteger(0);
    private static final AtomicInteger packingTimeCounter = new AtomicInteger(0);
    private static final AtomicInteger sealingTimeCounter = new AtomicInteger(0);


    /******** returns No. of B1 bottles in Godown ************/
    public static int getGodownB1Value() {
        return goDownCounterB1.get();
    }

    /*********** method to increment No. of B1 bottles in Godown ******/
    public static void incrementGodownB1() {
        while(true) {
            int existingValue = getGodownB1Value();
            int newValue = existingValue + 1;
            if(goDownCounterB1.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    /******** returns No. of B2 bottles in Godown ************/
    public static int getGodownB2Value() {
        return goDownCounterB2.get();
    }

    /*********** method to increment No. of B2 bottles in Godown ******/
    public static void incrementGodownB2() {
        while(true) {
            int existingValue = getGodownB2Value();
            int newValue = existingValue + 1;
            if(goDownCounterB2.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    /******** returns No. of B1 bottles in unfinished tray ************/
    public static int getUnFinishedTrayB1Value() {
        return unFinishedTrayB1.get();
    }

    /*********** method to decrement No. of B1 bottles in unfinished tray ******/
    public static int decrementUnFinishedTrayB1Value() {
        while(true) {
            int existingValue = getUnFinishedTrayB1Value();
            int newValue = existingValue - 1;
            if(unFinishedTrayB1.compareAndSet(existingValue, newValue)) {
                return newValue;
            }
        }
    }

    /******** returns No. of B2 bottles in unfinished tray ************/
    public static int getUnFinishedTrayB2Value() {
        return unFinishedTrayB2.get();
    }

    /*********** method to decrement No. of B2 bottles in unfinished tray ******/
    public static int decrementUnFinishedTrayB2Value() {
        while(true) {
            int existingValue = getUnFinishedTrayB2Value();
            int newValue = existingValue - 1;
            if(unFinishedTrayB2.compareAndSet(existingValue, newValue)) {
                return newValue;
            }
        }
    }

    /********* returns current time **********/

    public static int getTimeCounterValue() {
        return timeCounter.get();
    }

    /*********** update current time ************/
    public static void updateTimeCounter() {
        while(true) {
            int existingValue = getTimeCounterValue();
            int newValue = Math.min(getPackingTimeCounterValue(),getSealingTimeCounterValue());

            if(timeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    /*********** returns time in packing unit **********/

    public static int getPackingTimeCounterValue() {
        return packingTimeCounter.get();
    }

    /********** increment packing unit's time counter *********/
    public static void incrementPackingTimeCounter() {
        while(true) {
            int existingValue = getPackingTimeCounterValue();
            int newValue = existingValue + 2;

            if(packingTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    /********** updates packing unit's time counter *********/
    public static void updatePackingTimeCounter() {
        while(true) {
            int existingValue = getPackingTimeCounterValue();
            int newValue = getSealingTimeCounterValue();

            if(packingTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    /*********** returns time in Sealing unit **********/
    public static int getSealingTimeCounterValue() {
        return sealingTimeCounter.get();
    }

    /********** increment sealing unit's time counter *********/
    public static void incrementSealingTimeCounter() {
        while(true) {
            int existingValue = getSealingTimeCounterValue();
            int newValue = existingValue + 3;

            if(sealingTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
    /********** updates Sealing unit's time counter *********/
    public static void updateSealingTimeCounter() {
        while(true) {
            int existingValue = getSealingTimeCounterValue();
            int newValue = getPackingTimeCounterValue();

            if(sealingTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

}