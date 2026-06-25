package vu.firstbankuganda;

public class CurrentAccount extends Account {

    // Constructor - passes all details up to the parent Account class
    public CurrentAccount(String firstName, String lastName, String nationalID,
                          String email, String phoneNumber, String dateOfBirth,
                          String branch, double openingDeposit) {
        super(firstName, lastName, nationalID, email, phoneNumber, 
              dateOfBirth, branch, openingDeposit);
    }

    // Minimum deposit for Current account is 200,000 UGX
    @Override
    public double minimumDeposit() {
        return 200000;
    }

    // Returns the account type name
    @Override
    public String getAccountType() {
        return "Current";
    }
}
    

