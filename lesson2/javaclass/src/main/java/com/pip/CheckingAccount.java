package com.pip;

/**
 * Subclass of Account that allows withdrawal
 */
public class CheckingAccount extends Account {

    public CheckingAccount(long amount) {
        super(amount);
    }

    public void withdraw(long withdrawAmount) {
        setAmount(getAmount() - withdrawAmount);
    }

    public String toString() {
        return "Checking account balance: $" + getAmount();
    }
}
