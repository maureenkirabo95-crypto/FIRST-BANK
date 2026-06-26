package vu.firstbankuganda;

public class JointAccount extends Account {

    // Joint account requires a second NIN.
    private String secondNationalID;

    // Constructor
    public JointAccount(String firstName, String lastName, String nationalID,
                        String email, String phoneNumber, String dateOfBirth,
                        String branch, double openingDeposit,
                        String secondNationalID) {
        super(firstName, lastName, nationalID, email, phoneNumber,
              dateOfBirth, branch, openingDeposit);
        this.secondNationalID = secondNationalID;
    }

    // Minimum deposit for Joint account is 100,000 UGX
    @Override
    public double minimumDeposit() {
        return 100000;
    }

    // Returns the account type name
    @Override
    public String getAccountType() {
        return "Joint";
    }

    // Getter for second NIN
    public String getSecondNationalID() {
        return secondNationalID;
    }
}
