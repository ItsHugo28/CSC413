/*************************************************
 File: Transaction.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Transaction class
 *************************************************/
public class Transaction {
    private double balance;

    public Transaction(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of $" + amount + " successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal of $" + amount + " successful.");
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    public double checkBalance() {
        return balance;
    }
}

