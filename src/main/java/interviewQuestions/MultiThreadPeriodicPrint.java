package interviewQuestions;

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
     *   使用 condition 实现线程通知;
     */
    private static Lock lock2 = new ReentrantLock();
    private static Condition ACondition = lock2.newCondition();
    private static Condition BCondition = lock2.newCondition();
    private static Condition CCondition = lock2.newCondition();





    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
