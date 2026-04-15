
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
