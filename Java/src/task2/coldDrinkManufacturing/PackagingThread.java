package task2.coldDrinkManufacturing;


/*************** Packagin thread class *************/
public class PackagingThread extends Thread {
    private Packaging packaging;

    PackagingThread(Packaging packaging,String name){
        this.packaging = packaging;                //initialise object
        setName(name);                              //se name for thread
    }

    @Override
    public void run(){
        while(true) {

            // if condition  to stop the thread
            if (Constant.observationTime<SynchronizedCounter.getTimeCounterValue()+2
                    || (Constant.totalB1Bottles == SynchronizedCounter.getGodownB1Value()
                    && Constant.totalB2Bottles == SynchronizedCounter.getGodownB2Value())) {
                SynchronizedCounter.incrementPackingTimeCounter();
                SynchronizedCounter.updateTimeCounter();
                stop();
            }
            if (SynchronizedCounter.getPackingTimeCounterValue()<=SynchronizedCounter.getTimeCounterValue()) {
                // keep picking bottles
                int bottleType = packaging.packageBottle();
                if (bottleType >= 0) {
                    SynchronizedCounter.incrementPackingTimeCounter();     // increment packing time counter
                    SynchronizedCounter.updateTimeCounter();                // update time counter
                }
            }
        }
    }
}
