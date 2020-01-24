package task2.coldDrinkManufacturing;

public class Sealing extends Thread {
    int counter;
    public int sealBottle() {
        // check for bottles in sealing tray
        if (Constant.sealing.size() > 0)
        {
            int sealingTrayFirstElement = (int)Constant.sealing.remove();     //get fist bottle from tray
            if (sealingTrayFirstElement == 1)                                // check for B1 bottle
            {
                SynchronizedCounter.incrementGodownB1();                   // increment B1 in Godown
                Constant.sealedB1Bottles++;                               // increment B1 bottles counter for sealed bottles
                return 3;
            }
            if (sealingTrayFirstElement == 2)                             // check for B2 bottle
            {
                SynchronizedCounter.incrementGodownB2();
                Constant.sealedB2Bottles++;
                return 4;
            }
        }

        // if packaging tray is full then update time counter
        if (Constant.packagingB1.size() == Constant.packagingB1TraySize || Constant.packagingB2.size() == Constant.packagingB2TraySize){
            SynchronizedCounter.updateSealingTimeCounter();
            SynchronizedCounter.updateTimeCounter();
            return -1;
        }

        // check for B1 priority
        if(Constant.unfinishedTraySealingInput == 1) {
            if (SynchronizedCounter.getUnFinishedTrayB1Value() > 0) {                // if there are bottles in tray
                counter = SynchronizedCounter.decrementUnFinishedTrayB1Value();      // decrement B1 counter for UF tray
                Constant.unfinishedTraySealingInput = 2;                             // next priority is B2
                if(counter >= 0){
                    Constant.packagingB1.add(1);                                   // add bottle in packagin tray
                    Constant.sealedB1Bottles++;                                    // increment sealed B1 counter
                } else {
                    return sealBottle();                                         // if no bottles available check again
                }
                return 1;
            }
            if (SynchronizedCounter.getUnFinishedTrayB2Value() > 0) {                // if there are bottles in tray
                counter = SynchronizedCounter.decrementUnFinishedTrayB2Value();      // decrement B2 counter for UF tray
                Constant.unfinishedTraySealingInput = 2;                               // next priority is B2 as no B1 bottle available if we reached this statement
                if(counter >= 0){
                    Constant.packagingB2.add(2);                                   // add bottle in packagin tray
                    Constant.sealedB2Bottles++;                                    // increment sealed B2 counter
                } else {
                    return sealBottle();                                           // if no bottles available check again
                }
                return 2;
            }
        }
        // check for B2 priority
        if(Constant.unfinishedTraySealingInput == 2) {
            if (SynchronizedCounter.getUnFinishedTrayB2Value() > 0) {               // if there are bottles in tray
                counter = SynchronizedCounter.decrementUnFinishedTrayB2Value();     // decrement B2 counter for UF tray
                Constant.unfinishedTraySealingInput = 1;                              // next priority is B1
                if(counter >= 0){
                    Constant.packagingB2.add(2);                                    // add bottle in packagin tray
                    Constant.sealedB2Bottles++;                                    // increment sealed B2 counter
                } else {
                    return sealBottle();                                                 // if no bottles available check again
                }
                return 2;
            } else if (SynchronizedCounter.getUnFinishedTrayB1Value() > 0) {                // if there are bottles in tray
                counter = SynchronizedCounter.decrementUnFinishedTrayB1Value();               // decrement B1 counter for UF tray
                Constant.unfinishedTraySealingInput = 1;                                    // next priority is B1 as no B2 bottle available if we reached this statement
                if(counter >= 0){
                    Constant.packagingB1.add(1);                                      // add bottle in packagin tray
                    Constant.sealedB1Bottles++;                                       // increment sealed B1 counter
                } else {
                    return sealBottle();                                              // if no bottles available check again
                }
                return 1;
            }
        }
        return 0;
    }
}
