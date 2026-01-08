/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fobb;

/**
 *
 * @author AmmarKapadia
 */
public class Account {
    private String accountNumber;   // All private attributes
    private String accountHolder;
    private double balance;
    
    public Account(String accountNumber, String accountHolder, double initialBalance) {  // Constructor
        setAccountNumber(accountNumber); // Implement Account Number Validation using Mutator
        this.accountHolder = accountHolder;
        setInitialBalance(initialBalance); // Implement Initial Balance Validation using Mutator
    }

// --- Accessors and Mutators ---
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setInitialBalance(double initialBalance){
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Warning: Initial balance cannot be negative. Setting to 0.");
            this.balance = 0;
        }
    }
    
    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Account number cannot be null.");
        }

        int length = accountNumber.length();
        if (length < 8 || length > 10) {
            throw new IllegalArgumentException("Account number must be 8-10 digits long.");
        }

        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

// Utilizing Methods
    public void deposit(double amount) { // deposit method adds the deposit amount to the total balance.
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void displayAccountInfo() { // displayAccountInfo method outputs information about the bank account.
        System.out.println("---------------------------");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Current Balance: $%.2f" , balance);
        System.out.println("");
        System.out.println("---------------------------");
    }

// Overridable Methods for Subclasses
    public boolean withdraw(double amount) { // withdraw method subtracts the withdraw amount from the total balance
        // If valid, returns true. Else returns false with an appropriate message. Subclasses will override this method.
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }
    
    public void calculateInterest() { // Calculates interest. Base class provides a default (0%).
        // Subclasses will override this method with appropriate rates.
        System.out.println("No interest applicable for a standard account.");
    }
}

