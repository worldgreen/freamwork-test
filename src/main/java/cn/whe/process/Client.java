package cn.whe.process;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client{
}

class Depot {
    private int size;
    private int capacty;
    private Lock lock;
    private Condition fullCondition;
    private Condition emptyCondition;
    public Depot(int capacty) {
        super();
        this.capacty = capacty;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
            int left = val;
            while (left > 0) {
                while (size >= capacty) {
                    fullCondition.await();
                    int inc = (size + left) > capacty ? capacty - size : left;
                    size += inc;
                    left -= inc;
                    System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                            Thread.currentThread().getName(), val, left, inc, size);
                    emptyCondition.signal();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

    public  void consume(int val) {
        lock.lock();
        try {
            int left = val;
            while (left > 0) {
                while (size <= 0) {
                    emptyCondition.await();
                    int dec = (size >= left) ? left : size;
                    size -= dec;
                    left -= dec;
                    System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                            Thread.currentThread().getName(), val, left, dec, size);
                    fullCondition.signal();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            lock.unlock();
        }
    }
}

class Producer {
    private Depot depot;

    public Producer(Depot depot) {
        super();
        this.depot = depot;
    }
    public void produce(final int val)  {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                depot.produce(val);
            }
        }).start();
    }
}

class Consumer {
    private Depot depot;

    public Consumer(Depot depot) {
        super();
        this.depot = depot;
    }
    public void consume(final int val)  {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                depot.consume(val);
            }
        }).start();
    }
}