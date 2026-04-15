/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
// Create a class called BankAccount, this is the code for the class :
package com.mycompany.bankaccount;

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return new ArrayList<>(transactions); }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be bigger than zero");
        }
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
    }

    protected void deductFromBalance(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction("WITHDRAWAL", amount));
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public void printStatement() {
        System.out.println("=== Account Statement ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner: " + ownerName);
        System.out.println("Current Balance: R" + balance);
        System.out.println("-------------------------");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        int start = Math.max(0, transactions.size() - 5);
        List<Transaction> recent = transactions.subList(start, transactions.size());

        System.out.println("Recent Transactions:");
        for (Transaction t : recent) {
            System.out.println(t);
        }
        System.out.println("=========================");
    }
}

// Create a main class, this should be the code :
package com.mycompany.bankaccount;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter your name: ");
        String ownerName = scanner.nextLine();

        System.out.print("Enter your initial balance: R");
        double balance = scanner.nextDouble();

        // Create a SavingsAccount (concrete subclass of BankAccount)
        SavingsAccount account = new SavingsAccount(accountNumber, ownerName, balance);

        int choice = -1;
        while (choice != 4) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Print Statement");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: R");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: R" + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: R");
                    double withdrawAmount = scanner.nextDouble();
                    try {
                        account.withdraw(withdrawAmount);
                        System.out.println("Withdrawal successful. New balance: R" + account.getBalance());
                    } catch (InsufficientFundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    account.printStatement();
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }
        scanner.close();
    }
}

// Create a class called InsifficientFundsException, this is the code :

package com.mycompany.bankaccount;

/**
 *
 * @author BrightonJonathan
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Create a class called SavingsAccount, this is the code:

package com.mycompany.bankaccount;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountNumber, String ownerName, double balance) {
        super(accountNumber, ownerName, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }
        if (amount > getBalance()) {
            throw new InsufficientFundsException("Cannot withdraw R" + amount
                    + ". Available balance: R" + getBalance());
        }
        deductFromBalance(amount);
    }
}

