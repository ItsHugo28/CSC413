/*************************************************
 File: Account.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Account class
 *************************************************/
import java.util.Date;

public class Account implements Comparable<Account>, AccountInterface {
    private final Transaction transaction;
    private int accountId;
    private String accountType;
    private double balance;
    private Date creationDate;
    private Customer customer;
    public Account() {
        this.transaction = new Transaction();
    }

    public Account(int accountId, String accountType, double balance, Date creationDate, Customer customer) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.creationDate = creationDate;
        this.customer = customer;
        this.transaction = new Transaction();
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public void deposit(double amount) {
        transaction.deposit(amount);
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            transaction.withdraw(amount);
            this.balance -= amount;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public double checkBalance() {
        return transaction.checkBalance();
    }

    @Override
    public int compareTo(Account other) {
        return this.creationDate.compareTo(other.creationDate);
    }


    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", accountType='" + accountType + '\'' + ", balance=" + balance + ", creationDate=" + creationDate + ", customer=" + customer + '}';
    }
}
