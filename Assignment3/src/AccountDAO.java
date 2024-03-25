/*************************************************
 File: AccountDAO.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Account DOA interface
 *************************************************/
import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    boolean insertAccount(AccountDTO account);

    int updateAccount(AccountDTO account);

    boolean deleteAccount(int accountId);

    AccountDTO getId(int accountId);

    int save(AccountDTO account) throws SQLException;

    List<AccountDTO> getAllAccounts();

    int getHighestAccountId();
}
