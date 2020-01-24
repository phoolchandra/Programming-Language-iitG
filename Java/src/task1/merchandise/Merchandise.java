package task1.merchandise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.javatuples.Triplet;

public class Merchandise{

    private List<Setup> Setups; // List of Setup Threads
    private Order order = new Order();


    /******* starting process for each order *******/
    private void startMachine() {

        for (Setup setup : Setups)
        {
            setup.start();
        }

        /************ pause main thread till all orders are processed ***********/
        for (Setup setup : Setups)
        {
            try {
                setup.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /************** Creating array of threads for each order ******************/
    private Merchandise() {
        Setups = new ArrayList<>();
        for (int i = 0; i < Constant.numberOfOrder; i++) {
            Setup setup = new Setup(this, this.order, i+1);
            Setups.add(setup);
        }
    }

    public static void main(String[]args) throws FileNotFoundException {

        /**************** Taking input from file ************/
        File file = new File(Constant.fileName);
        Scanner scanner = new Scanner(file);

        /***************  Preparing  Order list ********/
        Constant.numberOfOrder = scanner.nextInt();
        int inputNumber = 0;
        while (scanner.hasNextLine()  && inputNumber++ < Constant.numberOfOrder) {
            int index = scanner.nextInt();
            Character type = scanner.next().charAt(0);
            int count = scanner.nextInt();
            Constant.orderList.add(new Triplet<>(index,type,count));
        }
        /****** print inventory **********/
        System.out.println("Inventory --");
        System.out.println("S   |   M   |   L   |   C");
        System.out.println(String.format("%d   |   %d   |   %d   |   %d \n",Constant.totalSmall,Constant.totalMedium,Constant.totalLarge,Constant.totalCap));


        Merchandise merchandise = new Merchandise();

        /********* To start processing each order ************/
        if (Constant.numberOfOrder > 0) {
            merchandise.startMachine();
        }
    }
}
