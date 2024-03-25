/*************************************************
 File: AccountDAOConcrete.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Account DOA concrete class
 *************************************************/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountDAOConcrete implements AccountDAO {

    static Connection conn = null;

    AccountDAOConcrete() {

        AccountDataConnection dataConnection = new AccountDataConnection();
        dataConnection.connect();
        conn = dataConnection.getConnection();

    }

    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) AS count FROM accounts WHERE username = ?";
        try (Connection conn = AccountDataConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int getAccountId(String username, String password) {
        int accountId = -1;
        String sql = "SELECT account_id FROM accounts WHERE username = ? AND password = ?";
        try (Connection conn = AccountDataConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    accountId = rs.getInt("account_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountId;
    }


    @Override
    public int getHighestAccountId() {
        int highestAccountId = 0;
        String sql = "SELECT MAX(account_id) AS highest_id FROM accounts";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                highestAccountId = rs.getInt("highest_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highestAccountId;
    }

    @Override
    public boolean insertAccount(AccountDTO account) {
        String sql = "INSERT INTO accounts (account_id, account_type, balance, creation_date, username, password, name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, account.getAccountId());
            stmt.setString(2, account.getAccountType());
            stmt.setDouble(3, account.getBalance());
            stmt.setDate(4, new java.sql.Date(account.getCreationDate().getTime()));
            stmt.setString(5, account.getUsername());
            stmt.setString(6, account.getPassword());
            stmt.setString(7, account.getName());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public HashMap<String, String> validateLogin(String username, String password) {
        HashMap<String, String> hm = new HashMap<>();
        try {
            String sql = "SELECT username, password FROM accounts WHERE BINARY username = ? AND BINARY password = ?";
            try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
                pStatement.setString(1, username);
                pStatement.setString(2, password);
                try (ResultSet result = pStatement.executeQuery()) {
                    if (result.next()) {
                        hm.put("username", result.getString("username"));
                        hm.put("password", result.getString("password"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hm;
    }


    @Override
    public int updateAccount(AccountDTO account) {
        String sql = "UPDATE accounts SET account_type = ?, balance = ?, name = ? WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountType());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getName());
            stmt.setInt(4, account.getAccountId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean deleteAccount(int accountId) {
        String sql = "DELETE FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AccountDTO getId(int accountId) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAccountDTO(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int save(AccountDTO account) throws SQLException {
        int res = -1;
        String nameStr = account.getName();
        String[] strArr = nameStr.split(" ");
        String username = account.getUsername();
        String password = account.getPassword();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO accounts (account_id, account_type, balance, creation_date, username, password, name) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, account.getAccountId());
            stmt.setString(2, account.getAccountType());
            stmt.setDouble(3, account.getBalance());
            stmt.setDate(4, new java.sql.Date(account.getCreationDate().getTime()));
            stmt.setString(5, username);
            stmt.setString(6, password);
            stmt.setString(7, account.getName());
            res = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return res;
    }


    @Override
    public List<AccountDTO> getAllAccounts() {
        List<AccountDTO> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                accounts.add(mapResultSetToAccountDTO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    private AccountDTO mapResultSetToAccountDTO(ResultSet rs) throws SQLException {
        AccountDTO account = new AccountDTO(rs.getInt("account_id"), rs.getString("account_type"), rs.getDouble("balance"), rs.getDate("creation_date"), rs.getString("username"), rs.getString("password"), rs.getString("name"));
        return account;
    }

}
