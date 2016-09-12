package com.pip;

/**
 * Class for an Account, able to manage amounts and compare with other Account(s)
 */
public abstract class Account {
    private long amount;

    public Account(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long newAmount) {
        amount = newAmount;
    }

    public void deposit(long depositAmount) {
        amount += depositAmount;
    }

    public String toString() {
        return "Account Balance: $" + amount;
    }

    public static Account largerAccount(Account acc1, Account acc2) {
        return (acc1.amount >= acc2.amount) ? acc1 : acc2;
    }

    public static void main(String[] args) {
        // Previous question's testing code
//        Account a = new Account(100);
//        System.out.println(a);
//        a.setAmount(20);
//        a.deposit(10);
//        System.out.println("New amount: " + a.getAmount());
//
//        Account small = new Account(20);
//        Account big = new Account(30);
//        System.out.println(Account.largerAccount(small, big));
    }
}

