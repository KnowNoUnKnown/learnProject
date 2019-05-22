package com.oracle.xing.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuyong
 * 2018-11-02  11-15
 */

public class ReentrantLockTest {

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>() ;

    private static final int CAPABILITY = 16;

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ReentrantLock lock = new ReentrantLock();
        Condition empty = lock.newCondition();
        Condition full = lock.newCondition();

        Runnable putRunable = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (true){
                        if(queue.size() >= CAPABILITY){
                            System.out.println("队列已经满了，put线程睡眠！");
                            full.await();
                        }
                        Thread.sleep(1500);
                        String value = Thread.currentThread().getName()+"============时间"+ dateFormat.format(new Date());
                        System.out.println("put value =========>"+value);
                        queue.put(value);
                        empty.signal();
                        full.await();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        Runnable getRunable = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (true){
                    if(queue.size() == 0){
                        System.out.println("队列为空，get线程睡眠！");
                        empty.await();
                    }
                    Thread.sleep(1500);
                    String value = queue.poll();
                    System.out.println("get value <========="+value);
                    full.signal();
                    empty.await();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };
        putRunable.run();
        getRunable.run();

        executorService.execute(putRunable);
        executorService.execute(getRunable);
    }

}
