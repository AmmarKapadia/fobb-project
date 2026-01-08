package fobb;

/**
 *
 * @author Daniel Menezes
 */
public class BankingSystemTest {

    public static void main(String[] args) {
        System.out.println("=== BANKING SYSTEM TEST ===");

        // 1. Account (parent class)
        System.out.println("\n--- 1. Account (base class) ---");
        Account baseAccount = new Account(
                "10000001",
                "Alice Smith",
                1000.0
        );

        baseAccount.displayAccountInfo();

        System.out.println("\nDeposit 250 into base account");
        baseAccount.deposit(250.0);
        baseAccount.displayAccountInfo();

        System.out.println("\nTry to withdraw 2000 (more than balance)");
        boolean baseWithdraw1 = baseAccount.withdraw(2000.0);
        System.out.println("Success? " + baseWithdraw1);
        baseAccount.displayAccountInfo();

        System.out.println("\nWithdraw 500 (valid)");
        boolean baseWithdraw2 = baseAccount.withdraw(500.0);
        System.out.println("Success? " + baseWithdraw2);
        baseAccount.displayAccountInfo();

        System.out.println("\nBase account interest:");
        baseAccount.calculateInterest();

        // 2. SavingsAccount
        System.out.println("\n--- 2. SavingsAccount ---");
        SavingsAccount savings = new SavingsAccount(
                "12345678",
                "John Doe",
                5000.0,
                0.03, // 3% annual
                1000.0, // minimum balance
                2000.0 // per‑transaction limit
        );

        System.out.println("\nInitial savings account:");
        savings.displaySavingsAccountInfo();

        System.out.println("\nDeposit 500 into savings");
        savings.deposit(500.0);
        System.out.println("Balance: " + String.format("%.2f", savings.getBalance()));

        System.out.println("\nWithdraw 2500 (over limit 2000)");
        boolean savW1 = savings.withdraw(2500.0);
        System.out.println("Success? " + savW1);
        System.out.println("Balance: " + String.format("%.2f", savings.getBalance()));

        System.out.println("\nWithdraw 4500 (would go under minimum balance)");
        boolean savW2 = savings.withdraw(4500.0);
        System.out.println("Success? " + savW2);
        System.out.println("Balance: " + String.format("%.2f", savings.getBalance()));

        System.out.println("\nWithdraw 1000 (valid)");
        boolean savW3 = savings.withdraw(1000.0);
        System.out.println("Success? " + savW3);
        System.out.println("Balance: " + String.format("%.2f", savings.getBalance()));

        System.out.println("\nAdd 6 months to savings account");
        for (int i = 0; i < 6; i++) {
            savings.incrementMonth();
        }

        System.out.println("\nSavings interest (calculateInterest):");
        savings.calculateInterest();

        System.out.println("\nApply interest to savings account");
        savings.applyInterest();

        System.out.println("\nChange savings interest rate to 3.5%");
        savings.updateInterestRate(0.035);

        System.out.println("\nFinal savings account:");
        savings.displaySavingsAccountInfo();

        System.out.println("\nParent view of savings account:");
        savings.displayAccountInfo();

        // 3. CurrentAccount
        System.out.println("\n--- 3. CurrentAccount ---");
        CurrentAccount current = new CurrentAccount(
                "87654321",
                "Bob Johnson",
                500.0,
                1000.0, // overdraft limit
                10.0, // monthly fee
                true // business flag
        );

        System.out.println("\nInitial current account:");
        current.displayAccountInfo();
        current.checkOverdraft();

        System.out.println("\nWithdraw 300 (still positive)");
        boolean curW1 = current.withdraw(300.0);
        System.out.println("Success? " + curW1);
        current.displayAccountInfo();
        current.checkOverdraft();

        System.out.println("\nWithdraw 500 (go into overdraft but within limit)");
        boolean curW2 = current.withdraw(500.0);
        System.out.println("Success? " + curW2);
        current.displayAccountInfo();
        current.checkOverdraft();

        System.out.println("\nWithdraw 1000 (should fail: beyond overdraft)");
        boolean curW3 = current.withdraw(1000.0);
        System.out.println("Success? " + curW3);
        current.displayAccountInfo();
        current.checkOverdraft();

        System.out.println("\nApply monthly fee on current account");
        current.applyMonthlyFee();
        current.displayAccountInfo();
        current.checkOverdraft();

        System.out.println("\nCurrent account interest / charge:");
        current.calculateInterest();

        // 4. Polymorphism with Account[]
        System.out.println("\n--- 4. Polymorphism (Account[]) ---");
        Account[] accounts = {baseAccount, savings, current};

        for (Account acc : accounts) {
            System.out.println("\nAccount: " + acc.getAccountHolder()
                    + " (" + acc.getAccountNumber() + ")");
            acc.displayAccountInfo();
            acc.calculateInterest();
        }

        System.out.println("\n=== TEST FINISHED ===");
    }
}