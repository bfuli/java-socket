package org.fuli;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadITest {
    private static int i = 0;
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        for (int j = 0; j<20; j++){
            Thread t = new MyThread(i);
            t.setName(String.valueOf(i));
            t.start();
            i++;
        }
    }

    static class MyThread extends Thread{
        private int num;
        public MyThread(int num){
            this.num = num;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+num);
        }
    }
}
