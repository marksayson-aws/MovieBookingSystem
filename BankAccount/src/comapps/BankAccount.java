package comapps;

public interface BankAccount {
    /**
     * method that deposits money to account (after various checks).
     * @param amount
     */
    void deposit(double amount);
    /**
     * method that withdraws money from account (after various checks).
     * @param amount
     */
    void withdraw(double amount);
    /**
     * stores balance of account.
     * @return getBalance
     */
    double getBalance();
    /**
     * if account is frozen or not.
     * @return isFrozen
     */
    boolean isFrozen();

//    /**
//     * Main method for testing.
//     * @param args
//     */
//    static void main(final String[] args) {
//        int test1 = 1000;
//        int test2 = 0;
//        int test3 = -500;
//        int test4 = 500;
//        int test5 = 1500;
//        int test6 = -100;
//        int test7 = 11500;
//        int test8 = 500;
//        int test9 = 100;
//
//        // create saving account
//        SavingsAccount sa = new SavingsAccount("Betlog");
//        System.out.println("Account name: " + sa.getOwnerName());
//        // deposit 1000
//        sa.deposit(test1);
//        // deposit 0 (error)
//        sa.deposit(test2);
//        // deposit negative (error)
//        sa.deposit(test3);
//        // withdraw sufficient
//        sa.withdraw(test4);
//        // withdraw insufficient (error)
//        sa.withdraw(test5);
//        // withdraw negative (error)
//        sa.withdraw(test6);
//        //freeze account and deposit money (error)
//        sa.freezeAccount();
//        sa.deposit(test7);
//        // withdraw from a frozen account (error)
//        sa.withdraw(test8);
//        // unfreeze and withdraw
//        sa.unfreezeAccount();
//        sa.withdraw(test9);
//        // check if account is frozen
//        if (sa.isFrozen()) {
//            System.out.println(sa.getOwnerName() + " is Frozen");
//        } else {
//            System.out.println(sa.getOwnerName() + " is not Frozen");
//        }
//        // check account balance
//        System.out.println(sa.getBalance());
//
//    }
}
