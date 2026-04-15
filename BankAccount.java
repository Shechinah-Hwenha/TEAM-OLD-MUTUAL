/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

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


