/*************************************************
 File: HomePage.java
 By: Hugo Gomez
 Date: 3/25/2024
 Description: This is the page for the bank
 account details, withdraw, depost, or
 delete their account or update
 *************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame implements ActionListener {

    private int accountId;
    private JButton jbViewAccount, jbDeposit, jbWithdraw, jbDeleteAccount, jbUpdateAccount;;
    private AccountDAOConcrete accountDAO;
    private Transaction transaction;

    public HomePage(int accountId) {
        this.accountId = accountId;
        setLayout(null);

        JLabel heading = new JLabel("Banking Application");
        heading.setBounds(100, 100, 400, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(heading);

        jbViewAccount = new JButton("View Account Details");
        jbViewAccount.setBounds(100, 200, 200, 40);
        jbViewAccount.addActionListener(this);
        add(jbViewAccount);

        jbDeposit = new JButton("Deposit");
        jbDeposit.setBounds(100, 250, 200, 40);
        jbDeposit.addActionListener(this);
        add(jbDeposit);

        jbWithdraw = new JButton("Withdraw");
        jbWithdraw.setBounds(100, 300, 200, 40);
        jbWithdraw.addActionListener(this);
        add(jbWithdraw);

        jbDeleteAccount = new JButton("Delete Account");
        jbDeleteAccount.setBounds(100, 350, 200, 40);
        jbDeleteAccount.addActionListener(this);
        add(jbDeleteAccount);

        jbUpdateAccount = new JButton("Update Account");
        jbUpdateAccount.setBounds(100, 400, 200, 40);
        jbUpdateAccount.addActionListener(this);
        add(jbUpdateAccount);

        accountDAO = new AccountDAOConcrete();

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jbViewAccount) {
            viewAccountDetails(accountId);
        } else if (ae.getSource() == jbDeposit) {
            depositFunds();
        } else if (ae.getSource() == jbWithdraw) {
            withdrawFunds();
        } else if (ae.getSource() == jbDeleteAccount) {
            deleteAccount();
        }
        else if (ae.getSource() == jbUpdateAccount) {
            updateAccount();
        }
    }
    private void viewAccountDetails(int accountId) {
        AccountDTO account = accountDAO.getId(accountId);
        if (account != null) {
            JOptionPane.showMessageDialog(this, "Account Details:\n" + account.toString(), "Account Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void depositFunds() {
        try {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter deposit amount:"));
            AccountDTO account = accountDAO.getId(accountId);
            if (account != null) {
                account.setBalance(account.getBalance() + amount);
                int success = accountDAO.updateAccount(account);
                if (success > 0) {
                    JOptionPane.showMessageDialog(this, "Deposit successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to deposit funds.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void withdrawFunds() {
        try {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter withdrawal amount:"));
            AccountDTO account = accountDAO.getId(accountId);
            if (account != null) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    int success = accountDAO.updateAccount(account);
                    if (success > 0) {
                        JOptionPane.showMessageDialog(this, "Withdrawal successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to withdraw funds.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAccount() {
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the account?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = accountDAO.deleteAccount(accountId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete account.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Deletion canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void updateAccount() {
        String newAccountType = JOptionPane.showInputDialog(this, "Enter new account type:");
        String newName = JOptionPane.showInputDialog(this, "Enter new name:");

        AccountDTO account = accountDAO.getId(accountId);

        if (account != null) {
            account.setAccountType(newAccountType);
            account.setName(newName);
            int success = accountDAO.updateAccount(account);

            if (success > 0) {
                JOptionPane.showMessageDialog(this, "Account details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update account details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int accountId = 1;
            new HomePage(accountId);
        });
    }
}
