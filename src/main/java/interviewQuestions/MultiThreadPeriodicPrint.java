package interviewQuestions;

import javafx.concurrent.Task;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: MultiThreadPeriodicPrint
 * @CopyRight: wufangmin
 * @Description: 多线程交替打印 A,B,C
 * @Author: wufangmin
 * @Date: 2019/10/30 10:22
 * @Version: 1.0
 */
public class MultiThreadPeriodicPrint {


    /**
     * 方案一: 使用共享变量维护元素打印 位置；并对共享变量的访问加锁控制
     *
     * 缺点: 效率较低
     */

    private static Lock lock = new ReentrantLock();
    private static int state = 0;
    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    if (state % 3 == 0) {
                        System.out.print("A");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    if (state % 3 == 1) {
                        System.out.print("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    if (state % 3 == 2) {
                        System.out.print("C");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
            System.out.println();
        }
    }



    /**
     *  方案二：使用 condition 实现线程通知;
     */
    private static Lock lock2 = new ReentrantLock();
    private static Condition ACondition = lock2.newCondition();
    private static Condition BCondition = lock2.newCondition();
    private static Condition CCondition = lock2.newCondition();

    private static int num = 0;

    static class TaskA implements Runnable{

        @Override
        public void run() {
            for(int i = 0 ; i < 10 ; i++){
                printA(i);
            }
        }
    }

    static class TaskB implements Runnable{

        @Override
        public void run() {
            for(int i = 0 ; i < 10 ; i++){
                printB();
            }
        }
    }

    static class TaskC implements Runnable{
        @Override
        public void run() {
            for(int i = 0 ; i < 10 ; i++){
                printC();
            }
            System.out.println();
        }
    }

    static void printA(int i){
        try {
            lock2.lock();
            if(num != 0){
                ACondition.await();
            }
            System.out.print("A");
            num = (++num)%3;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            BCondition.signal();
            lock2.unlock();
        }
    }

    static void printB(){
        try {
            lock2.lock();
            if(num != 1){
                BCondition.await();
            }
            System.out.print("B");
            num = (++num)%3;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            CCondition.signal();
            lock2.unlock();
        }
    }

    static void printC(){
        try {
            lock2.lock();
            if(num != 2){
                CCondition.await();
            }
            System.out.print("C");
            num = (++num)%3;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ACondition.signal();
            lock2.unlock();
        }
    }


    public static void main(String[] args) {

        Thread threadA = new Thread(new TaskA());
        Thread threadB = new Thread(new TaskB());
        Thread threadC = new Thread(new TaskC());

        threadA.start();
        threadB.start();
        threadC.start();

//        new ThreadB().start();
//        new ThreadA().start();
//        new ThreadC().start();
    }
}
