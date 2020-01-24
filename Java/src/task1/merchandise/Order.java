package task1.merchandise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Order {
    /******** All locks which are required for synchronization ********/
    private static final Lock smallLock = new ReentrantLock();
    private static final Lock mediumLock = new ReentrantLock();
    private static final Lock largeLock = new ReentrantLock();
    private static final Lock capLock = new ReentrantLock();
    private static final Lock printLock = new ReentrantLock();

    /******** print inventory with status of order ********/
    public static void printInventory(Integer orderNumber, Boolean success){
        /******** print locked to print complete status and inventory together *********/
        printLock.lock();
        if (success == true) {
            System.out.println(String.format("Order %d is successful", orderNumber));
        } else {
            System.out.println(String.format("Order %d failed", orderNumber));
        }
        System.out.println("Inventory --");
        System.out.println("S   |   M   |   L   |   C");
        System.out.println(String.format("%d   |   %d   |   %d   |   %d \n",Constant.totalSmall,Constant.totalMedium,Constant.totalLarge,Constant.totalCap));
        printLock.unlock();
    }

    /********** function to get order number ********/
    public static int pickOrder(Integer orderNumber){
        if (orderNumber <= Constant.numberOfOrder) {
            return orderNumber-1;
        } else {
            return -1;
        }
    }

    /********** Method to process an order *************/
    public static void orderProcess(Integer orderNumber){

        if(Constant.orderList.get(orderNumber).getValue1() == Constant.small) {
            /******** synchronization for t-shirt of small size *********/
            smallLock.lock();
            if (Constant.orderList.get(orderNumber).getValue2() >=0 && Constant.orderList.get(orderNumber).getValue2() <= Constant.totalSmall) {
                Constant.totalSmall -= Constant.orderList.get(orderNumber).getValue2();
                printInventory(Constant.orderList.get(orderNumber).getValue0(),true);
            } else {
                printInventory(Constant.orderList.get(orderNumber).getValue0(),false);
            }
            smallLock.unlock();
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.medium) {
            /******** synchronization for t-shirt of medium size *********/
            mediumLock.lock();
            if (Constant.orderList.get(orderNumber).getValue2() >=0 && Constant.orderList.get(orderNumber).getValue2() <= Constant.totalMedium) {
                Constant.totalMedium -= Constant.orderList.get(orderNumber).getValue2();
                printInventory(Constant.orderList.get(orderNumber).getValue0(),true);
            } else {
                printInventory(Constant.orderList.get(orderNumber).getValue0(),false);
            }
            mediumLock.unlock();
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.large) {
            /******** synchronization for t-shirt of large size *********/
            largeLock.lock();
            if (Constant.orderList.get(orderNumber).getValue2() >=0 && Constant.orderList.get(orderNumber).getValue2() <= Constant.totalLarge) {
                Constant.totalLarge -= Constant.orderList.get(orderNumber).getValue2();
                printInventory(Constant.orderList.get(orderNumber).getValue0(),true);
            } else {
                printInventory(Constant.orderList.get(orderNumber).getValue0(),false);

            }
            largeLock.unlock();
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.cap) {
            /******** synchronization for cap *********/
            capLock.lock();
                if (Constant.orderList.get(orderNumber).getValue2() >=0 && Constant.orderList.get(orderNumber).getValue2() <= Constant.totalCap) {
                    Constant.totalCap -= Constant.orderList.get(orderNumber).getValue2();
                    printInventory(Constant.orderList.get(orderNumber).getValue0(),true);
                } else {
                    printInventory(Constant.orderList.get(orderNumber).getValue0(),false);
                }
            capLock.unlock();
        }
    }

}
