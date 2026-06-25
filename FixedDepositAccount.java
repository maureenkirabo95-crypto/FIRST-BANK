package vu.firstbankuganda;

public class FixedDepositAccount extends Account {

    // Constructor - passes all details up to the parent Account class
    public FixedDepositAccount(String firstName, String lastName, String nationalID,
                               String email, String phoneNumber, String dateOfBirth,
                               String branch, double openingDeposit) {
        super(firstName, lastName, nationalID, email, phoneNumber, 
              dateOfBirth, branch, openingDeposit);
    }

    // Minimum deposit for Fixed Deposit is 1,000,000 UGX
    @Override
    public double minimumDeposit() {
        return 1000000;
    }

    // Returns the account type name
    @Override
    public String getAccountType() {
        return "Fixed Deposit";
    }
}
