package com.pip;

/**
 * Class which signs up for a checking account with specific initial amounts
 */
public class MoneySaver {
    private long myMoney;
    private Account myAccount;
    private String name;

    public MoneySaver(String name, long money) {
        this.name = name;
        myMoney = money;
    }

    public String getName() {
        return name;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public String toString() {
        return name + ", " + "My balance is: " + myMoney;
    }

    public void deposit(int amount) {
        myAccount.deposit(amount);
        myMoney -= amount;
    }

    public void withdraw(int amount) {
        ((CheckingAccount)myAccount).withdraw(amount);
        myMoney += amount;
    }

    public void signUpForChecking(int amount) {
        myAccount = new CheckingAccount(amount);
        myMoney -= amount;
    }

    public static void main(String[] args) {
//        MoneySaver david = new MoneySaver("David", 100);
//        david.signUpForChecking(50);  // put 50 dollars in a checking account
//        System.out.println(david);
//        System.out.println(david.getMyAccount());

        MoneySaver jim = new MoneySaver("Jim", 100);
        jim.signUpForChecking(30);
        CheckingAccount acc = (CheckingAccount) jim.getMyAccount();
        System.out.println(jim);
        System.out.println(acc);
        jim.deposit(40);
        System.out.println(jim);
        System.out.println(acc);
        jim.withdraw(60);
        System.out.println(jim);
        System.out.println(acc);
    }
}

