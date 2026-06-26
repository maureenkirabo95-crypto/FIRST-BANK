package vu.firstbankuganda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankAccountForm extends JFrame {

    // ---- Input Fields ----
    private JTextField txtFirstName, txtLastName, txtNationalID;
    private JTextField txtEmail, txtConfirmEmail;
    private JTextField txtPhone, txtDeposit;
    private JTextField txtSecondNIN;
    private JPasswordField txtPIN, txtConfirmPIN;

    // ---- Date of Birth Combo Boxes ----
    private JComboBox<String> cbYear, cbMonth, cbDay;

    // ---- Account Type and Branch ----
    private JComboBox<String> cbAccountType, cbBranch;

    // ---- Buttons ----
    private JButton btnSubmit, btnReset;

    // ---- Summary Area ----
    private JTextArea txtSummary;

    // ---- Error Labels ----
    private JLabel lblErrFirstName, lblErrLastName, lblErrNIN;
    private JLabel lblErrEmail, lblErrConfirmEmail;
    private JLabel lblErrPhone, lblErrPIN, lblErrConfirmPIN;
    private JLabel lblErrDOB, lblErrDeposit, lblErrSecondNIN;
    

    public BankAccountForm() {
        setTitle("First Bank Uganda - New Account Opening Form");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    // Builds all the components on the form
    private void initComponents() {

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Title label
        JLabel lblTitle = new JLabel("FIRST BANK UGANDA");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 51, 102));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubTitle = new JLabel("New Account Opening Form");
        lblSubTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(lblSubTitle);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // ---- PERSONAL DETAILS SECTION ----
        mainPanel.add(createSectionLabel("Personal Details"));

        // First Name
        txtFirstName = new JTextField(20);
        lblErrFirstName = new JLabel(" ");
        lblErrFirstName.setForeground(Color.RED);
        mainPanel.add(createRow("First Name:", txtFirstName, lblErrFirstName));

        // Last Name
        txtLastName = new JTextField(20);
        lblErrLastName = new JLabel(" ");
        lblErrLastName.setForeground(Color.RED);
        mainPanel.add(createRow("Last Name:", txtLastName, lblErrLastName));

        // National ID
        txtNationalID = new JTextField(20);
        lblErrNIN = new JLabel(" ");
        lblErrNIN.setForeground(Color.RED);
        mainPanel.add(createRow("National ID (NIN):", txtNationalID, lblErrNIN));

        // Email
        txtEmail = new JTextField(20);
        lblErrEmail = new JLabel(" ");
        lblErrEmail.setForeground(Color.RED);
        mainPanel.add(createRow("Email:", txtEmail, lblErrEmail));

        // Confirm Email
        txtConfirmEmail = new JTextField(20);
        lblErrConfirmEmail = new JLabel(" ");
        lblErrConfirmEmail.setForeground(Color.RED);
        mainPanel.add(createRow("Confirm Email:", txtConfirmEmail, lblErrConfirmEmail));

        // Phone Number
        txtPhone = new JTextField(20);
        lblErrPhone = new JLabel(" ");
        lblErrPhone.setForeground(Color.RED);
        mainPanel.add(createRow("Phone (+256XXXXXXXXX):", txtPhone, lblErrPhone));
        // PIN
        txtPIN = new JPasswordField(20);
        lblErrPIN = new JLabel(" ");
        lblErrPIN.setForeground(Color.RED);
        mainPanel.add(createRow("PIN (4-6 digits):", txtPIN, lblErrPIN));

        // Confirm PIN
        txtConfirmPIN = new JPasswordField(20);
        lblErrConfirmPIN = new JLabel(" ");
        lblErrConfirmPIN.setForeground(Color.RED);
        mainPanel.add(createRow("Confirm PIN:", txtConfirmPIN, lblErrConfirmPIN));

        // ---- DATE OF BIRTH SECTION ----
        mainPanel.add(createSectionLabel("Date of Birth"));

        // Year combo box - from 1950 to 2008
        cbYear = new JComboBox<>();
        for (int y = 1950; y <= 2008; y++) {
            cbYear.addItem(String.valueOf(y));
        }

        // Month combo box
        cbMonth = new JComboBox<>(new String[]{
            "January","February","March","April","May","June",
            "July","August","September","October","November","December"
        });

        // Day combo box - starts with 31 days
        cbDay = new JComboBox<>();
        for (int d = 1; d <= 31; d++) {
            cbDay.addItem(String.valueOf(d));
        }

        // When month or year changes, update the days available
        cbMonth.addActionListener(e -> updateDays());
        cbYear.addActionListener(e -> updateDays());

        // Put Year, Month, Day in one row
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.setBackground(Color.WHITE);
        dobPanel.add(new JLabel("Year:")); dobPanel.add(cbYear);
        dobPanel.add(new JLabel("Month:")); dobPanel.add(cbMonth);
        dobPanel.add(new JLabel("Day:")); dobPanel.add(cbDay);
        lblErrDOB = new JLabel(" ");
        lblErrDOB.setForeground(Color.RED);
        mainPanel.add(dobPanel);
        mainPanel.add(lblErrDOB);

        // ---- ACCOUNT DETAILS SECTION ----
        mainPanel.add(createSectionLabel("Account Details"));

        // Account Type
        cbAccountType = new JComboBox<>(new String[]{
            "Select Type","Savings","Current","Fixed Deposit","Student","Joint"
        });
        mainPanel.add(createRow("Account Type:", cbAccountType, new JLabel(" ")));

        // Second NIN - only visible for Joint account
        txtSecondNIN = new JTextField(20);
        lblErrSecondNIN = new JLabel(" ");
        lblErrSecondNIN.setForeground(Color.RED);
        txtSecondNIN.setVisible(false);
        mainPanel.add(createRow("Second NIN (Joint):", txtSecondNIN, lblErrSecondNIN));

        // Show/hide second NIN field based on account type selected
        cbAccountType.addActionListener(e -> {
            String selected = (String) cbAccountType.getSelectedItem();
            txtSecondNIN.setVisible("Joint".equals(selected));
            lblErrSecondNIN.setVisible("Joint".equals(selected));
            revalidate();
            repaint();
        });

        // Branch
        cbBranch = new JComboBox<>(new String[]{
            "Select Branch","Kampala","Gulu","Mbarara","Jinja","Mbale"
        });
        mainPanel.add(createRow("Branch:", cbBranch, new JLabel(" ")));

        // Opening Deposit
        txtDeposit = new JTextField(20);
        lblErrDeposit = new JLabel(" ");
        lblErrDeposit.setForeground(Color.RED);
        mainPanel.add(createRow("Opening Deposit (UGX):", txtDeposit, lblErrDeposit));
        // ---- BUTTONS ----
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(new Color(0, 51, 102));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 13));

        btnReset = new JButton("Reset");
        btnReset.setBackground(new Color(204, 0, 0));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Arial", Font.BOLD, 13));

        btnSubmit.addActionListener(e -> submitForm());
        btnReset.addActionListener(e -> resetForm());

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnReset);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(buttonPanel);

        // ---- SUMMARY AREA ----
        mainPanel.add(createSectionLabel("Account Summary is Below:"));
        txtSummary = new JTextArea(5, 40);
        txtSummary.setEditable(false);
        txtSummary.setLineWrap(true);
        txtSummary.setWrapStyleWord(true);
        txtSummary.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtSummary.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtSummary);
        mainPanel.add(scrollPane);

        // ---- WRAP IN SCROLL PANE ----
        JScrollPane mainScroll = new JScrollPane(mainPanel);
        add(mainScroll);

    } // end of initComponents
    // Creates a bold section heading label
    private JLabel createSectionLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setForeground(new Color(0, 51, 102));
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        return lbl;
    }

    // Creates a row with a label, input field and error label
    private JPanel createRow(String labelText, JComponent field, JLabel errorLabel) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(labelText);
        lbl.setPreferredSize(new Dimension(200, 25));
        field.setPreferredSize(new Dimension(200, 25));
        row.add(lbl);
        row.add(field);
        row.add(errorLabel);
        return row;
    }

    // Updates the Day combo box based on selected Month and Year
    private void updateDays() {
        int month = cbMonth.getSelectedIndex() + 1;
        int year = Integer.parseInt((String) cbYear.getSelectedItem());

        int maxDays;
        if (month == 2) {
            // Check for leap year
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                maxDays = 29;
            } else {
                maxDays = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        } else {
            maxDays = 31;
        }

        int currentDay = cbDay.getSelectedIndex() + 1;
        cbDay.removeAllItems();
        for (int d = 1; d <= maxDays; d++) {
            cbDay.addItem(String.valueOf(d));
        }
        if (currentDay <= maxDays) {
            cbDay.setSelectedIndex(currentDay - 1);
        }
    }
        // Validates all fields and returns true if everything is correct
        private boolean validateForm() {
          boolean valid = true;

        // Clear all previous error messages first
        lblErrFirstName.setText(" ");
        lblErrLastName.setText(" ");
        lblErrNIN.setText(" ");
        lblErrEmail.setText(" ");
        lblErrConfirmEmail.setText(" ");
        lblErrPhone.setText(" ");
        lblErrPIN.setText(" ");
        lblErrConfirmPIN.setText(" ");
        lblErrDOB.setText(" ");
        lblErrDeposit.setText(" ");
        lblErrSecondNIN.setText(" ");

        // First Name validation
        String firstName = txtFirstName.getText().trim();
        if (firstName.isEmpty() || !firstName.matches("[a-zA-Z]{2,30}")) {
            lblErrFirstName.setText("Letters only, 2-30 characters");
            valid = false;
        }

        // Last Name validation
        String lastName = txtLastName.getText().trim();
        if (lastName.isEmpty() || !lastName.matches("[a-zA-Z]{2,30}")) {
            lblErrLastName.setText("Letters only, 2-30 characters");
            valid = false;
        }

        // National ID validation
        String nin = txtNationalID.getText().trim();
        if (!nin.matches("[A-Z0-9]{14}")) {
            lblErrNIN.setText("Exactly 14 uppercase alphanumeric characters");
            valid = false;
        }

        // Email validation
        String email = txtEmail.getText().trim();
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            lblErrEmail.setText("Enter a valid email address");
            valid = false;
        }

        // Confirm Email validation
        String confirmEmail = txtConfirmEmail.getText().trim();
        if (!confirmEmail.equals(email)) {
            lblErrConfirmEmail.setText("Emails do not match");
            valid = false;
        }

        // Phone validation
        String phone = txtPhone.getText().trim();
        if (!phone.matches("\\+256\\d{9}")) {
            lblErrPhone.setText("Format: +256XXXXXXXXX");
            valid = false;
        }

        // PIN validation
        String pin = new String(txtPIN.getPassword()).trim();
        if (!pin.matches("\\d{4,6}") || pin.chars().distinct().count() == 1) {
            lblErrPIN.setText("4-6 digits, not all identical (e.g. not 0000)");
            valid = false;
        }

        // Confirm PIN validation
        String confirmPIN = new String(txtConfirmPIN.getPassword()).trim();
        if (!confirmPIN.equals(pin)) {
            lblErrConfirmPIN.setText("PINs do not match");
            valid = false;
        }

        // Age validation from Date of Birth
        int year = Integer.parseInt((String) cbYear.getSelectedItem());
        int month = cbMonth.getSelectedIndex() + 1;
        int day = Integer.parseInt((String) cbDay.getSelectedItem());
        java.util.Calendar dob = java.util.Calendar.getInstance();
        dob.set(year, month - 1, day);
        java.util.Calendar today = java.util.Calendar.getInstance();
        int age = today.get(java.util.Calendar.YEAR) - dob.get(java.util.Calendar.YEAR);
        if (today.get(java.util.Calendar.DAY_OF_YEAR) < dob.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--;
        }
        if (age < 18 || age > 75) {
            lblErrDOB.setText("Age must be between 18 and 75");
            valid = false;
        }

        // Account Type validation
        String accountType = (String) cbAccountType.getSelectedItem();
        if (accountType.equals("Select Type")) {
            JOptionPane.showMessageDialog(this, "Please select an Account Type");
            valid = false;
        }

        // Student age check
        if (accountType.equals("Student") && (age < 18 || age > 25)) {
            lblErrDOB.setText("Student account requires age 18-25");
            valid = false;
        }

        // Branch validation
        String branch = (String) cbBranch.getSelectedItem();
        if (branch.equals("Select Branch")) {
            JOptionPane.showMessageDialog(this, "Please select a Branch");
            valid = false;
        }

        // Opening Deposit validation
        String depositText = txtDeposit.getText().trim();
        double deposit = 0;
        try {
            deposit = Double.parseDouble(depositText);
        } catch (NumberFormatException e) {
            lblErrDeposit.setText("Enter a valid number");
            valid = false;
        }

        // Check minimum deposit against account type
        if (!accountType.equals("Select Type") && deposit > 0) {
            double minDeposit = 0;
            if (accountType.equals("Savings")) minDeposit = 50000;
            else if (accountType.equals("Current")) minDeposit = 200000;
            else if (accountType.equals("Fixed Deposit")) minDeposit = 1000000;
            else if (accountType.equals("Student")) minDeposit = 10000;
            else if (accountType.equals("Joint")) minDeposit = 100000;

            if (deposit < minDeposit) {
                lblErrDeposit.setText("Minimum deposit for " + accountType + " is UGX " + (int)minDeposit);
                valid = false;
            }
        }

        // Second NIN validation for Joint account
        if (accountType.equals("Joint")) {
            String secondNIN = txtSecondNIN.getText().trim();
            if (!secondNIN.matches("[A-Z0-9]{14}")) {
                lblErrSecondNIN.setText("Exactly 14 uppercase alphanumeric characters");
                valid = false;
            }
        }

        return valid;
    }// Generates account number based on branch and year
    private String generateAccountNumber(String branch) {
        String branchCode;
        switch (branch) {
            case "Kampala": branchCode = "KLA"; break;
            case "Gulu": branchCode = "GUL"; break;
            case "Mbarara": branchCode = "MBR"; break;
            case "Jinja": branchCode = "JJA"; break;
            case "Mbale": branchCode = "MBL"; break;
            default: branchCode = "UNK";
        }
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int randomNum = (int)(Math.random() * 900000) + 100000;
        return branchCode + "-" + year + "-" + randomNum;
    }

    // Called when Submit button is clicked
    private void submitForm() {
        if (!validateForm()) {
            JOptionPane.showMessageDialog(this,
                "Please fix the errors highlighted in red before submitting.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Collect all valid values
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String nin = txtNationalID.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String accountType = (String) cbAccountType.getSelectedItem();
        String branch = (String) cbBranch.getSelectedItem();
        double deposit = Double.parseDouble(txtDeposit.getText().trim());
        int year = Integer.parseInt((String) cbYear.getSelectedItem());
        int month = cbMonth.getSelectedIndex() + 1;
        int day = Integer.parseInt((String) cbDay.getSelectedItem());
        String dob = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);

        // Generate account number
        String accountNumber = generateAccountNumber(branch);

        // Build summary record
        String summary = "ACC: " + accountNumber + " | " +
                firstName + " " + lastName + " | " +
                accountType + " | " + branch + " | " +
                "DOB " + dob + " | " + phone + " | " +
                "Deposit " + String.format("%,.0f", deposit) + " | " + email;

        // Display in summary area
        txtSummary.setText(summary);

        // Save to database
        DatabaseHelper.saveAccount(accountNumber, firstName, lastName, nin,
                email, phone, dob, accountType, branch, deposit);

        JOptionPane.showMessageDialog(this,
                "Account created successfully!\n" + accountNumber,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Reset all fields
    private void resetForm() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtNationalID.setText("");
        txtEmail.setText("");
        txtConfirmEmail.setText("");
        txtPhone.setText("");
        txtPIN.setText("");
        txtConfirmPIN.setText("");
        txtDeposit.setText("");
        txtSecondNIN.setText("");
        txtSummary.setText("");
        cbAccountType.setSelectedIndex(0);
        cbBranch.setSelectedIndex(0);
        cbYear.setSelectedIndex(0);
        cbMonth.setSelectedIndex(0);
        cbDay.setSelectedIndex(0);
        lblErrFirstName.setText(" ");
        lblErrLastName.setText(" ");
        lblErrNIN.setText(" ");
        lblErrEmail.setText(" ");
        lblErrConfirmEmail.setText(" ");
        lblErrPhone.setText(" ");
        lblErrPIN.setText(" ");
        lblErrConfirmPIN.setText(" ");
        lblErrDOB.setText(" ");
        lblErrDeposit.setText(" ");
        lblErrSecondNIN.setText(" ");
    }

    // Main method - launches the form
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BankAccountForm().setVisible(true);
        });
    }

} // end of BankAccountForm class
