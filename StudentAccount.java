package vu.firstbankuganda;

public class StudentAccount extends Account {

    // Constructor - passes all details up to the parent Account class
    public StudentAccount(String firstName, String lastName, String nationalID,
                          String email, String phoneNumber, String dateOfBirth,
                          String branch, double openingDeposit) {
        super(firstName, lastName, nationalID, email, phoneNumber, 
              dateOfBirth, branch, openingDeposit);
    }

    // Minimum deposit for Student account is 10,000 UGX
    @Override
    public double minimumDeposit() {
        return 10000;
    }

    // Returns the account type name
    @Override
    public String getAccountType() {
        return "Student";
    }
}
