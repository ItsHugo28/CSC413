import java.util.Date;

public class AccountDTO extends Account {

    private String username;
    private String password;
    private String name;

    public AccountDTO(int accountId, String accountType, double balance, Date creationDate, String username, String password, String name) {
        super(accountId, accountType, balance, creationDate, new Customer(username, password, name));
        this.username = username;
        this.password = password;
        this.name = name;
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
}
