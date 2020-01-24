package task2.coldDrinkManufacturing;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Manufacturer {

    //objects of different threads
    Packaging packaging = new Packaging();
    Sealing sealing = new Sealing();
    PackagingThread packagingThread = new PackagingThread(packaging,"Packaging");
    SealingThread sealingThread = new SealingThread(sealing,"Sealing");

    private void printOutput() {
        System.out.println("Bottle Type      Status      Count");
        System.out.println("    B1          Packaged       "+Constant.packagedB1Bottles);
        System.out.println("    B1          Sealed         "+Constant.sealedB1Bottles);
        System.out.println("    B1          In Godown      "+ SynchronizedCounter.getGodownB1Value());
        System.out.println("    B2          Packaged       "+Constant.packagedB2Bottles);
        System.out.println("    B2          Sealed         "+Constant.sealedB2Bottles);
        System.out.println("    B2          In Godown      "+ SynchronizedCounter.getGodownB2Value());

    }

    private void startMachine() {

        // starting packaging and sealing threads
        packagingThread.start();
        sealingThread.start();

        // join to ensure main thread stops after both thread
        try {
            packagingThread.join();
            sealingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[]args) throws FileNotFoundException {
        File file = new File(Constant.fileName);
        Scanner scanner = new Scanner(file);
        String line;
        String[] lineVector;

        line = scanner.nextLine();

        //separate all values by comma
        lineVector = line.split(", ");

        //parsing the values to Integer
        Constant.totalB1Bottles= Integer.parseInt(lineVector[0]);
        Constant.totalB2Bottles=Integer.parseInt(lineVector[1]);
        Constant.observationTime=Integer.parseInt(lineVector[2]);

        Manufacturer manufacturer = new Manufacturer();

        if(Constant.observationTime >= 0 ){
            manufacturer.startMachine();
        }

        manufacturer.printOutput();
    }
}
