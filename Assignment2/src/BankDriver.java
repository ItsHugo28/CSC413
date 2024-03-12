/*************************************************
 File: AccountDAO.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Bank Driver to test out the objects
 *************************************************/
import java.util.Date;
import java.util.List;

public class BankDriver {

    public static void main(String[] args) {

        AccountDataConnection dataConnection = new AccountDataConnection();
        dataConnection.connect();

        AccountDAO accountDAO = new AccountDAOConcrete(dataConnection.getConnection());

        int highestAccountId = accountDAO.getHighestAccountId();
        int newAccountId = highestAccountId + 1;


        Date currentDate = new Date();
        AccountDTO account1 = new AccountDTO(newAccountId++, "Checking", 2000.0, currentDate, "user", "pass", "John Smith");
        AccountDTO account2 = new AccountDTO(newAccountId++, "Checking", 3000.0, currentDate, "user", "pass", "Jane Smith");


        accountDAO.insertAccount(account1);
        accountDAO.insertAccount(account2);


        List<AccountDTO> allAccounts = accountDAO.getAllAccounts();

        PriorityQueue<AccountDTO> accountPriorityQueue = new PriorityQueue<>();


        for (AccountDTO account : allAccounts) {
            accountPriorityQueue.add(account);
        }

        while (!accountPriorityQueue.isEmpty()) {
            AccountDTO account = accountPriorityQueue.remove();
            System.out.println("Account balance: " + account.getBalance() + " " + account.getCreationDate());
        }

        dataConnection.close();
    }
}
