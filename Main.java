/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
