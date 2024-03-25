/*************************************************
 File: AccountDAO.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Customer class
 *************************************************/
public class Customer {
    private String username;
    private String password;
    private String name;
    private final PriorityQueue<AccountDTO> accounts;


    public Customer(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.accounts = new PriorityQueue<>();
    }
    public Customer(){
        this.accounts = new PriorityQueue<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addAccount(AccountDTO account) {
        accounts.add(account);
    }


    public AccountDTO nextAccount() {
        return accounts.remove();
    }


    public void deposit(AccountDTO account, double amount) {
        account.deposit(amount);
    }


    public void withdraw(AccountDTO account, double amount) {
        account.withdraw(amount);
    }


    public double checkBalance(AccountDTO account) {
        return account.getBalance();
    }


    @Override
    public String toString() {
        return "Customer{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", fullName='" + name + '\'' + '}';
    }
}
