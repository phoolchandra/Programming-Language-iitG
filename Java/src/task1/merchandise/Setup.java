package task1.merchandise;

/****** Class which setup each order thread and then process the order to give output with help of order class ***********/
public class Setup extends Thread {
    private Merchandise merchandise;
    private Order order;

    Setup(Merchandise merchandise, Order order, int name){
        setName(String.valueOf(name));
        this.merchandise = merchandise;
        this.order = order;
    }

    @Override
    public void run(){
        /********* Get order number from orderlist *********/

        int orderNumber = order.pickOrder(Integer.parseInt(getName()));
        /******** Start processing current order *********/

        order.orderProcess(orderNumber);
        // Stop the thread
        stop();
    }
}
