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
import java.util.List;

public class AccountDAOConcrete implements AccountDAO {

    private final Connection conn;

    public AccountDAOConcrete(Connection conn) {
        this.conn = conn;
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


    @Override
    public boolean updateAccount(AccountDTO account) {
        String sql = "UPDATE accounts SET account_type = ?, balance = ? WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountType());
            stmt.setDouble(2, account.getBalance());
            stmt.setInt(3, account.getAccountId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
