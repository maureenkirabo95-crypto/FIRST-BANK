package vu.firstbankuganda;

public abstract class Account {

    // These are the common details every account must have
    private String firstName;
    private String lastName;
    private String nationalID;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String branch;
    private double openingDeposit;
    private String accountNumber;

    // Constructor - sets up the account with all details
    public Account(String firstName, String lastName, String nationalID,
                   String email, String phoneNumber, String dateOfBirth,
                   String branch, double openingDeposit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.branch = branch;
        this.openingDeposit = openingDeposit;
    }

    // Abstract method - every subclass MUST define its own minimum deposit
    public abstract double minimumDeposit();

    // Abstract method - every subclass MUST return its account type name
    public abstract String getAccountType();

    // Checks if the deposit entered meets the minimum required
    public boolean isDepositValid() {
        return openingDeposit >= minimumDeposit();
    }

    // Getters - used to read the private fields
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNationalID() { return nationalID; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getBranch() { return branch; }
    public double getOpeningDeposit() { return openingDeposit; }
    public String getAccountNumber() { return accountNumber; }

    // Setter for account number - assigned after validation
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
    

