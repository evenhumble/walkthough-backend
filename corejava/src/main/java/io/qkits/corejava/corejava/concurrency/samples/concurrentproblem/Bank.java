package io.qkits.corejava.corejava.concurrency.samples.concurrentproblem;

/**
 * Created by patrick on 16/8/27.
 */
public class Bank implements Runnable {
    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            this.account.subtractAmount(1000);
        }
    }
}
