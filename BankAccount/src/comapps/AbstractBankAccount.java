package comapps;

public abstract class AbstractBankAccount implements BankAccount {
    /** variable balance stores balance linked to account. */
    private double balance;
    /** variable isFrozen identifies if account is frozen or not. */
    private boolean isFrozen;

    // Initializes balance with 0, isFrozen with false.
    AbstractBankAccount() {
        balance = 0;
        isFrozen = false;
    }

    /**
     * The function deposits an amount.
     * It checks if the account is not frozen
     * and the amount deposited is valid.
     * @param amount
     */
    public void deposit(final double amount) {
        if (isFrozen) {
            throw new IllegalStateException("Account is frozen");
        } else if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit must be greater than 0");
        } else {
            balance += amount;
            System.out.println(
                    "Deposit successful. Current Savings: " + balance);
        }
    }


    /**
     * The function withdraws an amount. It checks if the account is not
     * frozen and the amount to be withdrawn is valid.
     * @param amount
     */
    public void withdraw(final double amount) {
        if (isFrozen) {
            throw new IllegalStateException("Account is frozen");
        } else if (amount > balance) {
            throw new IllegalArgumentException(
                    "Withdraw amount is too high. "
                    + "You don't have enough money.");
        } else if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount to be withdrawn must be greater than 0");
        } else {
            balance = balance - amount;
            System.out.println(
                    "Withdrawal successful. Current Savings: " + balance);
        }
    }

    /**
     * Returns the current balance.
     * @return getBalance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Checks if account is frozen.
     * @return isFrozen
     */
    public boolean isFrozen() {
        return isFrozen;
    }

    /** Sets account to frozen. */
    void freezeAccount() {
        isFrozen = true;
    }

    /** Sets account to unfrozen. */
    void unfreezeAccount() {
        isFrozen = false;
    }
}
