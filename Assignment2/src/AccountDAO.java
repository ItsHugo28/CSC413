/*************************************************
 File: AccountDAO.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Account DOA interface
 *************************************************/
import java.util.List;

public interface AccountDAO {
    boolean insertAccount(AccountDTO account);

    boolean updateAccount(AccountDTO account);

    boolean deleteAccount(int accountId);

    AccountDTO getId(int accountId);

    List<AccountDTO> getAllAccounts();

    int getHighestAccountId();
}
