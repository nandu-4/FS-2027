/*
You are developing a multi-threaded backend system for an e-commerce platform.

The platform receives customer orders from multiple sources (mobile app, website, partner APIs).
These orders must be queued, processed, and dispatched concurrently.

To ensure scalability:
	- Order creation and order processing run in parallel
	- Orders are exchanged through a shared buffer with limited capacity

Order Information: Each order contains the following details:

--------------------------------------------------
Field						Description
--------------------------------------------------
orderId					Unique order identifier
customerName	Name of the customer
productName		Product ordered
quantity					Number of items
pricePerUnit			Cost per item
totalAmount			quantity × price
--------------------------------------------------

Input Format:
-----------------
BUFFER_CAPACITY
NUMBER_OF_ORDERS, N
Next N lines:
orderId customerName productName quantity pricePerUnit


Sample Input:
-----------------
3
4
1001 Alice Laptop 1 75000
1002 Bob Phone 2 20000
1003 Charlie Tablet 1 30000
1004 Diana Headphones 2 5000

Sample Output:
-------------------
PRODUCED Order[ID=1001, Customer=Alice, Product=Laptop, Qty=1, Total=75000.0]
PRODUCED Order[ID=1002, Customer=Bob, Product=Phone, Qty=2, Total=40000.0]
PRODUCED Order[ID=1003, Customer=Charlie, Product=Tablet, Qty=1, Total=30000.0]
PRODUCED Order[ID=1004, Customer=Diana, Product=Headphones, Qty=2, Total=10000.0]
CONSUMED Order[ID=1001, Total=75000.0]
CONSUMED Order[ID=1002, Total=40000.0]
CONSUMED Order[ID=1003, Total=30000.0]
CONSUMED Order[ID=1004, Total=10000.0]

*/

import java.util.*;
import java.util.concurrent.*;

class Order{
    int o_id,qty;
    String cname,pname;
    double ppu;
    Order(int id,String cname,String pname,int qty,double ppu){
        this.o_id=id;
        this.cname=cname;
        this.pname=pname;
        this.qty=qty;
        this.ppu=ppu;
    }
}

class OrderProducer implements Callable<List<String>>{
    OrderBuffer buffer;
    List<Order> orders;
    OrderProducer(OrderBuffer buffer,List<Order> orders){
        this.buffer=buffer;
        this.orders=orders;
    }
    public List<String> call() throws Exception{
        List<String> ans=new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            Order cur=orders.get(i);
            ans.add("PRODUCED Order[ID="+cur.o_id+", Customer="+cur.cname+", Product="+cur.pname+", Qty="+cur.qty+", Total="+(cur.qty*cur.ppu)+"]");
            buffer.addbuffer(cur);
        }
        return ans;
    }
}
class OrderConsumer implements Callable<List<String>>{
    OrderBuffer buffer;
    int orderscount;
    OrderConsumer(OrderBuffer buffer,int orderscount){
        this.buffer=buffer;
        this.orderscount=orderscount;
    }
    public List<String> call() throws Exception{
        List<String> ans=new ArrayList<>();
        for(int i=0;i<orderscount;i++){
            Order cur=buffer.deletebuffer();
            ans.add("CONSUMED Order[ID="+cur.o_id+", Total="+(cur.qty*cur.ppu)+"]");
        }
        return ans;
    }
}
class OrderBuffer{
    Queue<Order> q;
    int max_cap;
    OrderBuffer(int max_cap){
        this.max_cap=max_cap;
        q=new LinkedList<>();
    }
    public synchronized void addbuffer(Order o)throws InterruptedException{
        while(q.size()==max_cap){
            wait();
        }
        q.offer(o);
        notifyAll();
    }
    public synchronized Order deletebuffer()throws InterruptedException{
        while(q.size()==0){
            wait();
        }
        notifyAll();
        return q.poll();
    }
}
public class ProducerConsumerCallableDemo {
    

    /* ==========================
       IMPLEMENT YOUR CODE HERE
       ========================== */
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int bufferCapacity = sc.nextInt();
        int n = sc.nextInt();

        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            orders.add(new Order(
                    sc.nextInt(),
                    sc.next(),
                    sc.next(),
                    sc.nextInt(),
                    sc.nextDouble()
            ));
        }

        OrderBuffer buffer = new OrderBuffer(bufferCapacity);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        long start = System.currentTimeMillis();

        Future<List<String>> producerFuture =
                executor.submit(new OrderProducer(buffer, orders));
        Future<List<String>> producerFutures =
                executor.submit(new OrderProducer(buffer, orders));
        Future<List<String>> consumerFuture =
                executor.submit(new OrderConsumer(buffer, n));
        
        /* ---- PHASE 1: PRINT PRODUCERS ---- */
        for (String log : producerFuture.get()) {
            System.out.println(log);
        }

        /* ---- PHASE 2: PRINT CONSUMERS ---- */
        for (String log : consumerFuture.get()) {
            System.out.println(log);
        }

        executor.shutdown();
        sc.close();
    }
}
