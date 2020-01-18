package io.qkits.corejava.corejava.concurrency.samples.concurrentproblem;

/**
 * Created by patrick on 16/8/27.
 */
public class ClientSample {

    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);

        Company company = new Company(account);
        Thread companyThread = new Thread(company);
        Bank bank= new Bank(account);
        Thread bankThread = new Thread(bank);
        System.out.println("Account Initial Balance is "+account.getBalance());

        companyThread.start();
        bankThread.start();

        try {
            companyThread.join();
            bankThread.join();
            System.out.println("Account final balance "+account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
