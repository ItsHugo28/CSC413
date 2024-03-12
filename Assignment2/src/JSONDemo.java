/*************************************************
 File: JSONDemo.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: JSON demo to test out
 *************************************************/
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

public class JSONDemo {

    static ObjectMapper objectMapper = new ObjectMapper();

    static String jsonAccountString = "{\"accountId\": 100, \"accountType\": \"Checking\", \"balance\": 5000.0, \"creationDate\": \"2024-03-10\", \"customer\": { \"username\": \"John Smith\", \"password\": \"123456\", \"name\": \"John Smith\" }}";
    static String jsonAccountsString = "[{\"accountId\": 200, \"accountType\": \"Savings\", \"balance\": 8000.0, \"creationDate\": \"2024-03-10\", \"customer\": { \"username\": \"Jane Smith\", \"password\": \"abcdef\", \"name\": \"Jane Smith\" }}, {\"accountId\": 300, \"accountType\": \"Checking\", \"balance\": 6000.0, \"creationDate\": \"2024-03-10\", \"customer\": { \"username\": \"John Jones\", \"password\": \"xyz123\", \"name\": \"John Jones\" }}]";

    public static void main(String[] args) {
        objectMapper = new ObjectMapper();

        Account account = new Account(100, "Checking", 5000.0, new Date(), new Customer("John Doe", "123456", "John Doe"));
        String accountString = "";
        try {
            accountString = objectMapper.writeValueAsString(account);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        }
        System.out.println("JSON String version of Account object:\n" + accountString);

        readJsonAccount();
        readJsonAccounts();
    }

    public static void readJsonAccount() {
        Account accountObj = null;
        try {
            accountObj = objectMapper.readValue(jsonAccountString, Account.class);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        }
        System.out.println("\nReading data from JSON String of single account:");
        if (accountObj != null) {
            System.out.println("Account ID: " + accountObj.getAccountId());
            System.out.println("Account Type: " + accountObj.getAccountType());
            System.out.println("Balance: " + accountObj.getBalance());
            System.out.println("Creation Date: " + accountObj.getCreationDate());
            Customer customer = accountObj.getCustomer();
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Customer Username: " + customer.getUsername());
            System.out.println("Customer Password: " + customer.getPassword());
        }
    }

    public static void readJsonAccounts() {
        List<Account> accountList = null;
        try {
            accountList = objectMapper.readValue(jsonAccountsString, new TypeReference<List<Account>>() {});
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        }
        System.out.println("\nReading data from multiple accounts in JSON array:");
        for (Account account : accountList) {
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Creation Date: " + account.getCreationDate());
            Customer customer = account.getCustomer();
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Customer Username: " + customer.getUsername());
            System.out.println("Customer Password: " + customer.getPassword());
            System.out.println();
        }
    }
}
