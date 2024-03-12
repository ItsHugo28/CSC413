/*************************************************
 File: Transaction.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Transaction class
 *************************************************/
public class Transaction {
    private double balance;

    public Transaction() {
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public double checkBalance() {
        return balance;
    }
}
