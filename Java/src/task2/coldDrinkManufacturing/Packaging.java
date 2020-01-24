package task2.coldDrinkManufacturing;


/********** Packaging class **************/
public class Packaging {
    int counter;
    public int packageBottle() {

        // check current priority and no. of bottle of current priority is not zero then start packing them
        if (Constant.trayPackagingInput == 1 && Constant.packagingB1.size() > 0)
        {
            SynchronizedCounter.incrementGodownB1();   //increment B1 bottles in Godown
            Constant.packagingB1.remove();              // remove B1 bottle from tray
            Constant.trayPackagingInput = 2;           // next priority is given to B2
            Constant.packagedB1Bottles++;              // increment packed B1 bottles
            return 3;
        }
        // check current priority and no. of bottle of current priority is not zero then start packing them
        if (Constant.trayPackagingInput == 2 && Constant.packagingB2.size() > 0)
        {
            SynchronizedCounter.incrementGodownB2();        //increment B2 bottles in Godown
            Constant.packagingB2.remove();                   // remove B2 bottle from tray
            Constant.trayPackagingInput = 1;                    // next priority is given to B1
            Constant.packagedB2Bottles++;                       // increment packed B2 bottles
            return 4;
        }
        // check current priority and no. of bottle of current priority is not zero then start packing them
        if (Constant.trayPackagingInput == 2 && Constant.packagingB1.size() > 0)
        {
            SynchronizedCounter.incrementGodownB1();      //increment B1 bottles in Godown
            Constant.packagingB1.remove();                  // remove B1 bottle from tray
            Constant.trayPackagingInput = 1;            // next priority is given to B1
            Constant.packagedB1Bottles++;               // increment packed B1 bottles
            return 3;
        }
        // check current priority and no. of bottle of current priority is not zero then start packing them
        if (Constant.trayPackagingInput == 1 && Constant.packagingB2.size() > 0)
        {
            SynchronizedCounter.incrementGodownB2();            //increment B1 bottles in Godown
            Constant.packagingB2.remove();                   // remove B1 bottle from tray
            Constant.trayPackagingInput = 2;                // next priority is given to B1
            Constant.packagedB2Bottles++;                 // increment packed B1 bottles
            return 4;
        }

        // if sealing tray is full
        if (Constant.sealing.size() == 2){
            SynchronizedCounter.updatePackingTimeCounter();   // keep updating time counter
            SynchronizedCounter.updateTimeCounter();
            return -1;
        }

        // when priority in unfinished tray is set for B1
        if(Constant.unfinishedTrayPackagingInput == 1) {
            if (SynchronizedCounter.getUnFinishedTrayB1Value() > 0) {      // B1 bottles in unfinished tray is not zero
                counter = SynchronizedCounter.decrementUnFinishedTrayB1Value();     // remove B1 from unfinished tray
                Constant.unfinishedTrayPackagingInput = 2;                  // next priority is B2
                if(counter >= 0){
                    Constant.sealing.add(1);                        // add B1 in sealing tray
                    Constant.packagedB1Bottles++;                  // increment packed B1 counter
                } else {
                    return packageBottle();                         // if not bottle available check again
                }
                return 1;
            }
            else if (SynchronizedCounter.getUnFinishedTrayB2Value() > 0) {   // B2 bottles in unfinished tray is not zero
                counter = SynchronizedCounter.decrementUnFinishedTrayB2Value();   // remove B2 from unfinished tray
                Constant.unfinishedTrayPackagingInput = 2;                       // next priority is B2 as no B1 bottle available if we reached this statement
                if(counter >= 0){
                    Constant.sealing.add(2);                                // add B2 in sealing tray
                    Constant.packagedB2Bottles++;                           // increment packed B2 counter
                } else {
                    return packageBottle();                              // if not bottle available check again
                }
                return 2;
            }
        }
        // when priority in unfinished tray is set for B1
        if(Constant.unfinishedTrayPackagingInput == 2){
            if (SynchronizedCounter.getUnFinishedTrayB2Value() > 0) {               // B2 bottles in unfinished tray is not zero
                counter = SynchronizedCounter.decrementUnFinishedTrayB2Value();      // remove B2 from unfinished tray
                Constant.unfinishedTrayPackagingInput = 1;                      // next priority is B1
                if(counter >= 0){
                    Constant.sealing.add(2);                                    // add B2 in sealing tray
                    Constant.packagedB2Bottles++;                             // increment packed B2 counter
                } else {
                    return packageBottle();                                    // if not bottle available check again
                }
                return 2;
            } else if (SynchronizedCounter.getUnFinishedTrayB1Value() > 0) {         // B1 bottles in unfinished tray is not zero
                counter = SynchronizedCounter.decrementUnFinishedTrayB1Value();          // remove B1 from unfinished tray
                Constant.unfinishedTrayPackagingInput = 1;                              // next priority is B1 as no B2 bottle available if we reached this statement
                if(counter >= 0){
                    Constant.sealing.add(1);                                       // add B2 in sealing tray
                    Constant.packagedB1Bottles++;                                // increment packed B2 counter
                } else {
                    return packageBottle();                                 // if not bottle available check again
                }
                return 1;
            }
        }
        return 0;
    }
}
