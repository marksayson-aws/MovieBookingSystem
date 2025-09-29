package comapps;

/**
 * Check style: Using .* on import should be avoided.
 * Check style: Magic numbers on Orders(3-12).
 * Code Reviewer: Christine Marasigan.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class BankAccountTest {

    /** The SavingsAccount instance used in the tests. */
    private SavingsAccount sa;

    /** valid deposit amount for tests. */
    private static final int VALID_DEPOSIT = 1000;

    /** valid withdrawal amount for tests. */
    private static final int VALID_WITHDRAW = 500;

    /** negative amount for testing invalid deposits/withdrawals. */
    private static final int NEGATIVE_AMOUNT = -100;

    /** excessive withdrawal amount for testing insufficient funds. */
    private static final int EXCESS_WITHDRAW = 1500;

    /** withdrawal amount used in multiple transactions tests. */
    private static final int MULTI_TXN_WITHDRAW = 100;


    @BeforeEach
    void setUp() {
        sa = new SavingsAccount("Betlog");
        System.out.println("[New Account Made for New Test Case]");
    }

    @Order(1)
    @DisplayName("Test 1: Test account creation")
    @Test
    void testAccountCreation() {
        assertEquals("Betlog", sa.getOwnerName());
        System.out.println(sa.getOwnerName());
        assertEquals(0, sa.getBalance());
        assertFalse(sa.isFrozen());
    }

    @Order(2)
    @DisplayName("Test 2: Test deposit")
    @Test
    void testValidDeposit() {
        sa.deposit(VALID_DEPOSIT);
        assertEquals(VALID_DEPOSIT, sa.getBalance());
    }

    @Order(3)
    @DisplayName("Test 3: Deposit zero (ERROR)")
    @Test
    void testInvalidDepositZero() {
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> sa.deposit(0));
        System.out.println(exception.getMessage());
        assertEquals("Deposit must be greater than 0", exception.getMessage());
    }

    @Order(4)
    @DisplayName("Test 4: Deposit a negative value (ERROR)")
    @Test
    void testInvalidDepositNegative() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> sa.deposit(NEGATIVE_AMOUNT));
        System.out.println(exception.getMessage());
        assertEquals("Deposit must be greater than 0", exception.getMessage());
    }

    @Order(5)
    @DisplayName("Test 5: Test a valid withdraw")
    @Test
    void testValidWithdrawal() {
        sa.deposit(VALID_DEPOSIT);
        sa.withdraw(VALID_WITHDRAW);
        assertEquals(VALID_DEPOSIT - VALID_WITHDRAW, sa.getBalance());
    }

    @Order(6)
    @DisplayName("Test 6: Withdraw while funds are insufficient (ERROR)")
    @Test
    void testWithdrawalInsufficientFunds() {
        sa.deposit(VALID_WITHDRAW);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> sa.withdraw(EXCESS_WITHDRAW));
        System.out.println(exception.getMessage());
        assertEquals("Withdraw amount is too high. "
                + "You don't have enough money.", exception.getMessage());
    }

    @Order(7)
    @DisplayName("Test 7: Withdraw a negative amount (ERROR)")
    @Test
    void testWithdrawalNegative() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> sa.withdraw(NEGATIVE_AMOUNT));
        System.out.println(exception.getMessage());
        assertEquals("Amount to be withdrawn "
                + "must be greater than 0", exception.getMessage());
    }

    @Order(8)
    @DisplayName("Test 8: Deposit to a frozen account (ERROR)")
    @Test
    void testDepositWhileFrozen() {
        sa.freezeAccount();
        Exception exception = assertThrows(IllegalStateException.class,
                () -> sa.deposit(VALID_DEPOSIT));
        System.out.println(exception.getMessage());
        assertEquals("Account is frozen", exception.getMessage());
    }

    @Order(9)
    @DisplayName("Test 9: Withdraw from a frozen account (ERROR)")
    @Test
    void testWithdrawWhileFrozen() {
        sa.deposit(VALID_WITHDRAW);
        sa.freezeAccount();
        Exception exception = assertThrows(IllegalStateException.class,
                () -> sa.withdraw(VALID_WITHDRAW));
        System.out.println(exception.getMessage());
        assertEquals("Account is frozen", exception.getMessage());
    }

    @Order(10)
    @DisplayName("Test 10: Unfreeze an account and withdraw")
    @Test
    void testUnfreezeAndWithdraw() {
        sa.deposit(VALID_WITHDRAW);
        sa.freezeAccount();
        sa.unfreezeAccount();
        sa.withdraw(MULTI_TXN_WITHDRAW);
        assertEquals(VALID_WITHDRAW - MULTI_TXN_WITHDRAW, sa.getBalance());
        assertFalse(sa.isFrozen());
    }

    @Order(11)
    @DisplayName("Test 11: Check if Account is Frozen")
    @Test
    void testCheckAccountIsFrozen() {
        sa.freezeAccount();
        assertTrue(sa.isFrozen());
        sa.unfreezeAccount();
        assertFalse(sa.isFrozen());
    }

    @Order(12)
    @DisplayName("Test 12: Show Balance After Multiple Transactions")
    @Test
    void testBalanceAfterMultipleTransactions() {
        sa.deposit(VALID_DEPOSIT);
        sa.withdraw(VALID_WITHDRAW);
        sa.withdraw(MULTI_TXN_WITHDRAW);
        assertEquals(VALID_DEPOSIT - VALID_WITHDRAW - MULTI_TXN_WITHDRAW,
                sa.getBalance(), "Balance should be Php 400");
    }
}
