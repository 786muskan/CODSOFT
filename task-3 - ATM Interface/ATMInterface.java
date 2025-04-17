import javax.swing.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        while (true) {
            String[] options = {"Check Balance", "Deposit", "Withdraw", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "ATM Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                checkBalance();
            } else if (choice == 1) {
                deposit();
            } else if (choice == 2) {
                withdraw();
            } else {
                JOptionPane.showMessageDialog(null, "Thank you for using the ATM!");
                break;
            }
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(null, "Current Balance:" + String.format("%.2f", account.getBalance()));
    }

    private void deposit() {
        String input = JOptionPane.showInputDialog("Enter amount to deposit:");
        if (input == null) return;
        try {
            double amount = Double.parseDouble(input);
            if (account.deposit(amount)) {
                JOptionPane.showMessageDialog(null, "Successfully deposited " + amount);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount entered.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a numeric value.");
        }
    }

    private void withdraw() {
        String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
        if (input == null) return;
        try {
            double amount = Double.parseDouble(input);
            if (account.withdraw(amount)) {
                JOptionPane.showMessageDialog(null, "Successfully withdrawn " + amount);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance or invalid amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a numeric value.");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000.0); // starting balance ₹5000
        ATM atmMachine = new ATM(userAccount);
        atmMachine.start();
    }
}
