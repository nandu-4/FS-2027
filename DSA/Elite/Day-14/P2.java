/*
You are given N customer orders that must be processed sequentially.
Design a Java multithreading application using the Callable interface only, 
with the following strict constraints:
    - There is exactly ONE Producer task
    - There is exactly ONE Consumer task
    - The number of orders (N) and the list of orders are global shared objects
    - The Producer and Consumer must not receive the orders directly
    - The Producer and Consumer must communicate only through a shared handoff object
    - There is NO buffer, queue, or collection used for intermediate storage

Each order must be processed in the following strict order:
Producer processes one order → Consumer processes the same order → then move to the next order

The Producer must wait until the Consumer finishes processing the current order.
The Consumer must wait until the Producer produces the next order.

Order Information: Each order contains the following details:

----------------------------------------
Field			Description
----------------------------------------
orderId			Unique order identifier
customerName	Name of the customer
productName		Product ordered
quantity		Number of items
pricePerUnit	Cost per item
totalAmount		quantity × price
----------------------------------------

Input Format:
-------------
NUMBER_OF_ORDERS, N
Next N lines: orderId customerName productName quantity pricePerUnit


Sample Input:
-------------
3
1001 Alice Laptop 1 75000
1002 Bob Phone 2 20000
1003 Charlie Tablet 1 30000

Sample Output:
--------------
PRODUCED Order[ID=1001, Customer=Alice, Product=Laptop, Qty=1, Total=75000.0]
CONSUMED Order[ID=1001, Total=75000.0]
PRODUCED Order[ID=1002, Customer=Bob, Product=Phone, Qty=2, Total=40000.0]
CONSUMED Order[ID=1002, Total=40000.0]
PRODUCED Order[ID=1003, Customer=Charlie, Product=Tablet, Qty=1, Total=30000.0]
CONSUMED Order[ID=1003, Total=30000.0]

*/

import java.util.*;
import java.util.concurrent.*;

class Order{
    int o_id,qty;
    String cname,pname;
    double ppu,total_amount;
    Order(int id,String cname,String pname,int qty,double ppu){
        this.o_id=id;
        this.cname=cname;
        this.pname=pname;
        this.qty=qty;
        this.ppu=ppu;
        total_amount=qty*ppu;
    }
}

class OrderProducer implements Callable<String> {
    Order order;
    sharedObject obj;
    OrderProducer(Order order,sharedObject obj){
        this.order=order;
        this.obj=obj;
    }
    public String call() throws Exception{
        return obj.put(order);
        // return "PRODUCED Order[ID="+order.o_id+", Customer="+order.cname+", Product="+order.pname+", Qty="+order.qty+", Total="+(order.total_amount)+"]";
    }
}
class OrderConsumer implements Callable<String> {
    // Order order;
    sharedObject obj;
    OrderConsumer(sharedObject obj){
        // this.order=order;
        this.obj=obj;
    }
    public String call() throws Exception{
        return obj.get();
        // return "CONSUMED Order[ID="+order.o_id+", Total="+order.total_amount+"]";
    }
}
class sharedObject{
    Order order;
    sharedObject(){
        order=null;
    }
    synchronized String put(Order o) throws InterruptedException{
        while(order!=null){
            wait();
        }
        order=o;
        notify();
        return "PRODUCED Order[ID="+order.o_id+", Customer="+order.cname+", Product="+order.pname+", Qty="+order.qty+", Total="+(order.total_amount)+"]";
        
    }
    synchronized String get()throws InterruptedException{
        while(order==null){
            wait();
        }
        notify();
        return "CONSUMED Order[ID="+order.o_id+", Total="+order.total_amount+"]";
    }
    void clearObject(){
        order=null;
    }
}
class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        List<Order> orders=new ArrayList<>();
        for(int i=0;i<n;i++){
            orders.add(new Order(
                sc.nextInt(),
                sc.next(),
                sc.next(),
                sc.nextInt(),
                sc.nextDouble()
                )
            );
        }
        ExecutorService executor=Executors.newFixedThreadPool(Math.min(n,Runtime.getRuntime().availableProcessors()));
        sharedObject obj=new sharedObject();
        for(Order order:orders){
            try{
                Future<String> producer=executor.submit(new OrderProducer(order,obj));
                System.out.println(producer.get());
                Future<String> consumer=executor.submit(new OrderConsumer(obj));
                System.out.println(consumer.get());
                obj.clearObject();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        executor.shutdown();
        sc.close();
    }
}