import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

interface ATM {
    void displayTransactionHistory();
    void withdraw();
    void deposit();
    void transfer();
    void quit();
}

class ATMSystem implements ATM {
    private double balance;
    private List<String> transactionHistory;

    public ATMSystem() {
        balance = 0;
        transactionHistory = new ArrayList<>();
    }

    @Override
    public void displayTransactionHistory() {
        String message;
        if (transactionHistory.isEmpty()) {
            message = "No transactions found.";
        } else {
            StringBuilder sb = new StringBuilder("Transaction History:\n");
            for (String transaction : transactionHistory) {
                sb.append(transaction).append("\n");
            }
            message = sb.toString();
        }
        JOptionPane.showMessageDialog(null, message, "Transaction History", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void withdraw() {
        String input = JOptionPane.showInputDialog(null, "Enter the amount to withdraw:");
        if (input == null) return;

        try {
            double amount = Double.parseDouble(input);
            if (amount <= balance) {
                balance -= amount;
                String transaction = "Withdraw: " + amount;
                transactionHistory.add(transaction);
                JOptionPane.showMessageDialog(null, "Withdrawal successful. Current balance: " + balance,
                        "Withdraw", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance.", "Withdraw", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount.", "Withdraw", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deposit() {
        String input = JOptionPane.showInputDialog(null, "Enter the amount to deposit:");
        if (input == null) return;

        try {
            double amount = Double.parseDouble(input);
            balance += amount;
            String transaction = "Deposit: " + amount;
            transactionHistory.add(transaction);
            JOptionPane.showMessageDialog(null, "Deposit successful. Current balance: " + balance,
                    "Deposit", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount.", "Deposit", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void transfer() {
        String accountNumber = JOptionPane.showInputDialog(null, "Enter the recipient's account number:");
        if (accountNumber == null) return;

        String input = JOptionPane.showInputDialog(null, "Enter the amount to transfer:");
        if (input == null) return;

        try {
            double amount = Double.parseDouble(input);
            if (amount <= balance) {
                balance -= amount;
                String transaction = "Transfer: " + amount + " to Account Number: " + accountNumber;
                transactionHistory.add(transaction);
                JOptionPane.showMessageDialog(null, "Transfer successful. Current balance: " + balance,
                        "Transfer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance.", "Transfer", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount.", "Transfer", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void quit() {
        JOptionPane.showMessageDialog(null, "Thank you for using the ATM. Goodbye!", "Quit", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

public class AtmInterface{
    private static ATM atm;

    public static void main(String[] args) {
        atm = new ATMSystem();

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("ATM App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 1));

        JButton historyButton = new JButton("View Transaction History");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer");
        JButton quitButton = new JButton("Quit");

        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.displayTransactionHistory();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.deposit();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.transfer();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.quit();
            }
        });

        frame.add(historyButton);
        frame.add(withdrawButton);
        frame.add(depositButton);
        frame.add(transferButton);
        frame.add(quitButton);

        frame.setVisible(true);
    }
}
