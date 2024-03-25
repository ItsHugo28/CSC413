/*************************************************
 File: AccountInterface.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Account interface
 *************************************************/
public interface AccountInterface {
    double getBalance();
    void deposit(double amount);
    void withdraw(double amount);
}
