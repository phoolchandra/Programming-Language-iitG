package task2.coldDrinkManufacturing;


/************* Class for sealing unit's thread with inheritance of thread class ************/
public class SealingThread extends Thread {

    private Sealing sealing;

    SealingThread(Sealing sealing, String name){
        this.sealing = sealing;    // initialise sealing object
        setName(name);
    }

    @Override
    public void run(){
        while (true) {
            // condition to stop thread
            if (Constant.observationTime<SynchronizedCounter.getTimeCounterValue()+3
                    || (Constant.totalB1Bottles == SynchronizedCounter.getGodownB1Value()
                    && Constant.totalB2Bottles == SynchronizedCounter.getGodownB2Value())) {
                SynchronizedCounter.incrementSealingTimeCounter();

                stop();
            }
            if (SynchronizedCounter.getSealingTimeCounterValue()<=SynchronizedCounter.getTimeCounterValue()) {
                int bottleType = sealing.sealBottle();            // keep picking bottles
                if (bottleType >=0) {
                    SynchronizedCounter.incrementSealingTimeCounter();        //  updates sealing time
                    SynchronizedCounter.updateTimeCounter();                // update time counter
                }
            }
        }
    }
}
