package io.qkits.corejava.corejava.concurrency.samples.concurrentproblem;

/**
 * Created by patrick on 16/8/27.
 */
public class Account {

    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addAmount(double amount){
        double tmp=balance;
        try{
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        tmp+=amount;
        balance=tmp;
    }

    public void subtractAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp-=amount;
        this.balance=tmp;
    }
}
