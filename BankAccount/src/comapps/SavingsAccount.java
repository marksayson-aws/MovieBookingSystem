package comapps;

public class SavingsAccount extends AbstractBankAccount {
    /** Holds the name of the owner’s account. */
    private String ownerName;

    // Initializes the ownerName.
    SavingsAccount(final String name) {
        this.ownerName = name;
    }


    // Returns the ownerName.
    final String getOwnerName() {
        return ownerName;
    }
}
