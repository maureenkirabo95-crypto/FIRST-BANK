package vu.firstbankuganda;

public class SavingsAccount extends Account {

    // Constructor - passes all details up to the parent Account class
    public SavingsAccount(String firstName, String lastName, String nationalID,
                          String email, String phoneNumber, String dateOfBirth,
                          String branch, double openingDeposit) {
        super(firstName, lastName, nationalID, email, phoneNumber, 
              dateOfBirth, branch, openingDeposit);
    }

    // Minimum deposit for Savings is 50,000 UGX
    @Override
    public double minimumDeposit() {
        return 50000;
    }

    // Returns the account type name
    @Override
    public String getAccountType() {
        return "Savings";
    }
}
    


