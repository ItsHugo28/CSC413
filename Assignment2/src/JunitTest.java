/*************************************************
 File: JunitTest.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: JUnit Tester
 *************************************************/
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class JunitTest {

    @Test
    public void testPriorityQueueOrder() {
        PriorityQueue<AccountDTO> queue = new PriorityQueue<>();

        AccountDTO account1 = new AccountDTO(1, "Checking", 1000.0, new Date(System.currentTimeMillis() - 1000), "user1", "pass1", "John Doe");
        AccountDTO account2 = new AccountDTO(2, "Savings", 2000.0, new Date(System.currentTimeMillis() - 2000), "user2", "pass2", "Jane Doe");
        AccountDTO account3 = new AccountDTO(3, "Investment", 3000.0, new Date(System.currentTimeMillis() - 3000), "user3", "pass3", "Alice");

        queue.add(account1);
        queue.add(account2);
        queue.add(account3);


        assertEquals(account3, queue.remove());
        assertEquals(account2, queue.remove());
        assertEquals(account1, queue.remove());
    }
}
