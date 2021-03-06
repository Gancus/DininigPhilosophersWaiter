/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dpp;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Marcin
 */
public class Philosopher extends Thread {
    private int id;
    private Semaphore left, right;
    private static Semaphore waiter;
    private int eatCouter = 0;

    public Philosopher(int id, Semaphore left, Semaphore right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }
    
    public static void setWaiter(Semaphore waiter) {
        Philosopher.waiter = waiter;
    }
    
    @Override
    public void run() {
        while(eatCouter <= 100) {
            try {
                think();
                waiter.acquire();
                left.acquire();
                right.acquire();
                eat();
                right.release();
                left.release();
                waiter.release();
            } catch (InterruptedException ex) {
                System.out.println("Przerywanie " + this);
            }
        }
    }
    
    public void think() throws InterruptedException {
        /*System.out.println(this + " zaczyna myśleć");
        System.out.println(this + " myśli");
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(this + " kończy myśleć");*/
    }
    
    public void eat() throws InterruptedException {
        System.out.println(this + " zaczyna jeść");
        System.out.println(this + " je");
        eatCouter++;
        //TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(this + " kończy jeść");
    }

    @Override
    public String toString() {
        return "Filozof{" + "id=" + id + '}';
    }
    
    
}
